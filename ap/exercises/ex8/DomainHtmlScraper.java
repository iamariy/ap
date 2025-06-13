package ap.exercises.ex8;


import ap.exercises.ex5.HtmlFetcher;
import ap.exercises.ex5.HtmlFileManager;
import ap.exercises.ex5.HtmlParser;
import ap.exercises.ex5.ImageDownloader;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

public class DomainHtmlScraper {
    private String domainAddress;
    private Queue<String> queue;
    private HtmlFileManager htmlFileManager;
    private ImageDownloader imageDownloader;
    private Set<String> visitedUrls = Collections.synchronizedSet(new HashSet<>());
    private Set<String> collectedImageLinks = Collections.synchronizedSet(new HashSet<>());
    private String imageLinksSavePath;
    private ExecutorService executorService;
    private int threadCount;

    public DomainHtmlScraper(String domainAddress, String savePath, String imageLinksSavePath) {
        this.domainAddress = domainAddress;
        this.queue = new ConcurrentLinkedQueue<>();
        this.htmlFileManager = new HtmlFileManager(savePath);
        this.imageDownloader = new ImageDownloader();
        this.imageLinksSavePath = imageLinksSavePath;
        this.threadCount = ConfigManager.getThreadCount();

        if (threadCount > 0) {
            this.executorService = Executors.newFixedThreadPool(threadCount);
        }
    }

    public void start() throws IOException {
        // Initial setup (single-threaded)
        List<String> htmlLines = HtmlFetcher.fetchHtml(domainAddress);
        this.htmlFileManager.save(htmlLines);

        List<String> urls = HtmlParser.getAllUrlsFromList(htmlLines);
        queue.addAll(new HashSet<>(urls));
        visitedUrls.add(domainAddress);

        List<String> initialImageLinks = HtmlParser.getAllImageUrlsFromHtmlLines(htmlLines);
        collectedImageLinks.addAll(initialImageLinks);

        if (threadCount > 0) {
            startMultiThreaded();
        } else {
            startSingleThreaded();
        }
    }

    private void startSingleThreaded() {
        int counter = 1;

        while (!queue.isEmpty()) {
            String url = queue.poll();
            processUrl(url, counter++);
        }

        saveFinalResults();
    }

    private void startMultiThreaded() {
        List<Future<?>> futures = new ArrayList<>();
        final int[] counter = {1};

        while (!queue.isEmpty() || !allTasksCompleted(futures)) {
            if (!queue.isEmpty()) {
                String url = queue.poll();
                futures.add(executorService.submit(() -> processUrl(url, counter[0]++)));
            }

            // Clean up completed futures
            futures.removeIf(Future::isDone);

            try {
                Thread.sleep(100); // Small delay to prevent busy waiting
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        saveFinalResults();
    }

    private boolean allTasksCompleted(List<Future<?>> futures) {
        return futures.stream().allMatch(Future::isDone);
    }

    private void processUrl(String url, int counter) {
        if (visitedUrls.contains(url)) {
            return;
        }

        visitedUrls.add(url);

        try {
            List<String> htmlLines = HtmlFetcher.fetchHtml(url);
            this.htmlFileManager.save(htmlLines);

            List<String> imageLinks = HtmlParser.getAllImageUrlsFromHtmlLines(htmlLines);
            collectedImageLinks.addAll(imageLinks);

            if (counter % 10 == 0) {
                synchronized (this) {
                    ImageDownloader.saveImageLinksToFile(imageLinksSavePath, collectedImageLinks);
                }
            }

            List<String> urls = HtmlParser.getAllUrlsFromList(htmlLines);
            for (String newUrl : urls) {
                if (!visitedUrls.contains(newUrl) && !queue.contains(newUrl)) {
                    queue.add(newUrl);
                }
            }

            System.out.println("[" + counter + "] " + url + " fetched and saved (queue size: " + queue.size() + ").");
        } catch (Exception e) {
            System.out.println("ERROR: " + url + "\t -> " + e.getMessage());
        }
    }

    private void saveFinalResults() {
        ImageDownloader.saveImageLinksToFile(imageLinksSavePath, collectedImageLinks);
        System.out.println("Scraping completed. Total image links collected: " + collectedImageLinks.size());
    }
}

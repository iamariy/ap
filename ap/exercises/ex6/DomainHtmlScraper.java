package ap.exercises.ex6;


import ap.exercises.ex5.HtmlFetcher;
import ap.exercises.ex5.HtmlFileManager;
import ap.exercises.ex5.HtmlParser;
import ap.exercises.ex5.ImageDownloader;

import java.io.IOException;
import java.util.*;

public class DomainHtmlScraper {
    private String domainAddress;
    private Queue<String> queue;
    private HtmlFileManager htmlFileManager;
    private ImageDownloader imageDownloader;
    private Set<String> visitedUrls = new HashSet<>();
    private Set<String> collectedImageLinks = new HashSet<>();
    private String imageLinksSavePath;

    public DomainHtmlScraper(String domainAddress, String savePath, String imageLinksSavePath) {
        this.domainAddress = domainAddress;
        this.queue = new LinkedList<>();
        this.htmlFileManager = new HtmlFileManager(savePath);
        this.imageDownloader = new ImageDownloader();
        this.imageLinksSavePath = imageLinksSavePath;
    }

    public void start() throws IOException {
        List<String> htmlLines = HtmlFetcher.fetchHtml(domainAddress);
        this.htmlFileManager.save(htmlLines);

        List<String> urls = HtmlParser.getAllUrlsFromList(htmlLines);
        queue.addAll(new HashSet<>(urls));
        visitedUrls.add(domainAddress);

        List<String> initialImageLinks = HtmlParser.getAllImageUrlsFromHtmlLines(htmlLines);
        collectedImageLinks.addAll(initialImageLinks);

        int counter = 1;

        while (!queue.isEmpty()) {
            String url = queue.remove();

            if (visitedUrls.contains(url)) {
                continue;
            }

            visitedUrls.add(url);
            counter++;

            try {
                htmlLines = HtmlFetcher.fetchHtml(url);
                this.htmlFileManager.save(htmlLines);

                List<String> imageLinks = HtmlParser.getAllImageUrlsFromHtmlLines(htmlLines);
                collectedImageLinks.addAll(imageLinks);

                if (counter % 10 == 0) {
                    ImageDownloader.saveImageLinksToFile(imageLinksSavePath, collectedImageLinks);
                }

                urls = HtmlParser.getAllUrlsFromList(htmlLines);
                for (String newUrl : urls) {
                    if (!visitedUrls.contains(newUrl) && !queue.contains(newUrl)) {
                        queue.add(newUrl);
                    }
                }
            } catch (Exception e) {
                System.out.println("ERROR: " + url + "\t -> " + e.getMessage());
            }

            System.out.println("[" + counter + "] " + url + " fetched and saved (queue size: " + queue.size() + ").");
        }

        ImageDownloader.saveImageLinksToFile(imageLinksSavePath, collectedImageLinks);
        System.out.println("Scraping completed. Total image links collected: " + collectedImageLinks.size());
    }
}

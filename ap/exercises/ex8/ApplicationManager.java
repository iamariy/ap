package ap.exercises.ex8;

import java.io.IOException;
import java.util.Scanner;

public class ApplicationManager {
    private Scanner scanner;

    public ApplicationManager() {
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Select a program to run:");
        System.out.println("1. Website Downloader");
        System.out.println("2. Domain ZNU Downloader");
        System.out.println("3. MP3 Downloader");
        System.out.println("4. Image Downloader");
        System.out.println("5. Domain HTML Scraper");
        System.out.print("Enter your choice (1-5): ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                runWebsiteDownloader();
                break;
            case 2:
                runDomainZnuDownloader();
                break;
            case 3:
                runMP3Downloader();
                break;
            case 4:
                runImageDownloader();
                break;
            case 5:
                runDomainHtmlScraper();
                break;
            default:
                System.out.println("Invalid choice!");
        }

        scanner.close();
    }

    private void runWebsiteDownloader() {
        System.out.println("\nRunning Website Downloader...");
        WebsiteDownloader.main(new String[]{});
    }

    private void runDomainZnuDownloader() {
        System.out.println("\nRunning Domain ZNU Downloader...");
        DomainZnuDownloader.main(new String[]{});
    }

    private void runMP3Downloader() {
        System.out.println("\nRunning MP3 Downloader...");
        System.out.print("Enter MP3 URL: ");
        String mp3Url = scanner.nextLine();
        System.out.print("Enter save path (with .mp3 extension): ");
        String savePath = scanner.nextLine();

        MP3Downloader.downloadMP3(mp3Url, savePath);
        MP3Downloader.shutdown();
    }

    private void runImageDownloader() {
        System.out.println("\nRunning Image Downloader...");
        System.out.print("Enter Image URL: ");
        String imageUrl = scanner.nextLine();
        System.out.print("Enter save path: ");
        String savePath = scanner.nextLine();

        try {
            ImageDownloader.downloadImage(imageUrl, savePath);
        } catch (IOException e) {
            System.err.println("Error downloading image: " + e.getMessage());
        }
        ImageDownloader.shutdown();
    }

    private void runDomainHtmlScraper() {
        System.out.println("\nRunning Domain HTML Scraper...");
        System.out.print("Enter domain URL (e.g., https://example.com): ");
        String domainUrl = scanner.nextLine();
        System.out.print("Enter HTML save directory: ");
        String htmlSavePath = scanner.nextLine();
        System.out.print("Enter image links save file path: ");
        String imageLinksPath = scanner.nextLine();

        try {
            DomainHtmlScraper scraper = new DomainHtmlScraper(domainUrl, htmlSavePath, imageLinksPath);
            scraper.start();
        } catch (IOException e) {
            System.err.println("Error starting scraper: " + e.getMessage());
        }
    }
}

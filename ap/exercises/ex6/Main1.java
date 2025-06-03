package ap.exercises.ex6;


public class Main1 {
    public static void main(String[] args) {
        String domainAddress = "https://example.com";
        String savePath = "output";
        String imageLinksSavePath = "output/image_links.txt";

        DomainHtmlScraper scraper = new DomainHtmlScraper(domainAddress, savePath, imageLinksSavePath);

        try {
            scraper.start();
        } catch (Exception e) {
            System.out.println("Error while scraping: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

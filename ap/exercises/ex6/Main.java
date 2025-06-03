package ap.exercises.ex6;

import ap.exercises.ex5.Conf;
import ap.exercises.ex5.DomainHtmlScraper;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        String domainAddress = ap.exercises.ex5.Conf.DOMAIN_ADDRESS;
        String savePath = Conf.SAVE_DIRECTORY;

        ap.exercises.ex5.DomainHtmlScraper domainHtmlScraper = new DomainHtmlScraper(domainAddress,savePath);

        domainHtmlScraper.start();
    }
}

package ap.exercises.ex6;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

public class WebsiteDownloader {
    static class Config {
        static final int DOWNLOAD_DELAY = 5000;
        static final String BASE_URL = "https://www.znu.ac.ir/";
        static final String OUTPUT_DIR = "downloaded_files";

        static final String HTML_DIR = OUTPUT_DIR + "/html";
        static final String IMAGE_DIR = OUTPUT_DIR + "/image";
        static final String SONG_DIR = OUTPUT_DIR + "/song";
    }

    public static void main(String[] args) {
        try {
            createDirectories();

            downloadWebsiteContent(Config.BASE_URL);

            System.out.println("Downloaded successfully!");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void createDirectories() throws IOException {
        Files.createDirectories(Paths.get(Config.HTML_DIR));
        Files.createDirectories(Paths.get(Config.IMAGE_DIR));
        Files.createDirectories(Paths.get(Config.SONG_DIR));
    }

    private static void downloadWebsiteContent(String url) {
        try {
            String htmlContent = downloadHtmlPage(url);
            saveHtmlFile(url, htmlContent);

            List<String> imageUrls = extractUrls(htmlContent, "img", "src");
            downloadFiles(imageUrls, Config.IMAGE_DIR);

            List<String> audioUrls = extractUrls(htmlContent, "audio", "src");
            downloadFiles(audioUrls, Config.SONG_DIR);

        } catch (Exception e) {
            System.err.println("Error for downloading: " + e.getMessage());
        }
    }

    private static String downloadHtmlPage(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        StringBuilder content = new StringBuilder();
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
        }
        return content.toString();
    }

    private static void saveHtmlFile(String url, String content) throws IOException {
        String fileName = url.substring(url.lastIndexOf('/') + 1);
        if (fileName.isEmpty()) fileName = "index.html";

        Path path = Paths.get(Config.HTML_DIR, fileName);
        Files.write(path, content.getBytes());

        System.out.println("HTML Saved: " + fileName);
    }

    private static List<String> extractUrls(String html, String tag, String attribute) {
        List<String> urls = new ArrayList<>();
        String pattern = "<" + tag + "[^>]*" + attribute + "=\"([^\"]*)\"";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(html);

        while (m.find()) {
            urls.add(m.group(1));
        }
        return urls;
    }

    private static void downloadFiles(List<String> urls, String outputDir) {
        for (String url : urls) {
            try {
                if (!url.startsWith("http")) {
                    url = Config.BASE_URL + url;
                }

                String fileName = url.substring(url.lastIndexOf('/') + 1);
                Path outputPath = Paths.get(outputDir, fileName);

                if (Files.exists(outputPath)) {
                    continue;
                }

                try (InputStream in = new URL(url).openStream()) {
                    Files.copy(in, outputPath);
                    System.out.println("Downloaded: " + fileName);

                    Thread.sleep(Config.DOWNLOAD_DELAY);
                }
            } catch (Exception e) {
                System.err.println("Error for downloading: " + url + ": " + e.getMessage());
            }
        }
    }
}

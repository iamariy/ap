package ap.exercises.ex6;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DomainZnuDownloader {
    private static final String TARGET_DOMAIN = "znu.ac.ir";
    private static final String DOWNLOAD_ROOT = "downloads";

    public static void main(String[] args) {
        String[] urlsToDownload = {
                "https://www.znu.ac.ir/znujrnls/test.html",
                "https://mail.znu.ac.ir/login/test.html",
                "https://rules.znu.ac.ir/document.pdf",
                "https://example.com/test.html"
        };

        for (String url : urlsToDownload) {
            try {
                downloadIfInTargetDomain(url);
            } catch (IOException e) {
                System.err.println("Error downloading " + url + ": " + e.getMessage());
            }
        }
    }

    public static void downloadIfInTargetDomain(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        String host = url.getHost();

        if (!host.endsWith(TARGET_DOMAIN)) {
            System.out.println("Skipping: " + urlStr + " (not in target domain)");
            return;
        }

        String subdomain = extractSubdomain(host);
        String filePath = url.getPath();
        String fileName = filePath.substring(filePath.lastIndexOf('/') + 1);

        Path downloadPath = Paths.get(DOWNLOAD_ROOT);
        if (subdomain != null) {
            downloadPath = downloadPath.resolve("_" + subdomain);
        }

        String dirPath = filePath.substring(0, filePath.lastIndexOf('/'));
        for (String part : dirPath.split("/")) {
            if (!part.isEmpty()) {
                downloadPath = downloadPath.resolve(part);
            }
        }

        Files.createDirectories(downloadPath);

        Path outputFile = downloadPath.resolve(fileName);

        System.out.println("Downloading: " + urlStr + " to " + outputFile);
        URLConnection connection = url.openConnection();
        try (InputStream in = connection.getInputStream();
             OutputStream out = new FileOutputStream(outputFile.toFile())) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
        System.out.println("Successfully downloaded: " + fileName);
    }

    private static String extractSubdomain(String host) {
        if (host.startsWith("www.")) {
            host = host.substring(4);
        }

        if (host.equals(TARGET_DOMAIN)) {
            return null;
        }

        int dotIndex = host.indexOf('.');
        if (dotIndex > 0) {
            return host.substring(0, dotIndex);
        }
        return null;
    }
}

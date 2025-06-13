package ap.exercises.ex8;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MP3Downloader {
    private static ExecutorService executorService;

    static {
        int threadCount = ConfigManager.getThreadCount();
        if (threadCount > 0) {
            executorService = Executors.newFixedThreadPool(threadCount);
        }
    }

    public static void downloadMP3(String fileUrl, String savePath) {
        if (executorService != null) {
            executorService.submit(() -> {
                try {
                    downloadMP3Internal(fileUrl, savePath);
                } catch (IOException e) {
                    System.err.println("Error downloading MP3: " + fileUrl + " - " + e.getMessage());
                }
            });
        } else {
            try {
                downloadMP3Internal(fileUrl, savePath);
            } catch (IOException e) {
                System.err.println("Error downloading MP3: " + e.getMessage());
            }
        }
    }

    private static void downloadMP3Internal(String fileUrl, String savePath) throws IOException {
        // اعتبارسنجی ورودی‌ها (همانند قبل)
        if (fileUrl == null || fileUrl.isBlank()) {
            throw new IllegalArgumentException("URL cannot be null or empty");
        }
        if (savePath == null || savePath.isBlank()) {
            throw new IllegalArgumentException("Save path cannot be null or empty");
        }
        if (!savePath.toLowerCase().endsWith(".mp3")) {
            throw new IllegalArgumentException("Save path must end with .mp3 extension");
        }

        Path outputPath = Paths.get(savePath);
        Files.createDirectories(outputPath.getParent());

        try (InputStream in = new URL(fileUrl).openStream();
             FileOutputStream fileOut = new FileOutputStream(savePath)) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                fileOut.write(buffer, 0, bytesRead);
            }
            System.out.println("Downloaded: " + savePath);
        }
    }

    public static void shutdown() {
        if (executorService != null) {
            executorService.shutdown();
        }
    }
}

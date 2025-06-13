package ap.exercises.ex8;


import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageDownloader {
    private static ExecutorService downloadExecutor;

    static {
        int threadCount = ConfigManager.getThreadCount();
        if (threadCount > 0) {
            downloadExecutor = Executors.newFixedThreadPool(threadCount);
        }
    }

    public static void downloadImage(String imageUrl, String outputPath) throws IOException {
        if (downloadExecutor != null) {
            downloadExecutor.submit(() -> {
                try {
                    downloadImageInternal(imageUrl, outputPath);
                } catch (IOException e) {
                    System.err.println("Failed to download image: " + imageUrl + " - " + e.getMessage());
                }
            });
        } else {
            downloadImageInternal(imageUrl, outputPath);
        }
    }

    private static void downloadImageInternal(String imageUrl, String outputPath) throws IOException {
        // Validate inputs
        if (imageUrl == null || imageUrl.isBlank()) {
            throw new IllegalArgumentException("Image URL cannot be null or empty");
        }
        if (outputPath == null || outputPath.isBlank()) {
            throw new IllegalArgumentException("Output path cannot be null or empty");
        }

        URL url = new URL(imageUrl);
        try (InputStream in = url.openStream()) {
            Path output = Path.of(outputPath);
            try {
                Files.createDirectories(output.getParent());
            } catch (Exception e) {
                // Ignore directory creation errors
            }
            Files.copy(in, output, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    public static synchronized void saveImageLinksToFile(String filePath, Set<String> imageLinks) {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            for (String link : imageLinks) {
                writer.println(link);
            }
            System.out.println("Image links saved to " + filePath);
        } catch (IOException e) {
            System.err.println("Failed to save image links: " + e.getMessage());
        }
    }

    public static void shutdown() {
        if (downloadExecutor != null) {
            downloadExecutor.shutdown();
        }
    }
}

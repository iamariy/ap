package ap.exercises.ex8;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigManager {
    private static final String CONFIG_FILE = "download_config.properties";
    private static final Properties properties = new Properties();

    static {
        try {
            if (Files.exists(Paths.get(CONFIG_FILE))) {
                properties.load(Files.newInputStream(Paths.get(CONFIG_FILE)));
            } else {
                // Default configuration
                properties.setProperty("thread-count", "0");
                properties.store(Files.newOutputStream(Paths.get(CONFIG_FILE)),
                        "Download Configuration");
            }
        } catch (IOException e) {
            System.err.println("Error loading config file: " + e.getMessage());
        }
    }

    public static int getThreadCount() {
        try {
            return Integer.parseInt(properties.getProperty("thread-count", "0"));
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}

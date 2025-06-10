package ap.exercises.ex7;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final String CONFIG_FILE = "config.properties";

    public static String getStorageType() {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            props.load(fis);
            return props.getProperty("storage", "tabsplit");
        } catch (IOException e) {
            System.err.println("Error to read file");
            return "tabsplit";
        }
    }
}

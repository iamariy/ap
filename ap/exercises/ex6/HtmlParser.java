package ap.exercises.ex6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HtmlParser {

    public static String getFirstUrl(String htmlLine) {
        String url = null;
        int startIndex = htmlLine.indexOf("href=\"");
        if (startIndex >= 0) {
            try {
                int hrefLength = "href\"".length();
                int endIndex = htmlLine.indexOf("\"", startIndex + hrefLength + 1);
                url = htmlLine.substring(startIndex + hrefLength + 1, endIndex);
            } catch (Exception e) {
            }
        }
        return url;
    }

    private static List<String> getAllUrlsFromHtmlLinesStream(Stream<String> htmlLinesStream) throws IOException {
        List<String> urls = htmlLinesStream
                .map(line -> getFirstUrl(line))
                .filter(s -> s != null)
                .collect(Collectors.toList());
        return urls;
    }

    public static List<String> getAllUrlsFromFile(String filePath) throws IOException {
        return getAllUrlsFromHtmlLinesStream(Files.lines(Path.of(filePath)));
    }

    public static List<String> getAllUrlsFromList(List<String> htmlLines) throws IOException {
        return getAllUrlsFromHtmlLinesStream(htmlLines.stream());
    }

    public static List<String> getAllImageUrlsFromHtmlLines(List<String> htmlLines) {
        return htmlLines.stream()
                .map(ap.exercises.ex5.HtmlParser::getImageUrl)
                .filter(s -> s != null && !s.isBlank())
                .collect(Collectors.toList());
    }

    public static String getImageUrl(String htmlLine) {
        int startIndex = htmlLine.indexOf("img src=\"");
        if (startIndex >= 0) {
            try {
                int endIndex = htmlLine.indexOf("\"", startIndex + 9);
                return htmlLine.substring(startIndex + 9, endIndex);
            } catch (Exception ignored) {}
        }
        return null;
    }

}

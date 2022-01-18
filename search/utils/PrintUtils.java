package search.utils;

/**
 * Util class for printing in proper formats etc.
 */
public class PrintUtils {

    /**
     * Join separated words back together to a proper sentence.
     */
    public static String getDisplayFormat(String[] wordsInLine) {
        return String.join(" ", wordsInLine);
    }
}

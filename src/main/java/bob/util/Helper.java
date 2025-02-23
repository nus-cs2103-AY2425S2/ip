package bob.util;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * This class contains static helper methods for Bob.
 */
public class Helper {

    /**
     * Converts a LocalDateTime object to a readable string for output purposes.
     *
     * @param dateTime object.
     * @return String.
     */
    public static String datetimeToReadable(LocalDateTime dateTime) {
        // 'withLocale' is needed to ensure AM/PM capitalisation is standard across OS-es
        // Locale.US ensures AM/PM is capitalised
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mma").withLocale(Locale.US);
        return dateTime.format(formatter);
    }

    /**
     * Converts user's input to a LocalDateTime object for storage purposes.
     *
     * @param input String.
     * @return LocalDateTime object.
     */
    public static LocalDateTime inputToDateTime(String input) {
        return LocalDateTime.parse(input, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }
}

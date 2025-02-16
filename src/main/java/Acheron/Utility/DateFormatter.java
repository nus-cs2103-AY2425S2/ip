package Acheron.Utility;

import java.time.LocalDate;

/**
 * A utility class to format the date correctly when trying to display them
 */
public class DateFormatter {

    /**
     * Takes in a date object and create a custom string display for them
     * @param date A date object
     * @return A custom string text representing the date
     */
    public static String formatDate(LocalDate date) {
        String stringDate = String.valueOf(date);
        String day = stringDate.substring(stringDate.length() - 2);
        String month = String.valueOf(date.getMonth());
        String year = String.valueOf(date.getYear());
        assert day.length() <= 2;
        assert year.length() == 4;
        return "%s %s %s".formatted(month, day, year);
    }
}

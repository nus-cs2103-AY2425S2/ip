package zea.ui;

import java.util.ArrayList;
import java.util.Objects;

import zea.ZeaException;


/**
 * Handles interactions with the user
 */
public class Ui {
    /**
     * Display the welcome message
     */
    public static String showWelcome() {
        return """
                Hello! I'm Zea.Zea
                What can I do for you?
                """;
    }

    /**
     * Displays the loading error message to the user
     *
     * @param e - the error to be displayed
     */
    public static String showLoadingError(ZeaException e) {
        return e.getMessage();
    }

    /**
     * Displays a single item along with a header and footer
     *
     * @param header - a message before the item is displayed
     * @param item   - the item to be displayed
     * @param footer - a message after the item is displayed
     * @param <T>    - any type that can be printed
     */
    public static <T>String formatItem(String header, T item, String footer) {
        StringBuilder sb = new StringBuilder();
        sb.append(header);
        sb.append("\n");
        sb.append(item);
        if (!Objects.equals(footer, "")) {
            sb.append("\n");
            sb.append(footer);
        }
        return sb.toString();
    }

    /**
     * Displays all the items with numbering
     *
     * @param header - a message before the item is displayed
     * @param list   - a list of items to be printed
     * @param <T>    - any type that can be printed
     */
    public static <T> String formatWithNumbering(String header, ArrayList<T> list) {
        StringBuilder sb = new StringBuilder();
        sb.append(header).append("\n");
        for (int i = 0; i < list.size(); i++) {
            T t = list.get(i);
            sb.append(i + 1).append(". ").append(t).append(i < list.size() - 1 ? "\n" : "");
        }
        return sb.toString();
    }

    public static String displayMessage(String message) {
        return message;
    }
}

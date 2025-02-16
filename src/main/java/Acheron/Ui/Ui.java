package Acheron.Ui;

import Acheron.Acheron;

/**
 * Represents the UI display
 */
public class Ui {
    private static String genericText = "________________________________________________________\n"
            + "%s\n"
            + "________________________________________________________";

    /**
     * Displays the text on the terminal or in the UI
     * @param text The text to be shown
     */
    public static void displayText(String text) {
        Acheron.setMessage(genericText.formatted(text));
    }
}

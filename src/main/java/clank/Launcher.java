package clank;

import clank.gui.MainApp;
import javafx.application.Application;

/**
 * Launcher for the Clank application.
 */
public class Launcher {

    /**
     * The main entry point of the application.
     * Calls {@link Application#launch(Class, String...)} to start the JavaFX application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Application.launch(MainApp.class, args);
    }
}

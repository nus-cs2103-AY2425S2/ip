package taskmax.main;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues when launching the JavaFX application.
 */
public class Launcher {
    /**
     * Main entry point for the JavaFX application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
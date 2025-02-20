package adam.javafx;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Launches the application.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}

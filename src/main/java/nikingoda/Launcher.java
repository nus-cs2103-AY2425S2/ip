package nikingoda;

import javafx.application.Application;
import nikingoda.NikiNgoda.Main;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}

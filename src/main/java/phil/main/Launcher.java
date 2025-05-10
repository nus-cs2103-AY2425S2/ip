package phil.main;

import javafx.application.Application;

/**
 * Represents a Launcher used to workaround path problems.
 * This class should be executed to initiate the bot (Graphical UI version) by
 * launching the PhilMain class.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(PhilMain.class, args);
    }
}

//Solution below adapted from https://se-education.org/guides/tutorials/javaFx.html

import javafx.application.Application;
// import static your.package.Constants.*; // Adjust the package name accordingly

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        validateArgs(args);
        launchApplication(args);
    }

    /**
     * Validates the arguments passed to the main method.
     *
     * @param args The arguments passed to the main method.
     */

    private static void validateArgs(String[] args) {
        assert args != null : "args should not be null";
        assert Main.class != null : "Main class should not be null";
    }

    /**
     * Launches the application with the specified arguments.
     *
     * @param args The arguments to pass to the application.
     */

    private static void launchApplication(String[] args) {
        Application.launch(Main.class, args);
    }
}


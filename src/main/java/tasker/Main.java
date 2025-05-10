package tasker;

import javafx.application.Application;
import tasker.ui.Ui;

/**
 * A launcher class to workaround classpath issues.
 */
public class Main {
    public static void main(String[] args) {
        Application.launch(Ui.class, args);
    }
}

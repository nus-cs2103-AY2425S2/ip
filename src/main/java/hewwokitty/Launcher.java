package hewwokitty;

import javafx.application.Application;

public class Launcher {
    public static void main(String[] args) {
        assert args != null : "Application arguments should not be null";
        Application.launch(HewwoKittyGUI.class, args);
    }
}

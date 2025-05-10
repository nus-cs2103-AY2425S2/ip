package nyanko.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import nyanko.Nyanko;

/**
 * The entry point for the Nyanko GUI application.
 * This class initializes and starts the JavaFX application.
 */
public class Main extends Application {

    private Nyanko nyanko = new Nyanko("data/nyanko.txt");

    /**
     * Starts the JavaFX application and sets up the primary stage.
     *
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            MainWindow controller = fxmlLoader.getController();
            controller.setNyanko(nyanko);

            stage.setTitle("Nyanko - Task Manager");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package perrygpt.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import perrygpt.PerryGPT;

/**
 * The main entry point for the GUI application using JavaFX.
 * This class initializes and launches the graphical user interface for PerryGPT.
 */
public class Main extends Application {

    private final PerryGPT perryGPT = new PerryGPT("data/PerryGPT.txt");

    /**
     * Starts the GUI application by loading the FXML layout and setting up the primary stage.
     *
     * @param stage The primary stage for the application window.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("PerryGPT");

            // Inject the Rose instance into the controller
            fxmlLoader.<MainWindow>getController().setPerry(perryGPT);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

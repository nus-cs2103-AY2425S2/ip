package clank.gui;

import java.io.IOException;

import clank.Clank;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Clank using FXML.
 */
public class MainApp extends Application {

    private Clank clank = new Clank("./data/clank.txt");

    /**
     * Starts the JavaFX application by loading the GUI from an FXML file.
     *
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            fxmlLoader.<MainWindow>getController().setClank(clank); // inject the Clank instance

            stage.setTitle("Clank");
            stage.setMinWidth(400);
            stage.setMinHeight(600);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package miki.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import miki.Miki;

/**
 * Main class to start the GUI.
 */
public class Main extends Application {
    /**
     * The Miki instance to be used by the GUI.
     */
    private Miki miki = new Miki();

    /**
     * Starts the GUI.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = loader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Miki");
            loader.<MainWindow>getController().setMiki(miki);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

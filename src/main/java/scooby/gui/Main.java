package scooby.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import scooby.ui.Scooby;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private Scooby scooby = new Scooby();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setScooby(scooby);  // inject the Scooby instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

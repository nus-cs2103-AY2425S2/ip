package dew;

import java.io.IOException;

import gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Dew using FXML.
 */
public class Main extends Application {
    private static final String FILE_PATH = "data/tasks.txt";
    private final Dew dew = new Dew(FILE_PATH);

    @Override
    public void start(Stage stage) {
        try {
            assert dew != null : "Dew instance should be initialized before starting the app";
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Dew");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDew(dew);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

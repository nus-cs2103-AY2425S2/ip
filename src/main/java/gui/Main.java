package gui;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ricky.Ricky;

/**
 * A GUI for Ricky using FXML.
 */
public class Main extends Application {

    private static final Path filePath = Paths.get("src", "main", "data", "ricky.txt");
    private Ricky ricky = new Ricky(filePath);

    /**
     * Starts the application.
     *
     * @param stage The stage to start.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setOnCloseRequest(event -> handleWindowClose());
            fxmlLoader.<MainWindow>getController().setRicky(ricky);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the tasks when the window is closed.
     */
    private void handleWindowClose() {
        ricky.saveTasks();
    }
}

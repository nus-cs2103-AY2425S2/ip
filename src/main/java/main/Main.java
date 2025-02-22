package main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Samantha using FXML.
 */
public class Main extends Application {

    private Samantha samantha = new Samantha("./data/samantha.txt");

    /**
     * Starts the JavaFX application.
     *
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        assert stage != null : "Stage cannot be null";
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSamantha(samantha);
            stage.setTitle("Samantha");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

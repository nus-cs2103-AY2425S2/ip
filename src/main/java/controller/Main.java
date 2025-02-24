package controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Alice;

/**
 * Main entry point for Alice chatbot.
 */
public class Main extends Application {

    private final Alice alice = new Alice();

    /**
     * Starts the JavaFX application.
     *
     * @param stage the primary stage for this application
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/MainWindow.fxml"));
            VBox vb = fxmlLoader.load();
            Scene scene = new Scene(vb);
            stage.setScene(scene);
            stage.getIcons().add(new Image(Main.class.getResourceAsStream("images/alice_annoyed.png")));
            stage.setTitle("alice");
            fxmlLoader.<MainWindow>getController().setAlice(alice);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

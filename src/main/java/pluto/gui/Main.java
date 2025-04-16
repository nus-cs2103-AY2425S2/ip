package pluto.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import pluto.Pluto;

/**
 * The Main class initializes the JavaFX application
 */
public class Main extends Application {
    private Pluto pluto = new Pluto();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            scene.getStylesheets().add(getClass().getResource("/view/styles.css").toExternalForm());
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setPluto(pluto);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
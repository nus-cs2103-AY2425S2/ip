package fx;

import java.io.IOException;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import yuki.Yuki;

import static java.lang.Thread.sleep;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private final Yuki yuki = new Yuki("./data/yuki.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Yuki");
            fxmlLoader.<MainWindow>getController().setDuke(yuki);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

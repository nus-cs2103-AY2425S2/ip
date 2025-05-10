package partillay.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Partillay using FXML.
 */
public class Main extends Application {

    private final Partillay partillay = new Partillay();

    @Override
    public void start(Stage stage) {
        try {
            stage.setMinWidth(417);
            stage.setMinHeight(220);
            stage.setTitle("Partillay");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setPartillay(partillay);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

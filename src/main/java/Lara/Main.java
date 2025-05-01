package Lara;

import Lara.ui.Lara;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Lara duke = new Lara("hello.txt");

    @Override
    public void start(Stage stage) {
        try {
            setupUI(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupUI(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
        assert fxmlLoader.getLocation() != null : "FXML file is missing!";

        AnchorPane ap = fxmlLoader.load();
        Scene scene = new Scene(ap);
        assert scene != null : "Scene should not be null before setting the stage!";

        stage.setScene(scene);
        stage.setTitle("Lara");
        stage.show();

        fxmlLoader.<MainWindow>getController().setDuke(duke);
    }
}

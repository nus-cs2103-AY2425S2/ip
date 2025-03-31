package clovis.gui;

import java.io.IOException;

import clovis.Clovis;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Clovis using FXML.
 */
public class Main extends Application {

    private Clovis clovis = new Clovis("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Clovis");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setClovis(clovis);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

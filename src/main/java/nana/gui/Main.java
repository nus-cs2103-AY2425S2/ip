package nana.gui;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import nana.logic.Nana;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Nana nana = new Nana("./data/Nana.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/nana/gui/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setNana(nana);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


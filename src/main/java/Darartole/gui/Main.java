package Darartole.gui;


import java.io.IOException;

import Darartole.ui.Darartole;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for the chatbot Darartole using FXML
 */
public class Main extends Application {
    private Darartole darartole = new Darartole();


    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Darartole");
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setDarartole(darartole);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}

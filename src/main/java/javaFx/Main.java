package javaFx;

import Aquadem.AquaDem;


import java.io.IOException;

import Aquadem.Storage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private AquaDem aquaDem = new AquaDem(new Storage());

    private Stage mainStage;

    /**
     * Sets the stage to the required scene.
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     *
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setAquadem(aquaDem);  // inject the Duke instance
            fxmlLoader.<MainWindow>getController().setStage(stage);
            this.mainStage = stage;
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}


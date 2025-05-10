package parakeet.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import parakeet.Parakeet;

import java.io.IOException;


public class Main extends Application {

    private Scene scene;
    private Parakeet parakeet = new Parakeet();
    @Override
    public void start(Stage stage) {
        //Setting up required components
        try {
            stage.setTitle("Parakeet");
            stage.setResizable(false);
            stage.setMinHeight(600.0);
            stage.setMinWidth(400.0);

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));

            AnchorPane mainLayout = fxmlLoader.load();
            scene = new Scene(mainLayout);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(parakeet);
            stage.show();

            stage.setOnCloseRequest(event -> {
                parakeet.getResponse("bye");
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

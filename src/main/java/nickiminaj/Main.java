package nickiminaj;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for NickiMinaj using FXML.
 */
public class Main extends Application {

    private NickiMinaj nickiMinaj = new NickiMinaj("./data/nickiminaj.NickiMinaj.txt"); //change thisfile path

    @Override
    public void start(Stage stage) {
        stage.setTitle("Nicki Minaj: Queen of Rap");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setNickiMinaj(nickiMinaj);  // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading FXML file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

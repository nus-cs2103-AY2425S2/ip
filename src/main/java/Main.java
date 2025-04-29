import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.fxml.FXMLLoader;

import doot.Doot;


/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Doot doot;


    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("CirnoBot");
            stage.setWidth(600);  // Default width
            stage.setHeight(800); // Default height
            fxmlLoader.<MainWindow>getController().setDoot(doot);  // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

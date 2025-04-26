package ui;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sunpter.Sunpter;
/**
 * A GUI for Duke using FXML.
 */
public class GUI extends Application {

    private Sunpter sunpter = new Sunpter();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("/view/GUIWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setScene(scene);
            fxmlLoader.<GUIWindow>getController().setSunpter(sunpter);  // inject the Sunpter instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package fx;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import thoughtbot.ThoughtBot;

/**
 * A GUI for ThoughtBot using FXML.
 */
public class Main extends Application {

    private ThoughtBot thoughtBot = new ThoughtBot();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("ThoughtBot");
            fxmlLoader.<MainWindow>getController().setThoughtBot(thoughtBot); // inject the ThoughtBot instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

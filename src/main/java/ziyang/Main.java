package ziyang;
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

    private Ziyang ziyang = new Ziyang();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(ziyang);  // inject the Duke instance
            fxmlLoader.<MainWindow>getController().sendMessage("Hello! I'm Zi Yang\nWhat can I do for you?");  // inject the Duke instance
            stage.setTitle("Your Assistant - Zi Yang");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


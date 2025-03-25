package buddy.ui;

import java.io.IOException;

import buddy.Buddy;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Buddy using FXML.
 */
public class Main extends Application {

    private final Buddy buddy = new Buddy();

    @Override
    public void start(Stage stage) {
        try {
            stage.setMinHeight(320);
            stage.setMinWidth(700);

            stage.setTitle("Buddy chatbot");

            Image appIcon = new Image(Main.class.getResourceAsStream("/images/doremon.png"));
            stage.getIcons().add(appIcon);

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            Scene scene = new Scene(ap);
            stage.setScene(scene);

            fxmlLoader.<MainWindow>getController().sendGreetMessage();
            fxmlLoader.<MainWindow>getController().setBuddy(buddy);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

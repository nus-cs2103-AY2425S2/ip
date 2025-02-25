package mars.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mars.Mars;

/**
 * A GUI for Mars using FXML.
 *
 * use FXML to separate view and controller aspects into separate files,
 * and demonstrate how FXML files and matching Java files can still refer each other's elements.
 */
public class Main extends Application {
    private Mars mars;

    @Override
    public void start(Stage stage) {
        try {
            mars = new Mars("src/main/resources/marsBot.txt");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load(); // interact with the AnchorPane defined in the FXML as we would have if we created the AnchorPane ourselves inside the Main class.
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Mars - Tasklist Bot");

            MainWindow controller = fxmlLoader.getController();
            controller.setMars(mars); // inject the Mars instance
            controller.displayWelcomeMessage();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

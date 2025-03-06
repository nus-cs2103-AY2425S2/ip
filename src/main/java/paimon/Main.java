package paimon;

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

    private Paimon paimon = new Paimon();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/mainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            // fxmlLoader.<MainWindow>getController().setPaimon(paimon); // inject the Duke instance
            MainWindow mainWindow = fxmlLoader.<MainWindow>getController();
            mainWindow.setPaimon(paimon);
            stage.setTitle("Paimon"); // add the title of the window
            mainWindow.displayWelcomeMessage(); // show the welcome message
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

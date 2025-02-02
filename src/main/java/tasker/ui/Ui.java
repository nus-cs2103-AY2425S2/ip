package tasker.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Controller of the main application UI.
 */
public class Ui extends Application {
    @Override
    public void start(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(Ui.class.getResource("/view/MainWindow.fxml"));
        AnchorPane ap = null;

        try {
            ap = fxmlLoader.load();
        } catch (IOException e) {
            System.err.println("Failed to render main window.");
            System.exit(1);
        }

        Scene scene = new Scene(ap);
        stage.setScene(scene);
        stage.show();
    }
}

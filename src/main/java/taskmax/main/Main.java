package taskmax.main;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import taskmax.ui.MainWindow;

/**
 * The main entry point for Taskmax's GUI.
 */
public class Main extends Application {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private Taskmax taskmax;

    /**
     * Initializes Taskmax with the given file path.
     */
    private Taskmax initializeTaskmax() {
        return new Taskmax("data/tasks.txt"); // You could change this to dynamically get the file path
    }

    @Override
    public void start(Stage stage) {
        try {
            taskmax = initializeTaskmax();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
            AnchorPane root = fxmlLoader.load();

            MainWindow controller = fxmlLoader.getController();
            controller.setTaskmax(taskmax);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Taskmax");
            stage.show();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error loading FXML", e);
            showErrorAlert("Error", "Failed to load the application. Please try again.");
        }
    }

    /**
     * Displays an error alert with the given title and message.
     *
     * @param title The title of the alert.
     * @param message The message to display in the alert.
     */
    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null); // No header text
        alert.setContentText(message);
        alert.showAndWait(); // Display the alert and wait for the user to close it
    }

    public static void main(String[] args) {
        launch(args);
    }
}

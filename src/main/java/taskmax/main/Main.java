package taskmax.main;

import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.stage.Stage;

import taskmax.ui.MainWindow;

/**
 * Main entry point for the Taskmax application. Initializes and displays the main window.
 * AI tools (ChatGPT) were used to add JavaDoc to all methods in this class to enhance code documentation.
 */
public class Main extends Application {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private Taskmax taskmax;

    /**
     * Initializes Taskmax with the given file path.
     *
     * @return A Taskmax instance initialised with the file path "data/tasks.txt".
     */
    private Taskmax initializeTaskmax() {
        return new Taskmax("data/tasks.txt");
    }

    /**
     * Starts the JavaFX application by setting up the main window and scene.
     *
     * @param stage The primary stage for the application.
     */
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
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Launches the JavaFX application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}



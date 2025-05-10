package helix.ui;

import helix.Helix;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Initializes and starts the Helix GUI application.
 */
public class MainApp extends Application {

    private Helix helix = new Helix("data/helix_tasklist.txt");
    /**
     * Starts the Helix application.
     *
     * @param stage the primary stage for the JavaFX application
     */
    @Override
    public void start(Stage stage) {
        try {
            configureStage(stage);
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/view/MainWindow.fxml"));
            AnchorPane root = fxmlLoader.load();
            setupScene(stage, root, fxmlLoader);
            stage.show();
        } catch (Exception e) {
            handleException(e);
        }
    }

    /**
     * Configures the stage properties.
     */
    private void configureStage(Stage stage) {
        stage.setMinHeight(220);
        stage.setMinWidth(417);
        stage.setTitle("Helix - Personal Assistant");
        stage.setOnCloseRequest(event -> handleExit());
    }

    /**
     * Sets up the scene and controller.
     */
    private void setupScene(Stage stage, AnchorPane root, FXMLLoader fxmlLoader) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        MainWindow controller = fxmlLoader.getController();
        controller.setHelix(helix);
        controller.setCloseHandler(stage);
    }

    /**
     * Handles application exit.
     */
    private void handleExit() {
        System.out.println("Closing application...");
        Platform.exit();
    }

    /**
     * Handles exceptions gracefully.
     */
    private void handleException(Exception e) {
        e.printStackTrace();
    }
}

package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tasks.TaskManager;

/**
 * This class handles the main logic for interaction.
 *
 * @author Yashvan
 */
public class Tyrese extends Application {

    private TaskManager taskManager = new TaskManager();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Tyrese.class.getResource("/view/MainWindow.fxml"));
            AnchorPane root = fxmlLoader.load();
            Scene scene = new Scene(root);

            stage.setMinHeight(800);
            stage.setMinWidth(1200);
            stage.setTitle("Tyrese Task Manager");
            stage.setScene(scene);

            MainWindow controller = fxmlLoader.getController();
            controller.setTaskManager(taskManager);

            stage.show();

            taskManager.loadTasks();

        } catch (IOException e) {
            System.out.println(
                    "\t______________________________________________________________________________________\n"
                    + "\t " + e.getMessage()
                    + "\n\t______________________________________________________________________________________\n"
            );
        }
    }
}


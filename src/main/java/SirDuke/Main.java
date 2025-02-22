package SirDuke;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javafx.scene.image.Image;
import SirDuke.controls.MainWindow;

/** Welcome to SirDuke!
 *<p>
 *     Using the chatbot:
 *     - To view the list, use the following command:
 *     list
 *
 *      - To add a To Do to the list, use the following format:
 *     deadline/description
 *
 *      - To add a Deadline to the list, use the following format:
 *     deadline/description/time to be completed by
 *
 *      - To add an Event to the list, use the following format:
 *      event/description/start time/end time
 *
 *      - To mark a task as done, use the following format:
 *      mark/task index
 *
 *      - To unmark a task as done, use the following format:
 *      unmark/task index
 *
 *      - To delete a task, use the following format:
 *      delete/task index
 *
 *      - To close the chatbot, use the following command:
 *      bye
 *</p>
 */


/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private SirDuke sirDuke = new SirDuke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            stage.setTitle("SirDuke");
            Image image = new Image("images/SirDuke.jpeg");
            stage.getIcons().add(image);

            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSirDuke(sirDuke); // inject the SirDuke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


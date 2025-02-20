package gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import tasks.TaskManager;
import tasks.parser.UserCommandParser;

/**
 * Controller for the main GUI.
 *
 * @author Yashvan
 */
public class MainWindow {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/samurai_doge.png"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/gigachad.png"));

    private TaskManager taskManager;

    /**
     * Initialises the scrollPane and userInput placeholder text.
     */
    @FXML
    public void initialize() {
        // Automatically scrolls to the latest message
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        dialogContainer.getChildren().add(
                DialogBox.getTyreseDialog(
                        "__________________________________________________\n"
                        + "\t\t\tIt's ya boi Tyrese!\n"
                        + "\t    Type 'help' to see what I can do! \n"
                        + "__________________________________________________\n", botImage)
        );
    }

    /**
     * Sets the TaskManager instance.
     *
     * @param taskManager The task manager instance.
     */
    public void setTaskManager(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * Handles user input and displays response based on input.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        if (!input.isEmpty() && taskManager != null) {
            boolean[] isRun = { true };
            String response = UserCommandParser.parseCommand(input, taskManager, isRun);

            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getTyreseDialog(response, botImage)
            );

            userInput.clear();
        }

        try {
            taskManager.saveUnmarkedTasks();
        } catch (IOException e) {
            System.out.println(
                    "\t______________________________________________________________________________________\n"
                    + "\t " + e.getMessage()
                    + "\n\t______________________________________________________________________________________\n"
            );
        }
    }
}


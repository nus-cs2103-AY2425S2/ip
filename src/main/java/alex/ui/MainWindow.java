package alex.ui;

import alex.Parser;
import alex.command.Command;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.util.ArrayList;

import alex.Alex;
import alex.Ui;
import alex.task.Task;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane implements Ui {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Alex alex;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/image/DaUser.png"));
    private Image alexImage = new Image(this.getClass().getResourceAsStream("/image/DaAlex.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setAlex(Alex a) {
        alex = a;
    }

    @FXML
    public void printWelcomeMsg() {
        dialogContainer.getChildren().addAll(
                DialogBox.getAlexDialog("Hello! I'm Alex\nWhat can I do for you?", alexImage)
        );
    }

    @FXML
    public void printExitMsg() {
        dialogContainer.getChildren().addAll(
                DialogBox.getAlexDialog("Bye. Hope to see you again soon!", alexImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage)
        );
        userInput.clear();
    }

    @FXML
    public void readCommand() {
        String inputStr = userInput.getText();

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(inputStr, userImage)
        );

        userInput.clear();

        alex.run(inputStr);
    }

    public String getTaskCount(int count) {
        return "Now you have " + count + " tasks in the list.";
    }

    @FXML
    public void addItemResponse(String task, int count) {
        String message = "Got it. I've added this task:\n" + task + "\n" + getTaskCount(count);
        dialogContainer.getChildren().addAll(
                DialogBox.getAlexDialog(message, alexImage)
        );
    }

    @FXML
    public void showErrorMsg(Exception e) {
        dialogContainer.getChildren().addAll(
                DialogBox.getAlexDialog(e.getMessage(), alexImage)
        );
    }

    @FXML
    public void deleteTaskResponse(String task, int count) {
        String message = "Noted, I've removed this task:\n" + task + "\n" + getTaskCount(count);
        dialogContainer.getChildren().addAll(
                DialogBox.getAlexDialog(message, alexImage)
        );
    }

    @FXML
    public void showSearchResult(ArrayList<Task> result) {
        if (result.size() == 0) {
            dialogContainer.getChildren().add(
                    DialogBox.getAlexDialog("Sorry, there are no matching results.", alexImage)
            );
        } else {
            StringBuilder resultMessage = new StringBuilder("Here are the matching tasks in your list:\n");
            int count = 1;
            for (Task task : result) {
                resultMessage.append(count).append(". ").append(task).append("\n");
                count++;
            }
            dialogContainer.getChildren().add(
                    DialogBox.getAlexDialog(resultMessage.toString(), alexImage)
            );
        }
    }

    @FXML
    public void printMsg(String msg) {
        dialogContainer.getChildren().addAll(
                DialogBox.getAlexDialog(msg, alexImage)
        );
    }
}
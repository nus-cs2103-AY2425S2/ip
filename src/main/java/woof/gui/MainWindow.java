package woof.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import woof.Woof;
import woof.task.TaskList;

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

    private Woof woof;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserImage.png"));
    private Image woofImage = new Image(this.getClass().getResourceAsStream("/images/WoofLogo.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Woof instance */
    public void setWoof(Woof w) {
        woof = w;
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

        woof.getResponse(input);
    }

    /**
     * Displays a welcome message when the application starts.
     */
    public void showWelcome() {
        dialogContainer.getChildren().add(
                DialogBox.getWoofDialog("Woof! I'm Woof!\nWhat can I do for you today?", woofImage)
        );
    }

    /**
     * Displays an error message to the user.
     *
     * @param error The error message to display.
     */
    public void showError(String error) {
        dialogContainer.getChildren().add(
                DialogBox.getWoofDialog(error, woofImage)
        );
    }

    /**
     * Displays the list of tasks to the user.
     */
    public void displayTaskList() {
        dialogContainer.getChildren().add(
                DialogBox.getWoofDialog(TaskList.print(), woofImage)
        );
    }

    /**
     * Displays a message confirming that a task has been found.
     *
     * @param find The task that was found.
     */
    public void displayFind(String find) {
        dialogContainer.getChildren().add(
                DialogBox.getWoofDialog("Sniff sniff... Woof! I have found:\n" + find, woofImage)
        );
    }

    /**
     * Displays a message confirming that a task has been added.
     *
     * @param task        The task that was added.
     * @param totalTasks  The total number of tasks after adding the new task.
     */
    public void displayTaskAdded(String task, int totalTasks) {
        dialogContainer.getChildren().add(
                DialogBox.getWoofDialog("Woof! successfully added: " + task
                        + "\nWoof! You have " + totalTasks + " tasks now.", woofImage)
        );
    }

    /**
     * Displays a message confirming that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void displayTaskMarked(String task) {
        dialogContainer.getChildren().add(
                DialogBox.getWoofDialog("Woof! Good Job on completing:\n" + task, woofImage)
        );
    }

    /**
     * Displays a message confirming that a task has been unmarked.
     *
     * @param task The task that was unmarked.
     */
    public void displayTaskUnmarked(String task) {
        dialogContainer.getChildren().add(
                DialogBox.getWoofDialog("Woof! Made a mistake? I have unmarked:\n" + task, woofImage)
        );
    }

    /**
     * Displays a message confirming that a task has been deleted.
     *
     * @param task        The task that was deleted.
     * @param totalTasks  The total number of tasks after deleting the task.
     */
    public void displayTaskDeleted(String task, int totalTasks) {
        dialogContainer.getChildren().add(
                DialogBox.getWoofDialog("Woof! Less Work! I have deleted:\n" + task
                        + "\nWoof! You have " + totalTasks + " tasks now.", woofImage)
        );
    }

    /**
     * Displays a message confirming that all tasks have been cleared.
     */
    public void displayTasksCleared() {
        dialogContainer.getChildren().add(
                DialogBox.getWoofDialog("Woof! You have cleared all tasks!", woofImage)
        );
    }

    /**
     * Displays a goodbye message when the application exits.
     */
    public void displayGoodbye() {
        dialogContainer.getChildren().add(
                DialogBox.getWoofDialog("""
                        Woof! Thank you for using me!
                        Hope to see you again soon!""", woofImage)
        );
    }

    /**
     * Displays a help message to the user.
     */
    public void displayHelp() {
        dialogContainer.getChildren().add(
                DialogBox.getWoofDialog("""
                        Woof! Need some help? Here are the commands you can use:
                        1. todo <description> - Adds a todo task.
                        2. deadline <description> /by <date> - Adds a deadline task.
                        3. event <description> /from <start time> /to <end time> - Adds an event task.
                        4. list - Lists all tasks.
                        5. mark <task number> - Marks a task as done.
                        6. unmark <task number> - Unmarks a task.
                        7. delete <task number> - Deletes a task.
                        8. find <keyword> - Finds tasks with the keyword.
                        9. clear - Clears all tasks.
                        10. help - Shows the list of commands.
                        11. bye - Exits the application.""", woofImage)
        );
    }
}

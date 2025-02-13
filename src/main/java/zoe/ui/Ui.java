package zoe.ui;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lombok.Setter;
import zoe.Zoe;
import zoe.ZoeException;
import zoe.task.Task;
import zoe.task.TaskList;

/**
 * Controller for the main GUI.
 */
public class Ui extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    @SuppressWarnings("unused")
    private Button sendButton;
    @Setter
    private Zoe zoe;

    private final Image userImage;
    private final Image dukeImage;

    /**
     * Constructs a new Ui instance and initializes the user and chatbot images.
     * The images are loaded from the classpath resources. If the images cannot be found,
     * a {@link NullPointerException} is thrown.
     */
    public Ui() {
        userImage = loadImage("/images/DaUser.png");
        dukeImage = loadImage("/images/DaDuke.png");
    }

    /**
     * Loads an image from the specified resource path.
     *
     * @param resourcePath The path to the image resource in the classpath.
     * @return An {@link Image} object representing the loaded image.
     * @throws NullPointerException If the image resource cannot be found at the specified path.
     */
    private Image loadImage(String resourcePath) {
        var imageStream = Objects.requireNonNull(this.getClass().getResourceAsStream(resourcePath));
        return new Image(imageStream);
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        zoe.notify(input);
        userInput.clear();
    }

    /**
     * Displays a response from Zoe (the chatbot) in the dialog container.
     *
     * @param response The message to be displayed in Zoe's dialog box.
     */
    public void reply(String response) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(response, dukeImage));
    }

    /**
     * Displays a welcome message when the chatbot starts.
     */
    public void showWelcome() {
        reply("Hello! I'm Zoe\nWhat can I do for you?\n");
    }

    /**
     * Displays an error message when an exception occurs.
     *
     * @param e The exception containing the error message to be displayed.
     */
    public void showError(ZoeException e) {
        reply(e.getMessage());
    }

    /**
     * Displays a confirmation message after a task is added to the task list.
     *
     * @param taskList The current task list.
     * @param task The task that was added.
     */
    public void showAddedTask(TaskList taskList, Task task) {
        reply(String.format("""
                Got it. I've added this task:
                \t%s
                Now you have %d task(s) in your list.""", task.getFullDescription(), taskList.size()));
    }

    /**
     * Displays a confirmation message after a task is removed from the task list.
     *
     * @param taskList The current task list.
     * @param task The task that was removed.
     */
    public void showRemovedTask(TaskList taskList, Task task) {
        reply(String.format("""
                Noted. I've removed this task:
                \t%s
                Now you have %d task(s) in your list.""", task.getFullDescription(), taskList.size()));
    }

    /**
     * Displays a list of tasks that match a search or filter criteria.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void showList(List<? extends Task> tasks) {
        reply("Here are the matching tasks in your list:\n"
                + IntStream
                .range(0, tasks.size())
                .mapToObj(i -> String.format("%d. %s", i + 1, tasks.get(i).getFullDescription()))
                .collect(Collectors.joining("\n")));
    }

    /**
     * Displays a confirmation message after a task is marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showMarkedTask(Task task) {
        reply(String.format("""
                Nice! I've marked this task as done:
                \t%s""", task.getFullDescription()));
    }

    /**
     * Displays a confirmation message after a task is marked as undone.
     *
     * @param task The task that was marked as undone.
     */
    public void showUnmarkedTask(Task task) {
        reply(String.format("""
                OK, I've marked this task as not done yet:
                \t%s""", task.getFullDescription()));
    }
}


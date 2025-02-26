package Krypto.IO;

import Krypto.Task.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import Krypto.Krypto;
/**
 * Controller for the main GUI.
 */
public class GUI extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Krypto krypto;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image kryptoImage = new Image(this.getClass().getResourceAsStream("/images/krypto.png"));

    private static final String WELCOME_MESSAGE = "Hey! I'm Krypto the superdog. How can I assist you ?";

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Krypto instance */
    public void setKrypto(Krypto k) {
        krypto = k;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Krypto's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().
                addAll(DialogBox.getUserDialog(input, userImage));
        krypto.run(input);
        userInput.clear();
    }

    public void newResponse(String input) {
        assert(kryptoImage != null) : "dukeImage is null.";
        dialogContainer.getChildren().addAll(
                DialogBox.getKryptoDialog(input, kryptoImage)
        );
    }

    public void showWelcome() {
        newResponse(WELCOME_MESSAGE);
    }

    public void showError(Exception e) {
        newResponse(e.toString());
    }

    public void showExit() {
        newResponse("Great talking to you!");
    }

    public void addTaskResponse(Task t, int len) {
        newResponse(String.format("Got it. I've added this task " +
                ":  %s\nNow you have %d tasks in the list.", t, len));
    }

    /**
     * Displays a response after deleting a task.
     *
     * @param t The task that was removed.
     * @param len The current number of tasks in the list.
     */
    public void deleteTaskResponse(Task t, int len) {
        newResponse(String.format("Got it. I've removed this task " +
                ":  %s\nNow you have %d tasks in the list.", t, len));
    }
}
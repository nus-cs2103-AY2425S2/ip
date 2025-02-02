package tasker.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import tasker.Tasker;
import tasker.exception.TaskerException;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    /** Contains command history */
    @FXML
    private ScrollPane scrollPane;
    /** Container for dialog */
    @FXML
    private VBox dialogContainer;
    /** Input field */
    @FXML
    private TextField userInput;
    /** Button to send commands */
    @FXML
    private Button sendButton;
    /** Tasker instance */
    private Tasker tasker;

    /**
     * Controller initializer.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        try {
            tasker = new Tasker();
        } catch (TaskerException e) {
            this.showError(e.getMessage());
        }
    }

    /**
     * Creates a dialog box containing user input, and appends it to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();

        try {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText),
                    DialogBox.getDukeDialog(tasker.respond(userText)));
        } catch (TaskerException e) {
            this.showError(e.getMessage());
        }

        userInput.clear();
    }

    /**
     * Displays an error message on the UI.
     *
     * @param error The error message.
     */
    private void showError(String error) {
        Label label = new Label(error);
        label.setWrapText(true);
        dialogContainer.getChildren().add(label);
    }
}

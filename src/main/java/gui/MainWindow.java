package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import ricky.Ricky;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Ricky ricky;

    private Image userImage =
            new Image(this.getClass().getResourceAsStream("/images/2fc633221d5e434fb74a7a891dc071ce~noop.jpeg"),
                    150, 150, false, false);
    private Image rickyImage =
            new Image(this.getClass().getResourceAsStream("/images/black-and-white-cat-breeds.png"),
                    150, 150, false, false);

    /**
     * Initializes the scroll pane to scroll to the bottom when the dialog container is updated.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Ricky instance */
    public void setRicky(Ricky r) {
        ricky = r;
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(ricky.getResponse(""), rickyImage, "Welcome")
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Ricky's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = ricky.getResponse(input);
        String commandType = ricky.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, rickyImage, commandType)
        );
        ricky.saveTasks();
        userInput.clear();
    }
}

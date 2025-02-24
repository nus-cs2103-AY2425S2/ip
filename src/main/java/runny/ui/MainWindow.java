package runny.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import runny.Runny;

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

    private Runny runny;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/chatbotUser.png"));
    private Image runnyImage = new Image(this.getClass().getResourceAsStream("/images/chatbotRunny.png"));

    /**
     * Initializes the MainWindow.
     * Displays a welcome message to the user.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        displayWelcomeMessage();
    }

    /** Injects the Runny instance */
    public void setRunny(Runny r) {
        runny = r;
    }


    /**
     * Displays a welcome message for the user.
     *
     */
    public void displayWelcomeMessage() {
        dialogContainer.getChildren().add(DialogBox.getRunnyDialog("Hello! I'm Runny, here to help with your "
                + "tasks tracking!\nPlease enter a command.\n"
                + "Here are the available commands:\n"
                + "list, mark, unmark, todo, event, deadline, find, bye", runnyImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        runny.run(input);
        String response = runny.getUi().getOutput();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getRunnyDialog(response, runnyImage)
        );
        userInput.clear();
    }
}

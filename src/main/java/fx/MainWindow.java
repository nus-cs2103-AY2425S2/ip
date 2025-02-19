package fx;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import thoughtbot.ThoughtBot;
import utilities.StringConstants;

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

    private ThoughtBot thoughtBot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/userImage.png"));
    private Image thoughtBotImage = new Image(this.getClass().getResourceAsStream("/images/thoughtBotImage.png"));

    /**
     * Initializes the main window and prints the greeting
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        dialogContainer.getChildren().addAll(
                DialogBox.getTbDialog(StringConstants.GREETING, thoughtBotImage)
        );
    }

    /**
     * Injects the ThoughtBot instance
     * @param t ThoughtBot object to e injected
     */
    public void setThoughtBot(ThoughtBot t) {
        thoughtBot = t;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing ThoughtBot's reply and then appends
     * them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = thoughtBot.getResponse(input);

        if (response.equals("bye given")) {
            Platform.exit();
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getTbDialog(response, thoughtBotImage)
            );
            userInput.clear();
        }
    }
}

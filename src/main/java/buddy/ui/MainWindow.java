package buddy.ui;

import buddy.Buddy;
import buddy.display.Display;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

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

    private Buddy buddy;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/nobita.png"));
    private Image buddyImage = new Image(this.getClass().getResourceAsStream("/images/doremon.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Duke instance
     */
    public void setBuddy(Buddy buddy) {
        this.buddy = buddy;

        String taskList = this.buddy.getResponse("list");

        dialogContainer.getChildren().addAll(
                DialogBox.getBuddyDialog(taskList, buddyImage)
        );
    }

    /**
     * Sends greet message.
     */
    public void sendGreetMessage() {
        dialogContainer.getChildren().addAll(
                DialogBox.getBuddyDialog(Display.greet(), buddyImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = buddy.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBuddyDialog(response, buddyImage)
        );
        userInput.clear();

        if (input.equals("bye")) {
            delayAndCloseWindow();
        }
    }

    private void delayAndCloseWindow() {
        PauseTransition delay = new PauseTransition(Duration.seconds(1)); // 2-second delay
        delay.setOnFinished(event -> closeWindow());
        delay.play();
    }

    private void closeWindow() {
        Stage stage = (Stage) userInput.getScene().getWindow();
        stage.close();
    }
}


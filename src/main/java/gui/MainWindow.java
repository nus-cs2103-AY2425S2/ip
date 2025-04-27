package gui;

import dew.Dew;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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

    private Dew dew;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dewImage = new Image(this.getClass().getResourceAsStream("/images/DaDew.png"));
    private Image backgroundImage = new Image(this.getClass().getResourceAsStream("/images/Background.png"));

    /**
     * Initializes the main GUI window.
     * Binds the scroll pane to automatically scroll down as new messages are added.
     * Displays a welcome message when the application starts.
     */
    @FXML
    public void initialize() {

        String startDialogue = " Hello! I'm Dew\n"
                + " What can I do for you?\n";

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        StringBuilder sb = new StringBuilder();
        sb.append(startDialogue);
        DialogBox welcomeMessage = DialogBox.getDukeDialog(sb.toString(), dewImage);
        dialogContainer.getChildren().add(welcomeMessage);
    }

    /** Injects the Duke instance */
    public void setDew(Dew d) {
        dew = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = dew.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dewImage)
        );
        if (input.trim().equals("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> System.exit(0));
            delay.play();
        }
        userInput.clear();
    }
}

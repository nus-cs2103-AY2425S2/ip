package scooby.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.application.Platform;
import javafx.util.Duration;
import javafx.animation.PauseTransition;
import scooby.ui.Scooby;

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

    private Scooby scooby;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image scoobyImage = new Image(this.getClass().getResourceAsStream("/images/Scooby.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Scooby instance */
    public void setScooby(Scooby scooby) {
        this.scooby = scooby;  // Fix: Assign the correct instance
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Scooby's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = scooby.getResponse(input);

        // Check if the input is "bye"
        if (input.equalsIgnoreCase("bye")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getScoobyDialog(response, scoobyImage)
            );

            // Delay before closing the application
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> Platform.exit());
            pause.play();

            userInput.clear();
            return;
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getScoobyDialog(response, scoobyImage)
        );
        userInput.clear();
    }
}

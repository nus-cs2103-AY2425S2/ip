package app;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import seb.ui.SebException;
import seb.ui.Sebastian;

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

    private Sebastian sebastian;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/userphoto.jpeg"));
    private Image sebImage = new Image(this.getClass().getResourceAsStream("/images/sebphoto.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        if (sebastian != null) {
            dialogContainer.getChildren().add(
                    DialogBox.getSebDialog(sebastian.getWelcomeMessage(), sebImage));
        }
    }

    /** Injects the Seb instance */
    public void setSeb(Sebastian seb) {
        this.sebastian = seb;
        dialogContainer.getChildren().add(
                DialogBox.getSebDialog(sebastian.getWelcomeMessage(), sebImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other
     * containing Seb's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws SebException {
        String input = userInput.getText();
        String response = sebastian.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSebDialog(response, sebImage)
        );
        userInput.clear();

        if (input.equalsIgnoreCase("bye")) {
            userInput.setDisable(true);
            sendButton.setDisable(true);
            dialogContainer.getChildren().add(DialogBox.getSebDialog(
                    "Session has ended queen :( Restart the program so we can talk again.", sebImage));
        }
    }
}


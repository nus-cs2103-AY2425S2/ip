package main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import ui.Ui;

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

    private static final String EXIT_COMMAND = "bye";
    private static final String GREETING_MESSAGE = "Hi I'm Samantha!";
    private Samantha samantha;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Phoneix.jpg"));
    private Image samanthaImage = new Image(this.getClass().getResourceAsStream("/images/Samantha.jpg"));

    /**
     * Initializes the GUI.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the samantha instance
     */
    public void setSamantha(Samantha d) {
        samantha = d;
        greeting();
    }

    /**
     * Displays the greeting message.
     */
    private void greeting() {
        String greetingMessage = GREETING_MESSAGE;
        dialogContainer.getChildren().add(
                DialogBox.getSamanthaDialog(greetingMessage, samanthaImage)
        );
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Samantha's reply and then appends them
     * to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = samantha.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSamanthaDialog(response, samanthaImage)
        );
        userInput.clear();

        if (input.equalsIgnoreCase(EXIT_COMMAND)) {
            System.exit(0);
        }
    }
}

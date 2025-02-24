package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import julie.Julie;

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

    private Julie julie;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/userImage.jpg"));
    private Image julieImage = new Image(this.getClass().getResourceAsStream("/images/julieImage.jpg"));

    /**
     * Initializes the main window of the chatbot's GUI.
     * Binds the scroll pane's vertical position to the height of the dialog container
     * so that new messages are always visible. Also displays the chatbot's welcome
     * message when the GUI starts.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Julie instance */
    public void setJulie(Julie j) {
        julie = j;

        String welcomeMessage = julie.getWelcomeMessage();
        dialogContainer.getChildren().add(DialogBox.getJulieDialog(welcomeMessage, julieImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Julie's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = julie.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getJulieDialog(response, julieImage)
        );

        userInput.clear();

        if (input.trim().equalsIgnoreCase("bye")) {
            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                    System.exit(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }


}

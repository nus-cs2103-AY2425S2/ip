package godbot.ui;

import godbot.GodBot;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI window of GodBot.
 * Handles user input and displays messages in the dialog container.
 */
public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private GodBot godBot;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/godbotUser.jpg"));
    private Image godBotImage = new Image(this.getClass().getResourceAsStream("/images/godbotBot.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the GodBot instance for handling input.
     *
     * @param godBot The GodBot instance.
     */
    public void setGodBot(GodBot godBot) {
        this.godBot = godBot;
        String welcomeMessage = godBot.getUi().showWelcomeMessage();
        dialogContainer.getChildren().add(
            DialogBox.getBotDialog(welcomeMessage, godBotImage)
        );
    }

    /**
     * Handles user input by sending it to GodBot and displaying the response.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = godBot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBotDialog(response, godBotImage)
        );
        userInput.clear();
        if (input.equalsIgnoreCase("bye")) {
            Platform.exit();
        }
    }
}


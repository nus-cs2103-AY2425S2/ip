package ferb.gui;

import ferb.Ferb;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


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

    private Ferb ferb;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image ferbImage = new Image(this.getClass().getResourceAsStream("/images/DaFerb.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setFerb(Ferb f) {
        ferb = f;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = ferb.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getFerbDialog(response, ferbImage)
        );
        userInput.clear();
    }

    /**
     * Displays a welcome message.
     */
    public void showWelcomeMessage() {
        String welcomeMessage = "Hello! I'm Ferb\nWhat can I do for you?";
        dialogContainer.getChildren().add(DialogBox.getFerbDialog(welcomeMessage, ferbImage));
    }
}


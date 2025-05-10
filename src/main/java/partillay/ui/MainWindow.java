package partillay.ui;

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

    private Partillay partillay;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image partillayImage = new Image(this.getClass().getResourceAsStream("/images/partillay.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Partillay instance */
    public void setPartillay(Partillay partillay) {
        this.partillay = partillay;
        String greetingMessage = partillay.ui.getWelcomeMessage();
        dialogContainer.getChildren().add(DialogBox.getPartillayDialog(greetingMessage, partillayImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Partillay's
     * reply and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = partillay.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getPartillayDialog(response, partillayImage)
        );
        userInput.clear();
        if (input.trim().equals("bye") || input.trim().equals("b")) {
            javafx.application.Platform.exit();
        }
    }
}

package parakeet.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import parakeet.Parakeet;

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

    private Parakeet parakeet;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image parakeetImage = new Image(this.getClass().getResourceAsStream("/images/DaParakeet.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren()
                .addAll(DialogBox
                        .getParakeetDialog("Hello! I'm Parakeet. \n What can I do for you?",
                                parakeetImage));
    }

    /** Injects the Duke instance */
    public void setDuke(Parakeet p) {
        parakeet = p;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String parakeetText = parakeet.getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getParakeetDialog(parakeetText, parakeetImage)
        );

        if ("bye".equalsIgnoreCase(userText.trim())) {
            System.out.println("Exit the program...");
            Platform.exit();
        }
        userInput.clear();
    }
}

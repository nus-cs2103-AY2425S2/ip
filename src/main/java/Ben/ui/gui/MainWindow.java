package ben.ui.gui;

import ben.ui.Ben;
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

    private Ben ben;
    private String intro = "Hello! I'm Ben\nWhat can I do for you?";
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaDiamond.png"));
    private Image omniImage = new Image(this.getClass().getResourceAsStream("/images/Omnitrix.png"));


    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getOmiDialog(intro, omniImage)
        );
    }

    /** Injects the Ben instance */
    public void setBen(Ben b) {
        ben = b;
    }

    /**
     * Handles "bye" command when the window is closing.
     */
    public void bye() {
        String response = ben.getResponse("bye");
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(response, omniImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = ben.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getOmiDialog(response, omniImage)
        );
        userInput.clear();
    }
}
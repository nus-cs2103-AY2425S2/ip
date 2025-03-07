package app;

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

    private Daiyan daiyan;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Commander.png"));
    private Image daiyanImage = new Image(this.getClass().getResourceAsStream("/images/Daiyan.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setDaiyan(Daiyan d) {
        daiyan = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = daiyan.getResponse(input);
        String commandType = daiyan.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDaiyanDialog(response, daiyanImage, commandType)
        );
        userInput.clear();
    }

    public void showWelcomeMessage() {
        String response = daiyan.getStartUpResponse();
        dialogContainer.getChildren().add(DialogBox.getDaiyanDialog(response, daiyanImage, "Start"));
    }
}

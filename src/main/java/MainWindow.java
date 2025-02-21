//Solution below adapted from https://se-education.org/guides/tutorials/javaFx.html

import avocado.Avocado;
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

    private Avocado avo;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/gira.jpg"));
    private Image avoImage = new Image(this.getClass().getResourceAsStream("/images/avo.jpg"));

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Avocado instance */
    public void setAvo(Avocado a) {
        avo = a;
    }

    
    @FXML
    public void showWelcome() {
        String alltext = avo.showWelcome();
        dialogContainer.getChildren().add(
                DialogBox.getAvoDialog(alltext, avoImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Avocado's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = avo.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAvoDialog(response, avoImage)
        );
        userInput.clear();
    }
    
}

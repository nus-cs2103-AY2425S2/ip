package nyanko.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import nyanko.Nyanko;

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

    private Nyanko nyanko;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserNatsume.png"));
    private Image nyankoImage = new Image(this.getClass().getResourceAsStream("/images/Nyanko.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects an instance of Nyanko into the GUI.
     *
     * @param n The Nyanko instance.
     */
    public void setNyanko(Nyanko n) {
        this.nyanko = n;
        // Ensure welcome message appears in GUI at startup
        dialogContainer.getChildren().add(
                DialogBox.getNyankoDialog(nyanko.getResponse(""), nyankoImage)
        );
    }

    /**
     * Handles user input and appends responses to the dialog container.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = nyanko.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getNyankoDialog(response, nyankoImage)
        );
        userInput.clear();
    }
}

package prophet.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import prophet.Prophet;

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

    private Prophet prophet;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/you.jpeg"));
    private Image prophetImage = new Image(this.getClass().getResourceAsStream("/images/prophet.jpeg"));

    /**
     * Initialises the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getProphetDialog(Ui.greetHello(), prophetImage)
        );
    }

    /** Injects the Prophet instance. */
    public void setProphet(Prophet d) {
        prophet = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Prophet's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = prophet.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getProphetDialog(response, prophetImage)
        );
        userInput.clear();
    }
}

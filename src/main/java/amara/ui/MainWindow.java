package amara.ui;

import javafx.application.Platform;
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

    private Amara amara;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/pikachu.jpg"));
    private Image amaraImage = new Image(this.getClass().getResourceAsStream("/images/eevee.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Amara instance */
    public void setAmara(Amara amara) {
        this.amara = amara;
        // To set the initialization message into the chatbox
        dialogContainer.getChildren().add(DialogBox.getAmaraDialog(amara.greet(), amaraImage));
    }

    /**
     * Creates two dialog boxes, one taking the user's input and letting
     * {@link Amara} to process the input and return a string. Two dialog boxes
     * are added to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = amara.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAmaraDialog(response, amaraImage)
        );
        userInput.clear();
        if (input.toLowerCase().equals("bye")) {
            Platform.exit();
        }
    }
}

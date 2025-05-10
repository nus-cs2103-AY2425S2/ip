package nickiminaj;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import nickiminaj.Ui;
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

    private NickiMinaj nickiMinaj;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/barb.png"));
    private Image nickiMinajImage = new Image(this.getClass().getResourceAsStream("/images/nickiminaj.png"));

    @FXML
    public void initialize() {
        assert scrollPane != null : "Error: scrollPane is not initialized!";
        assert dialogContainer != null : "Error: dialogContainer is not initialized!";
        assert userInput != null : "Error: userInput is not initialized!";
        assert sendButton != null : "Error: sendButton is not initialized!";

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setNickiMinaj(NickiMinaj nm) {
        nickiMinaj = nm;
        showWelcomeMessage();
    }

    private void showWelcomeMessage() {
        String welcomeMessage = "Hello! I'm the Queen of Rap rrr.\n whatchu wanna do, Barb?" ;
        dialogContainer.getChildren().add(DialogBox.getNickiMinajDialog(welcomeMessage, nickiMinajImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        assert userInput != null : "Error: userInput field is not initialized!";
        assert dialogContainer != null : "Error: dialogContainer is not initialized!";
        assert nickiMinaj != null : "Error: NickiMinaj instance is not initialized!";

        String input = userInput.getText();
        String response = nickiMinaj.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getNickiMinajDialog(response, nickiMinajImage)
        );
        userInput.clear();
    }
}

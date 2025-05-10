package laffy.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import laffy.Laffy;
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

    private Laffy laffy;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image laffyImage = new Image(this.getClass().getResourceAsStream("/images/laffy.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Laffy instance */
    public void setLaffy(Laffy d) {
        laffy = d;
        dialogContainer.getChildren().add(
                DialogBox.getLaffyDialog(laffy.greet(), laffyImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Laffy's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = laffy.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getLaffyDialog(response, laffyImage)
        );
        userInput.clear();
        if (input.equals("bye")) {
            System.exit(0);
        }
    }
}

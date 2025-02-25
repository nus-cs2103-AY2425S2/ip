package mars.ui;

import mars.Mars;
import mars.ui.DialogBox;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

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

    private Mars mars;

    private Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaUser.png")));
    private Image marsImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaDuke.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Mars instance */
    public void setMars(Mars d) {
        mars = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Mars's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = mars.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMarsDialog(response, marsImage)
        );
        userInput.clear();
    }

    public void displayWelcomeMessage() {
        String userGuide = mars.getUserGuide();
        String welcomeMessage = mars.getWelcomeMessage();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userGuide, userImage),
                DialogBox.getMarsDialog(welcomeMessage, marsImage)
        );
    }
}

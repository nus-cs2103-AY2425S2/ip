package wizt.ui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


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

    private WizT wizt;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/dwight.jpg"));
    private Image wiztImage = new Image(this.getClass().getResourceAsStream("/images/jim.jpg"));

    /**
     * Creates a starting conversation
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String defaultMessage = "Hello! I am WizT. How may i assist you today my dear manager?";
        dialogContainer.getChildren().add(DialogBox.getWiztDialog(defaultMessage, wiztImage));
    }

    /** Injects the Duke instance */
    public void setWizT(WizT w) {
        wizt = w;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @SuppressWarnings("checkstyle:WhitespaceAround")
    @FXML
    private void handleUserInput() throws WizTException {
        String input = userInput.getText();
        String response = wizt.getResponse(input);

        if (input.isEmpty()) {
            return;
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getWiztDialog(response, wiztImage)
        );
        userInput.clear();
        if (input.equalsIgnoreCase("bye")) {
            wizt.writeToFile();
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> {
                Platform.exit();
            });
            pause.play();
        }
    }
}

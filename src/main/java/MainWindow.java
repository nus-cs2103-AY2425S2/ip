import bobandsteve.BobAndSteve;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
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

    private BobAndSteve bobAndSteve;

    private Image steveImage = new Image(this.getClass().getResourceAsStream("/images/steve.png"));
    private Image bobImage = new Image(this.getClass().getResourceAsStream("/images/bob.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Bob and steve instance */
    public void setBobAndSteve(BobAndSteve bobAndSteve) {
        this.bobAndSteve = bobAndSteve;
        welcome();
    }

    private void welcome() {
        String welcome = "Welcome, I am Bob and you must be Steve! How can I help you? "
                + "Enter 'help' so I can assist you.";
        dialogContainer.getChildren().add(DialogBox.getBobDialog(welcome, bobImage, "bob-chat"));
    }

    private void disableInput() {
        userInput.setDisable(true);
        sendButton.setDisable(true);
    }

    private void fadeAndClose(Node node) {
        FadeTransition fade = new FadeTransition(Duration.seconds(1), node);
        fade.setFromValue(1.0);
        fade.setToValue(0.0);
        fade.setOnFinished(event -> {
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(e -> Platform.exit());
            delay.play();
        });
        fade.play();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert input != null : "Expected user input should not be null";
        if (input.isEmpty()) {
            return;
        }
        String response = bobAndSteve.getResponse(input);
        assert response != null : "Expected response from user input should not be null";
        String commandType = bobAndSteve.getCommandType();
        assert commandType != null : "Expected a command type from user response should not be null";
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, steveImage, "steve-chat"),
                DialogBox.getBobDialog(response, bobImage, "bob-chat")
        );
        if ("bye".equals(input.trim())) {
            fadeAndClose(this);
            disableInput();
        }
        userInput.clear();
    }
}

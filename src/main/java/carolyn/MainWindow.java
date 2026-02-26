package carolyn;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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

    private Carolyn c;
    private Stage stage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image ccImage = new Image(this.getClass().getResourceAsStream("/images/bot.png"));


    /** Initializes the window */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setCarolyn(Carolyn c, Stage stage) {
        this.c = c;
        this.stage = stage;
        this.stage.setTitle("Carolyn Chatbot");
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog("Hello!", ccImage));
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = c.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, ccImage)
        );

        userInput.clear();

        if (input.equals("bye")) {
            stage.close();
        }
    }
}

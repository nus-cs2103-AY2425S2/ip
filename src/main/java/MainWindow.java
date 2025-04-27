import a.A;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 *
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

    private A a;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }


    public void setDuke(A a) {
        this.a = a;
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog("Hello! I'm A\nWhat can I do for you?", dukeImage));
    }


    @FXML
    private void handleUserInput() {
        a.setOutputConsumer(message -> dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(message, dukeImage)));
        String input = userInput.getText();
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage));
        a.handleInput(input);
        userInput.clear();
    }
}

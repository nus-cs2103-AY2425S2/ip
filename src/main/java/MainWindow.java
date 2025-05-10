import gigi.Gigi;
import javafx.fxml.FXML;
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

    private Gigi gigi;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image gigiImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Gigi instance */
    public void setGigi(Gigi g) {
        gigi = g;
        showWelcome();
    }

    private void showWelcome() {
        String welcome = gigi.getWelcome();
        dialogContainer.getChildren().add(
                DialogBox.getGigiDialog(welcome, gigiImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Gigi's reply and
     * then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = gigi.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getGigiDialog(response, gigiImage)
        );
        userInput.clear();
    }
}

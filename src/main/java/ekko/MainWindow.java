package ekko;
import ekko.ui.Ekko;
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

    private Ekko ekko;


    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/dino.png"));
    private Image ekkoImage = new Image(this.getClass().getResourceAsStream("/images/ekko.png"));

    /**
     * Initialise the interface, making sure that the scroll pane can be scrolled down
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.setOnScroll(event -> {
            double deltaY = event.getDeltaY(); // Get scroll amount
            double height = scrollPane.getContent().getBoundsInLocal().getHeight();
            double vValue = scrollPane.getVvalue();

            // Adjust scrolling speed
            scrollPane.setVvalue(vValue - deltaY / height);
        });
    }

    /**
     * Injects the Ekko instance
     */
    public void setEkko(Ekko e) {
        ekko = e;
    }
    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert input != null : "User input cannot be null.";
        String response = ekko.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getEkkoDialog(response, ekkoImage)
        );
        // Ensure the ScrollPane scrolls to the bottom
        Platform.runLater(() -> {
            scrollPane.vvalueProperty().unbind();
            scrollPane.setVvalue(1.0);
        });
        userInput.clear();
    }

}

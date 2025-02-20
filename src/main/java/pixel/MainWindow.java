package pixel;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pixel.util.PixelException;

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

    private Pixel pixel;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image pixelImage = new Image(this.getClass().getResourceAsStream("/images/robot-idle.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Pixel instance */
    public void setPixel(Pixel p) {
        pixel = p;
        String response = pixel.init();
        dialogContainer.getChildren().add(DialogBox.getPixelDialog(response, pixelImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Pixel's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String response = null;
        String input = userInput.getText();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        try {
            response = pixel.getResponse(input);
            dialogContainer.getChildren().addAll(DialogBox.getPixelDialog(response, pixelImage));
            if (response.equals(" Goodbye. Hope to see you again soon!")) {
                closeStage();
            }
        } catch (PixelException e) {
            dialogContainer.getChildren().add(DialogBox.getPixelExceptionDialog(e.getMessage(), pixelImage));
        } finally {
            userInput.clear();
        }
    }

    /**
     * Closes the Stage, to terminate the Pixel application.
     */
    private void closeStage() {
        Stage stage = (Stage) userInput.getScene().getWindow();
        stage.close();
    }
}

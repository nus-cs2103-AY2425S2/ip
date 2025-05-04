package softess;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

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

    private Softess softess;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/batman.png"));
    private Image softessImage = new Image(this.getClass().getResourceAsStream("/images/mrbean.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Softess instance */
    public void setSoftess(Softess s) {
        this.softess = s;
    }

    @FXML
    private void handleUserInput() throws IOException {
        String input = userInput.getText();
        String response = this.softess.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSoftessDialog(response, softessImage)
        );
        userInput.clear();
        this.softess.updateData();
    }
}

package baymax.ui;

import baymax.Baymax;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    @FXML
    private Button sendButton;

    private Baymax baymax;
    private Image userImage = new Image(MainWindow.class.getResource("/images/user.png").toString());
    private Image baymaxImage = new Image(MainWindow.class.getResource("/images/baymax.png").toString());


    /**
     * Initializes the GUI. Sets up scrolling behavior and loads Baymax's greeting.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // Initialize Baymax instance
        baymax = new Baymax();

        // Display Baymax's greeting at startup
        dialogContainer.getChildren().add(
                DialogBox.getBaymaxDialog(baymax.getGreeting(), baymaxImage)
        );
    }

    /** Injects the Baymax instance */
    public void setBaymax(Baymax baymax) {
        this.baymax = baymax;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws IOException {
        String input = userInput.getText();
        String response = null;
        try {
            response = baymax.getResponse(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBaymaxDialog(response, baymaxImage)
        );
        userInput.clear();
    }
}

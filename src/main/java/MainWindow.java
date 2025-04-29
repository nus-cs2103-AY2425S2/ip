import doot.Doot;
import doot.InvalidFormatException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.IOException;
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

    private Doot doot;

    private final Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaUser.png")));
    private final Image dootImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaDuke.png")));
    private final Image errorImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaAngry.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setDoot(Doot d) {
        doot = d;
        try {
            doot = Doot.createDoot();
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(doot.getIntro(), dootImage),
                    DialogBox.getDukeDialog(doot.getResponse("list"), dootImage)
            );
        } catch (InvalidFormatException | IOException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(e.getMessage(), dootImage)
            );
        }
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        try {
            String response = doot.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dootImage)
            );
            if (response.equals("Remember to take it easy!")) {
                Platform.exit();
            }
        } catch (IOException|InvalidFormatException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getErrorDialog(e.getMessage(), errorImage)
            );
        }
        userInput.clear();
    }
}
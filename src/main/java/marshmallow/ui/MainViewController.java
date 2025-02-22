package marshmallow.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import marshmallow.Marshmallow;
import marshmallow.MarshmallowDelegate;
import marshmallow.commands.Command;

/**
 * Controller for the main GUI.
 */
public class MainViewController extends AnchorPane implements MarshmallowDelegate {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Marshmallow marshmallow;

    private Image userImage = new Image(getClass().getResourceAsStream("/images/user.png"));
    private Image marshmallowImage = new Image(getClass().getResourceAsStream("/images/marshmallow.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setMarshmallow(Marshmallow marshmallow) {
        this.marshmallow = marshmallow;
    }

    @Override
    public void marshmallowRespond(String response) {
        dialogContainer.getChildren().add(
            new DialogBox(response, marshmallowImage)
        );
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();

        if (input.isEmpty()) {
            return;
        }

        dialogContainer.getChildren().add(
            new DialogBox(input, userImage, DialogBoxAlignment.LEFT)
        );

        Command command = marshmallow.issue(input);
        if (command != null && command.isExit()) {
            Platform.exit();
        }

        userInput.clear();
    }
}

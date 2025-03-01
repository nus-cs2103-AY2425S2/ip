package bebop.ui;

import java.io.IOException;

import bebop.command.Command;
import bebop.exception.BebopException;
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

    private Bebop bebop;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/knight.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/king.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Bebop instance */
    public void setBebop(Bebop b) {
        bebop = b;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws BebopException, IOException {
        String input = userInput.getText();
        Command c = bebop.getParser().parse(input);
        String output = c.execute(bebop.getTaskList(), bebop.getUi(), bebop.getStorage());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBebopDialog(output, dukeImage)
        );
        userInput.clear();
        if (output.equals("Have a nice day :D, see you soon!")) {
            bebop.getStorage().deload(bebop.getTaskList());
            Platform.exit();
            System.exit(0);
        }
    }
}

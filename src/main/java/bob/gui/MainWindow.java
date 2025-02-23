package bob.gui;

import bob.command.ExitCommand;
import bob.core.Bob;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    private static final String WELCOME_MESSAGE = "Hello, I'm Bob. How can I help you today?";

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;

    /**
     * Method triggered by 'ENTER' keypress or Send button)
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert input != "" : "Input should not be empty";

        // Do nothing if input is empty
        if (input.isBlank()) {
            return;
        }

        String response = Bob.processUserInput(input);
        dialogContainer.getChildren().add(DialogBox.getUserDialogBox(input));

        // exit program if ExitCommand is called
        // note: ExitCommand gives blank response
        if (response.isBlank()) {
            dialogContainer.getChildren().add(DialogBox.getBobDialogBox(ExitCommand.EXIT_MESSAGE));

            new Thread(() -> {
                try {
                    Thread.sleep(500); // Delay for 0.5s before exit
                    System.exit(0); // Exit the program after showing the message
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

        // if all is good, add Bob's response
        // note: this else block is needed to prevent adding a blank dialog box
        } else {
            dialogContainer.getChildren().add(DialogBox.getBobDialogBox(response));
        }

        userInput.clear();
    }

    /**
     * Note that method is auto-called,
     * We are doing additional setup here.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty()); // Auto-scroll to the bottom
        dialogContainer.getChildren().add(DialogBox.getBobDialogBox(WELCOME_MESSAGE)); // Starting message
    }
}

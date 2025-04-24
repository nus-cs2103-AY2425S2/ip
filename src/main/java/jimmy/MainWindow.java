package jimmy;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import jimmy.commands.Command;

/**
 * Controller for the main GUI of the Jimmy chatbot.
 * This class manages user interactions, including displaying messages
 * and handling user input.
 */
public class MainWindow {

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    @FXML
    private ScrollPane scrollPane;

    private Jimmy jimmy;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image jimmyImage = new Image(this.getClass().getResourceAsStream("/images/jimmy.png"));

    /**
     * Initializes the JavaFX components and sets up event listeners.
     * This method is automatically called when the FXML file is loaded.
     */
    @FXML
    public void initialize() {
        userImage = new Image(getClass().getResource("/images/user.png").toExternalForm());
        jimmyImage = new Image(getClass().getResource("/images/jimmy.png").toExternalForm());

        // Display the initial greeting from Jimmy.
        dialogContainer.getChildren().add(DialogBox.getJimmyDialog(
            "Hello! I'm Jimmy. How can I help you?", jimmyImage));

        // Auto-scroll to the latest message when new messages are added.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Sets the Jimmy chatbot instance.
     *
     * @param jimmy The Jimmy chatbot instance to be used in the application.
     */
    public void setJimmy(Jimmy jimmy) {
        this.jimmy = jimmy;
    }

    /**
     * Handles the user input when the Enter key is pressed or when the Send button is clicked.
     * It appends the user's message to the chat, processes it using Jimmy, and then appends
     * Jimmy's response to the chat.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        DialogBox userDialog = DialogBox.getUserDialog(input, userImage);
        dialogContainer.getChildren().add(userDialog);

        try {
            Command command = Parser.parse(input);
            String response = command.execute(jimmy.getTasks(), new Ui(new TextArea()), jimmy.getStorage());
            DialogBox jimmyDialog = DialogBox.getJimmyDialog(response, jimmyImage);
            dialogContainer.getChildren().add(jimmyDialog);
            if (command.isExit()) {
                System.exit(0);
            }
        } catch (JimmyException e) {
            dialogContainer.getChildren().add(DialogBox.getJimmyDialog("Error: " + e.getMessage(), jimmyImage));
        }
        userInput.clear();
    }
}

package teddy;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

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

    private Teddy teddy;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/laughingdog.png"));
    private Image teddyImage = new Image(this.getClass().getResourceAsStream("/images/teddy.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Duke instance
     */
    public void setTeddy(Teddy t) {
        teddy = t;
        showGreeting();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws TeddyException {
        String input = userInput.getText();
        if (input.isBlank()) {
            return; // Prevents empty input processing
        }
        String response = teddy.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTeddyDialog(response, teddyImage) // Update to Teddy's image
        );
        userInput.clear();

        if (response.equals("Bye! Hope to see you again soon!")) {
            exitWithDelay(2);
        }
    }

    private void showGreeting() {
        String greetingMessage = "🐻 Hello! I'm Teddy, your friendly task assistant! 📝\n" +
                "What can I do for you today?\n\n" +
                "💡 Here are some things I can help you with:\n" +
                "━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                "📜 **Task Management:**\n" +
                "  🔹 `list` - View all your tasks\n" +
                "  🔹 `todo <description>` - Add a new ToDo task\n" +
                "  🔹 `deadline <description> /by <date>` - Add a Deadline task\n" +
                "  🔹 `event <description> /from <start> /to <end>` - Schedule an Event\n\n" +
                "✅ **Task Updates:**\n" +
                "  🔹 `mark <task number>` - Mark a task as done\n" +
                "  🔹 `unmark <task number>` - Unmark a completed task\n" +
                "  🔹 `delete <task number>` - Remove a task from your list\n\n" +
                "🔍 **Find Tasks:**\n" +
                "  🔹 `find <keyword>` - Search for tasks containing a specific keyword\n\n" +
                "🚪 **Exit:**\n" +
                "  🔹 `bye` - Close the application\n" +
                "━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                "✨ I'm ready to help you stay organized! Type a command to get started! 🎯";

        dialogContainer.getChildren().add(
                DialogBox.getTeddyDialog(greetingMessage, teddyImage)
        );
    }


    private void exitWithDelay(int seconds) {
        PauseTransition delay = new PauseTransition(Duration.seconds(seconds));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }

    @FXML
    private void handleMouseEnter() {
        sendButton.setStyle("-fx-background-color: #45a049; -fx-background-radius: 20px;");
    }

    @FXML
    private void handleMouseExit() {
        sendButton.setStyle("-fx-background-color: #4CAF50; -fx-background-radius: 20px;");
    }

}
package jimmy;

import javafx.scene.control.TextArea;
/**
 * The Ui class handles all interactions with the user.
 * It provides methods to display messages, errors, and receive input from the user.
 */
public class Ui {
    private TextArea chatArea;

    /**
     * Constructs a {@code Ui} instance with the specified chat area.
     *
     * @param chatArea the {@code TextArea} component where messages will be displayed.
     */
    public Ui(TextArea chatArea) {
        this.chatArea = chatArea;
    }

    /**
     * Displays a message in the chat area with the "Jimmy:" prefix.
     *
     * @param message the message to be displayed.
     */
    public void showMessage(String message) {
        if (chatArea != null) {
            chatArea.appendText("Jimmy: " + message + "\n");
        }
    }
}

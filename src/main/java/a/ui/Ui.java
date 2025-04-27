package a.ui;
import java.util.function.Consumer;

/**
 * Handles all user interactions with the chatbot.
 * It manages user input and displays messages.
 */
public class Ui {

    private Consumer<String> outputConsumer;

    /**
     * Constructs a new Ui instance and initializes the scanner.
     */
    public Ui() {
        this.outputConsumer = System.out::println;;
    }

    public void setOutputConsumer(Consumer<String> outputConsumer) {
        this.outputConsumer = outputConsumer;
    }
    /**
     * Displays a welcome message to the user.
     */
    public void welcome() {
        outputConsumer.accept("Hello! I'm A");
        outputConsumer.accept("What can I do for you?\n");
    }

    /**
     * Displays a message to the user.
     *
     * @param message The message to be displayed.
     */
    public void showMessage(String message) {
        outputConsumer.accept(message);
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message to be displayed.
     */
    public void showError(String errorMessage) {
        outputConsumer.accept("Error: " + errorMessage);
    }

    /**
     * Displays a goodbye message and closes the scanner.
     *
     * @return
     */
    public void bye() {
        outputConsumer.accept("Bye. Hope to see you again soon!");
    }
}

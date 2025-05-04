package softess;

/**
 * Handles user interactions by displaying messages to the console.
 * This class provides methods to display welcome, goodbye, and error messages
 * to enhance user experience.
 *
 * @author Hrishikesh Sathyian
 */
public class UserInterface {

    /**
     * Constructs a new {@code UserInterface} instance.
     */
    public UserInterface() {}

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Softess");
        System.out.println("What can I do for you?\n");
    }

    /**
     * Displays a goodbye message when the user exits the application.
     */
    public String showGoodByeMessage() {
        return "Bye. Hope to see you again soon noob!";
    }

    /**
     * Displays an error message to the user.
     *
     * @param message the error message to be displayed
     */
    public void showErrorMessage(String message) {
        String text = "Oops! Looks like something went wrong: \n %s".formatted(message);
        System.out.println(text);
    }
}

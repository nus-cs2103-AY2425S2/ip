package yuki;

/**
 * Ui class deals with interactions with the user.
 */
public class Ui {
    private static final String BORDER = "____________________________________________________________";

    public Ui() {
    }

    public void showWelcome() {
        System.out.println(BORDER);
        System.out.println("Hello! I'm Yuki");
        System.out.println("What can I do for you?");
        System.out.println(BORDER);
    }

    public void showLine() {
        System.out.println(BORDER + "\n");
    }

    /**
     * Returns the goodbye message.
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints the error message to the console.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    public void showLoadingError() {
        System.out.println("Error loading file. Starting with an empty task list.");
    }

    /**
     * Prints the message to the console.
     *
     * @param message The message to be printed.
     */
    public void print(String message) {
        System.out.println(message);
    }
}

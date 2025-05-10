package steve.ui;

/**
 * Provides static methods for printing various messages in the chatbot interface.
 */
public class Messages {

    /**
     * Prints the greeting message when the chatbot starts.
     */
    public static void greeting() {
        System.out.println("_________________________\n"
                + "     Hello! I'm Steve 🌈\n"
                + "     Ready to craft some tasks?\n"
                + "_________________________\n");
    }

    /**
     * Returns the greeting message as String for GUI message.
     */
    public static String greetingToString() {
        return "_________________________\n"
                + "     Hello! I'm Steve 🌈\n"
                + "     Ready to craft some tasks?\n"
                + "_________________________\n";

    }

    /**
     * Prints the goodbye message when the chatbot exits.
     */
    public static void goodbye() {
        System.out.println("____________________________"
                + "________________________________\n"
                + "     \n" + "Bye! Hope to see you again soon =)" + "\n"
                + "______________________________"
                + "______________________________");
    }

    /**
     * Displays an error message when the chatbot does not understand the user input.
     */
    public static void unknown() {
        String first = "Sorry I don't quite understand!";
        String second = " Please add a task by entering: \n 1. todo "
                + "\n 2. deadline \n 3. event \n 4. find \n 5. contact \n 7. mark \n 8. unmark \n 9. list \n";
        String third = "Followed by a description of your task! =)";
        System.out.println(first + second + third);
    }

    /**
     * Prints an error message when the chatbot does not understand the user input.
     */
    public static String unknownString() {
        String result = "Whoopsie-doodle! I'm all out of brain cells."
                + "Try these: \n 1. todo "
                + "\n 2. deadline \n 3. event \n 4. find \n 5. contact "
                + "\n 7. mark \n 8. unmark \n 9. list \n 10. clients \n"
                + "~ Do-do-do do do-do do, your friend....Steve! 🌈";
        return result;
    }

    /**
     * Returns a message indicating that the description cannot be empty.
     *
     * @return the message about empty description
     */
    public static String descriptionEmpty() {
        return "Description cannot be empty. "
                + "Usage: todo <description>";
    }

    /**
     * Prints a border line for visual separation of messages.
     */
    public static void border() {
        System.out.println("_________________________"
                + "___________________________________");
    }

    /**
     * Returns a message prompting the user to enter a valid number for the task.
     *
     * @return the message for invalid task number input
     */
    public static String formatExceptionMessage() {
        return "Please enter a valid number for the task!";
    }
}

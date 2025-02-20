package ui;

/**
 * This class represents an interaction between the user and system.
 * It greets the user when starting up and bids goodbye when done.
 *
 * @author Yashvan
 */
public class Greeting {
    /**
     * Lists out the current functions that can be used by the user.
     *
     * @return The various functions with a specified format that the user can use.
     */
    public static String help() {
        return "Here's what I can do for now!\n"
                + getTodoHelp()
                + getDeadlineHelp()
                + getEventHelp()
                + getGeneralCommands();
    }

    /**
     * Provides help information for the 'todo' command.
     *
     * @return A string describing the usage of the 'todo' command.
     */
    private static String getTodoHelp() {
        return "| todo <description> /priority <LOW|MEDIUM|HIGH|URGENT>: "
                + "Adds a todo without a specific date/time\n";
    }

    /**
     * Provides help information for the 'deadline' command.
     *
     * @return A string describing the usage of the 'deadline' command.
     */
    private static String getDeadlineHelp() {
        return "| deadline <description> /by <D/M/YYYY HHmm> /priority <LOW|MEDIUM|HIGH|URGENT>: "
                + "Adds a task with a deadline\n";
    }

    /**
     * Provides help information for the 'event' command.
     *
     * @return A string describing the usage of the 'event' command.
     */
    private static String getEventHelp() {
        return "| event <description> /from <D/M/YYYY HHmm> /to <D/M/YYYY HHmm> "
                + "/priority <LOW|MEDIUM|HIGH|URGENT>: "
                + "Adds event with start/end time\n";
    }

    /**
     * Provides help information for general commands.
     *
     * @return A string describing the usage of general commands.
     */
    private static String getGeneralCommands() {
        return "| delete <task number>: Deletes task no.x\n"
                + "| find <description>: Finds task with matching description\n"
                + "| list: Displays all tasks added\n"
                + "| mark <task number>: Marks task no.x as done\n"
                + "| unmark <task number>: Unmarks task no.x\n"
                + "| exit: Exits the application";
    }

    /**
     * Shows a help command if the user enters the wrong or unknown command.
     *
     * @return The command to type for 'help'.
     */
    public static String callForHelp() {
        return "______________________________________________________________________________________\n"
                + "Whatchu talking about bruh? Type 'help' if you need it!"
                + "\n______________________________________________________________________________________\n";
    }
}

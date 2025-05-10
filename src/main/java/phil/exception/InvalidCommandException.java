package phil.exception;

/**
 * Represents the exception regarding invalid commands passed when creating tasks.
 * This is thrown when the first word of the input line is not a valid command.
 */
public class InvalidCommandException extends PhilException {

    /**
     * Sets up the InvalidCommandException.
     *
     * @param invalidCommand String representing first word of the command passed by the user.
     */
    public InvalidCommandException(String invalidCommand) {
        super(invalidCommand + " is not a valid command. \n"
                + "These are the supported commands: \n"
                + "'list' to see the list of tasks.\n"
                + "'todo <some task>' to create a new Todo task. \n"
                + "'deadline <some task> /by <deadline-date>' to create a new Deadline task. \n"
                + "'event <some task> /from <start-date> /to <end-date>' to create a new Event task. \n"
                + "'delete <some task index>' to delete a task. \n"
                + "'find <some search term>' to find a task by that search term. \n"
                + "'new-note <some note>' to create a new note. \n"
                + "'delete-note <some note index>' to delete a note. \n"
                + "'list-note' to view a list of notes. \n"
                + "'bye' to end the conversation. \n");
    }
}

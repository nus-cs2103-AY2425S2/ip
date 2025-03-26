package viktor.commands;

/**
 * Command to display a list of available commands.
 */
public class HelpCommand implements Commandable {
    /**
     * Executes the command to display available commands.
     *
     * @return A formatted string listing the bot's capabilities.
     */
    @Override
    public String execute() {
        return "Here are some of my advanced capabilities:\n\n"
                + "Adding Tasks - 'add <task description>'\n"
                + "Deleting a Task - 'delete <task number>'\n"
                + "Marking Tasks as Done - 'mark <task number>'\n"
                + "Unmarking Tasks as Done - 'unmark <task number>'\n"
                + "Viewing all Tasks - 'list'\n"
                + "Finding Tasks - 'find <keyword>'\n"
                + "Viewing Tasks by Date - 'time <date>'\n"
                + "Exiting the Program - 'bye'\n"
                + "Resetting the Program - 'reset'\n\n"
                + "What can I do for you today?\n";
    }
}

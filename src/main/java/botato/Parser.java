package botato;


import botato.command.Command;
import botato.command.DeadlineCommand;
import botato.command.DeleteCommand;
import botato.command.EventCommand;
import botato.command.ExitCommand;
import botato.command.FindCommand;
import botato.command.HelpCommand;
import botato.command.ListCommand;
import botato.command.MarkCommand;
import botato.command.TodoCommand;
import botato.command.UnmarkCommand;
import botato.exception.InvalidCommandException;
import botato.exception.InvalidTaskNumberException;
import botato.exception.MissingKeywordException;

/**
 * The parser class takes a user input string and returns a {@link Command} object.
 */
public class Parser {
    /**
     * Parses user input string and returns relevant command.
     * If input string is not a valid command, throws {@link InvalidCommandException}.
     *
     * @param cmd is user input read by {@link Ui#readCommand()}.
     * @return {@link Command} object based on input.
     */
    public static Command parse(String cmd) {
        assert cmd != null : "Input cannot be null";
        if (cmd.strip().equals("bye")) {
            // Save data and exit chatbot
            return new ExitCommand();
        } else if (cmd.strip().equals("list")) {
            // Display list of tasks
            return new ListCommand();
        } else if (cmd.matches("^mark \\d+$")) {
            // Mark specific task as completed
            int taskNumber = validateAndGetTaskNumber(cmd);
            return new MarkCommand(taskNumber);
        } else if (cmd.matches("^unmark \\d+$")) {
            // Mark specified task as not done
            int taskNumber = validateAndGetTaskNumber(cmd);
            return new UnmarkCommand(taskNumber);
        } else if (cmd.matches("^todo\\s+.+$")) {
            // Add a Task of type Todo to tasks
            return new TodoCommand(cmd);
        } else if (cmd.matches("^deadline\\s+.+/by\\s+.+$")) {
            // Add a Task of type Deadline to tasks
            return new DeadlineCommand(cmd);
        } else if (cmd.matches("^event\\s+.+/from\\s+.+/to\\s+.+$")) {
            // Add a Task of type Event to tasks
            return new EventCommand(cmd);
        } else if (cmd.matches("^delete \\d+$")) {
            // Delete a specified task based on task number given
            int taskNumber = validateAndGetTaskNumber(cmd);
            return new DeleteCommand(taskNumber);
        } else if (cmd.strip().equals("help")) {
            // Opens help interface
            return new HelpCommand();
        } else if (cmd.matches("^find\\s+.+")) {
            String keyword = validateAndGetKeyword(cmd);
            return new FindCommand(keyword);
        } else {
            // Handle invalid commands
            throw new InvalidCommandException();
        }
    }

    private static int validateAndGetTaskNumber(String cmd) {
        int taskNumber = Integer.parseInt(cmd.split(" ")[1]);
        if (taskNumber <=0 || taskNumber > TaskList.size()) {
            throw new InvalidTaskNumberException(TaskList.size());
        }
        return taskNumber;
    }

    private static String validateAndGetKeyword(String cmd) {
        String keyword = cmd.substring(cmd.indexOf(" "));
        if (keyword.isBlank()) {
            throw new MissingKeywordException();
        }
        return keyword;
    }

}

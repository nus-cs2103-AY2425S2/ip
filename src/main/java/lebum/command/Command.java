package lebum.command;
import lebum.exception.DukeException;
import lebum.main.Storage;
import lebum.main.TaskList;
import lebum.main.Ui;


/**
 * Parent command class.
 */
public abstract class Command {
    private String response;
    public static final String EMPTY_DESC_ERROR = "OOPS!!! The description of a deadline cannot be empty.";
    public static final String MISSING_DEADLINE_ERROR = "OOPS!!! Please provide a deadline using /by.";
    public static final String INVALID_FORMAT_ERROR = "OOPS!!! Invalid deadline format. Use: deadline <description> /by <time>";
    public static final String INVALID_DATE_ERROR = "Invalid date format. Use yyyy-MM-dd or yyyy-MM-dd HH:mm";
    public static final String MULTIPLE_BY_ERROR = "OOPS!!! Multiple /by tags found. Please use only one /by tag.";
    public static final String DUPLICATE_TASK_ERROR = "OOPS!!! This exact deadline already exists in your list.";
    public static final String PAST_DATE_ERROR = "OOPS!!! Deadline cannot be set in the past.";
    public static final String SPECIAL_CHAR_ERROR = "OOPS!!! Description contains invalid special characters.";
    public static final String MAX_LENGTH_ERROR = "OOPS!!! Description is too long. Maximum 100 characters allowed.";
    public static final String EMPTY_TIME_ERROR = "OOPS!!! Time cannot be empty after /by.";
    public abstract void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException;

    public String getResponse() {
        return response;
    }
    public boolean isExit() {
        return false;
    }

    /**
     * Validates if input is a user command or normal command.
     * @param input user input
     * @return if the input contains , which means it is a mass command
     */
    public boolean isMassCommand(String[] input) {
        String content = input[1];
        return content.contains(",");
    }
}

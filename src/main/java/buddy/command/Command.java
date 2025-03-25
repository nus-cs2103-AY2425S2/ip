package buddy.command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import buddy.exception.BuddyException;
import buddy.storage.DataStorage;
import buddy.task.TaskList;

/**
 * Represents Command from the user
 */
public abstract class Command {
    private static final DateTimeFormatter storeDateTimePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    protected ArrayList<String> args;
    protected boolean isExit = false;

    /**
     * Constructor for Command class
     */
    public Command() {
    }

    /**
     * Constructor for Command class
     *
     * @param args Arguments from user command
     */
    public Command(ArrayList<String> args) {
        this.args = args;
    }

    /**
     * Gets date and time.
     *
     * @param input The date time input from user
     * @return The date and time
     * @throws DateTimeParseException         The date time parse exception
     * @throws ArrayIndexOutOfBoundsException The array index out of bounds exception
     */
    public static LocalDateTime getDateAndTime(String input)
            throws DateTimeParseException, ArrayIndexOutOfBoundsException {
        String[] data = input.split(" ");
        LocalDate date = LocalDate.parse(data[0], storeDateTimePattern);
        String time = data[1];
        return date.atTime(Integer.parseInt(time.substring(0, 2)),
                Integer.parseInt(time.substring(2, 4)));
    }

    public ArrayList<String> getArgs() {
        return this.args;
    }

    /**
     * Gets exit status.
     *
     * @return the exit status
     */
    public boolean getExitStatus() {
        return isExit;
    }

    /**
     * Retrieves string response of command execution.
     *
     * @param taskList    The task list
     * @param dataStorage The data storage from hard disk
     * @return The response when executing command
     * @throws BuddyException The buddy exception
     */
    public abstract String execute(TaskList taskList, DataStorage dataStorage) throws BuddyException;
}

package laffy.command;

import laffy.Storage;
import laffy.Ui;
import laffy.command.exceptions.MissingKeywordFlag;
import laffy.tasklist.TaskList;
import laffy.tasklist.exceptions.TaskListException;

/**
 * Represents a command that can be executed by the user.
 */
public abstract class Command {
    private static final CommandWord COMMAND_WORD = CommandWord.INVALID;
    private boolean isValid;

    public Command(String args) {
        this.isValid = false;
    }

    /**
     * Executes the command.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TaskListException {
        // executes after all children have done so
        storage.saveData(taskList.toTasksData());
    };

    /**
     * Executes the command without UI
     */
    public String execute(TaskList taskList, Storage storage) throws TaskListException {
        storage.saveData(taskList.toTasksData());
        return "";
    }

    public boolean isExit() {
        return false;
    }

    /**
     * Utility function to check if a keyword flag is present in the command.
     * Allowed formats are:
     * 1. flag
     * 2. flag args
     * 3. args flag
     * 4. args flag args
     *
     * @param args The command arguments.
     * @param keywordFlag The keyword flag to check for.
     * @throws MissingKeywordFlag If the keyword flag is not present in the command.
     */
    public void checkKeywordFlagIsPresent(String args, String keywordFlag) throws MissingKeywordFlag {
        if (!args.matches(keywordFlag + "|(.*)\\s"
                + keywordFlag + "|" + keywordFlag
                + "\\s(.*)|(.*)\\s" + keywordFlag
                + "\\s(.*)")) {
            throw new MissingKeywordFlag(
                    "Could not find flag \"" + keywordFlag + "\" in command.\n" + getUsage()
            );
        }
    }

    public static String getCommandWord() {
        return COMMAND_WORD.toString();
    }

    public boolean isValid() {
        return this.isValid;
    }

    public void setValid(boolean bool) {
        this.isValid = bool;
    }

    public static String getDescription() {
        return "";
    }

    public String getUsage() {
        return "Usage: ";
    }

}

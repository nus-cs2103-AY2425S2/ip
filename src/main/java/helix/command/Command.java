package helix.command;

import helix.enums.CommandType;
import helix.enums.ExecutionStatus;
import helix.exception.HelixException;
import helix.task.TaskList;
import helix.ui.ConsoleUi;

/**
 * Represents an executable command in the Helix application.
 */
public abstract class Command {

    private final CommandType commandType;

    /**
     * Constructs a new Command with the specified {@link CommandType}.
     *
     * @param commandType the type of the helix.command
     */
    public Command(CommandType commandType) {
        this.commandType = commandType;
    }

    /**
     * Retrieves the {@link CommandType} of this helix.command.
     *
     * @return the type of the helix.command
     */
    public CommandType getCommandType() {
        return commandType;
    }

    /**
     * Executes the helix.command on the specified TaskList.
     *
     * @param taskList the TaskList on which the helix.command will operate
     * @param consoleUi the ConsoleUi component used to display messages to the user
     * @throws HelixException if the helix.command fails
     */
    public abstract void execute(TaskList taskList, ConsoleUi consoleUi) throws HelixException;

    /**
     * Determines whether the application should continue or terminate after executing the helix.command.
     *
     * @return {@code ExecutionStatus.CONTINUE} to continue or {@code ExecutionStatus.EXIT} to terminate.
     */
    public ExecutionStatus isExit() {
        return ExecutionStatus.CONTINUE;
    }
}

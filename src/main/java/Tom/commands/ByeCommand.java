package Tom.commands;

import Tom.tasks.TaskList;

/**
 * Represents a user bye command to exit the program.
 */
public class ByeCommand extends Command {

    /**
     * Executes the bye command to exit the program.
     *
     * @param tasks the taskList instance.
     * @return A string message of the command's response.
     */
    @Override
    public String execute(TaskList tasks) {
        System.exit(0);
        return "Bye! See you again soon!";
    }

}

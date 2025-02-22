package marshmallow.commands;

import marshmallow.tasks.TaskList;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {
    /**
     * Constructs an exit command.
     */
    public ExitCommand() {
        this.isExit = true;
    }

    @Override
    public String handle(TaskList tasks) {
        return "Bye. Hope to see you again soon!";
    }
}

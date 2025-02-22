package marshmallow.commands;

import marshmallow.tasks.TaskList;

/**
 * Represents a command to display the list of tasks.
 */
public class ListDisplayCommand extends Command {
    @Override
    public String handle(TaskList tasks) {
        assert tasks != null : "Task list should not be null";

        String response = tasks.toString();
        if (response.isBlank()) {
            return "You have no tasks!";
        }

        return response;
    }
}

package marshmallow.commands;

import marshmallow.MarshmallowException;
import marshmallow.tasks.Todo;

/**
 * Represents a command to add a todo task to the task list.
 */
public class ListAddTodoCommand extends ListAddTaskCommand {
    /**
     * Constructs a command to add a todo task to the task list.
     *
     * @param description Description of the todo task.
     */
    public ListAddTodoCommand(String description) {
        if (description.isEmpty()) {
            throw new MarshmallowException("Please tell me what you need to do!");
        }

        this.task = new Todo(description);
    }
}

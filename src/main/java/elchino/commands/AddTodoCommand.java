package elchino.commands;

import elchino.exceptions.*;
import elchino.storage.Storage;
import elchino.tasks.*;
import elchino.ui.Ui;

/**
 * Command to add a new todo task
 */
public class AddTodoCommand extends Command {
    private final String description;

    private static final String ERROR_EMPTY_DESCRIPTION = "La descripción no puede estar vacía";
    private static final String MESSAGE_ADD_TODO = "Agregado: %s";

    /**
     * Creates a new AddTodoCommand with the given description
     * @param input Description of the todo task
     */
    public AddTodoCommand(String input) throws InvalidInputException {
        if (input.isEmpty()) {
            throw new InvalidInputException(ERROR_EMPTY_DESCRIPTION);
        }
        this.description = input;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ElchinoException {
        Task task = new Todo(description);
        tasks.addTask(task);
        storage.saveTasks(tasks.getTasks());
        return String.format(MESSAGE_ADD_TODO, task);
    }
}
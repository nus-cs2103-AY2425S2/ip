package gigi.commands;

import java.io.IOException;

import gigi.exceptions.GigiException;
import gigi.storage.Storage;
import gigi.tasks.Tasklist;
import gigi.tasks.ToDos;
import gigi.ui.Ui;

/**
 * Represents a command to add a ToDo task to the task list.
 * This command is triggered when the user inputs "todo" followed by a task description.
 */

public class ToDoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    private final String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command to add a ToDo task to the task list.
     * If description is empty, an exception is thrown.
     * Otherwise, the task is added, saved, and a confirmation message is shown.
     *
     * @param tasks   The task list where the ToDo task will be added.
     * @param ui      The UI component responsible for displaying messages.
     * @param storage The storage component for saving tasks.
     * @throws GigiException If the task description is empty.
     */
    @Override
    public String execute(Tasklist tasks, Ui ui, Storage storage) throws GigiException, IOException {
        if (description.isBlank()) {
            throw new GigiException("MEOW! A ToDo task cannot be empty.");
        }
        ToDos todo = new ToDos(description);
        tasks.addTask(todo);

        tasks.saveTasks(storage);

        return ui.showAddMessage() + "\n"
                + ui.showMessage(String.valueOf(todo)) + "\n"
                + ui.showTaskNumber(tasks);
    }
}

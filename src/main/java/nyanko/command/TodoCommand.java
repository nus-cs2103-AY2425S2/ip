package nyanko.command;

import java.io.IOException;

import nyanko.storage.Storage;
import nyanko.task.TaskList;
import nyanko.task.ToDo;
import nyanko.ui.Ui;

/**
 * Represents a command to add a new {@link ToDo} task to the task list.
 */
public class TodoCommand extends Command {
    private String description;

    /**
     * Constructs a {@code TodoCommand} with the specified task description.
     *
     * @param argument The description of the To-Do task.
     */
    public TodoCommand(String argument) {
        if (argument == null || argument.trim().isEmpty()) {
            throw new IllegalArgumentException("Your description cannot be empty dumbdumb!!");
        }
        this.description = argument;
    }

    /**
     * Executes the command to add a new To-Do task to the task list.
     * The task is stored in memory and saved in persistent storage.
     *
     * @param tasks   The {@link TaskList} containing the current tasks.
     * @param ui      The {@link Ui} instance responsible for user interaction.
     * @param storage The {@link Storage} instance used to save task data.
     * @throws IOException If an error occurs while saving the updated task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ToDo todo = new ToDo(description);
        tasks.addTask(todo);
        String toDoString = "WOW you're so hardworking\n"
                + "ok fine... your task has been added!\nadded: "
                + todo.toString()
                + "\nOh my! You have "
                + tasks.size()
                + " tasks!";
        ui.showMessage(toDoString);
        storage.save(tasks.getTasks());
    }
}

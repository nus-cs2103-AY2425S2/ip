package tyler.command;

import tyler.storage.Storage;
import tyler.task.ToDo;
import tyler.task.list.TaskList;
import tyler.ui.Ui;

/**
 * Represents a command to add a new todo to the list.
 */
public class ToDoCommand extends Command {
    private final String[] tokens;

    public ToDoCommand(String[] tokens) {
        super();
        this.tokens = tokens;
    }

    /**
     * Adds specified todo to the list of tasks and returns the modified list.
     *
     * @param tasks   The list of tasks to which the todo should be added.
     * @param ui      The UI object for any required printing.
     * @param storage The storage for I/O involving storing the tasks on the disk (unused).
     * @return The list with the task added.
     */
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            ToDo toDo = new ToDo(tokens[1]);
            assert toDo.getCategory().equals("T");
            boolean isDuplicate = tasks.isDuplicate(toDo);
            if (!isDuplicate) {
                tasks.addToList(toDo, ui);
            } else {
                ui.showMessage("\t !!This task already exists!!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showMessage("\t !!Please provide the correct number of arguments!!");
        } catch (IllegalArgumentException e) {
            ui.showMessage(e.getMessage());
        }
        return tasks;
    }
}

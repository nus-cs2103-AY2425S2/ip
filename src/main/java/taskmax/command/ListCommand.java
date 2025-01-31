package taskmax.command;

import taskmax.storage.Storage;
import taskmax.storage.TaskList;

import taskmax.ui.Ui;

import taskmax.exception.TaskmaxException;

public class ListCommand extends Command {
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws TaskmaxException {
        if (tasks.isEmpty()) {
            ui.showMessage("Your task list is empty! Start adding tasks to see them in the list!.");
        } else {
            StringBuilder output = new StringBuilder("\nHere are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                output.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
            }
            ui.showMessage(output.toString());
        }
        return false;
    }
}

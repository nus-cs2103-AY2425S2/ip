package taskmax.command;

import taskmax.storage.Storage;
import taskmax.storage.TaskList;

import taskmax.ui.Ui;

import java.io.IOException;

public class ExitCommand extends Command {
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.saveTasks(tasks.getTasks());
            ui.showMessage("Tasks have been saved to my drive!");
        } catch (IOException e) {
            ui.showMessage("Error saving tasks to my drive!");
        }

        ui.showMessage("\nI hope that you are satisfied with your service.\n"
                + "See you again soon!\n");
        return true;
    }
}

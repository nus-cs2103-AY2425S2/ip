package nyanko.command;

import nyanko.storage.Storage;
import nyanko.task.TaskList;
import nyanko.ui.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(String argument) {
        this.index = Integer.parseInt(argument) - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, InvalidTaskNumberException {
        if (index >= tasks.size() || index < 0) {
            throw new InvalidTaskNumberException("Task number " + (index + 1) + " is invalid!!");
        }
        tasks.removeTask(index);
        ui.showMessage("You're goofy but I deleted your task");
        storage.save(tasks.getTasks());
    }
}
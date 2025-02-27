package nyanko.command;

import nyanko.storage.Storage;
import nyanko.task.Task;
import nyanko.task.TaskList;
import nyanko.ui.Ui;

import java.io.IOException;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(String argument) {
        this.index = Integer.parseInt(argument) - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, InvalidTaskNumberException {
        if (index >= tasks.size() || index < 0) {
            throw new InvalidTaskNumberException("Oi! Task number " + (index + 1) + " is invalid!!");
        }
        Task task = tasks.getTask(index);
        task.markAsDone();
        String markString = "zzzz... oh WHAT you're done already?\n  " + task.toString();
        ui.showMessage(markString);
        storage.save(tasks.getTasks());
    }
}

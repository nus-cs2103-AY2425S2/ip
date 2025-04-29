package xuxin.command;

import xuxin.main.Statistics;
import xuxin.main.Storage;
import xuxin.main.TaskList;
import xuxin.main.Ui;

/**
 * ExitCommand is a command to flag the conversation is ended.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, Statistics stats) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
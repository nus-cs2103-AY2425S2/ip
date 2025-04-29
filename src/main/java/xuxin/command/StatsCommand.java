package xuxin.command;

import xuxin.main.Statistics;
import xuxin.main.Storage;
import xuxin.main.TaskList;
import xuxin.main.Ui;
import xuxin.exception.DukeException;


public class StatsCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage,
                        Statistics stats) throws DukeException {
        ui.addMessage(stats.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

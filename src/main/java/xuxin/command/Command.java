package xuxin.command;

import xuxin.main.Statistics;
import xuxin.main.Storage;
import xuxin.main.TaskList;
import xuxin.main.Ui;
import xuxin.exception.DukeException;

/**
 * Command is an abstract command to make some changes to the tasks in the tasklist.
 */
public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage,
                                 Statistics stats) throws DukeException;
    public abstract boolean isExit();
}
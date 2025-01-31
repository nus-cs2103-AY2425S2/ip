package taskmax.command;

import taskmax.storage.Storage;
import taskmax.storage.TaskList;

import taskmax.ui.Ui;

import taskmax.exception.TaskmaxException;

public abstract class Command {
    public abstract boolean execute(TaskList tasks, Ui ui, Storage storage) throws TaskmaxException;
}

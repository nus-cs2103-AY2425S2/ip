package dudu.controller;

import dudu.model.TaskList;

import dudu.utils.DuduException;

import java.io.File;
import java.io.IOException;

public interface Command {
    String execute(TaskList tasks, File cachedTasks) throws DuduException, IOException;
    boolean isExit();
}

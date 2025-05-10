package dudu.controller;

import dudu.model.TaskList;

import dudu.utils.Ui;

import java.io.File;

public class HelpCommand implements Command {

    /**
     * Returns the list of commands available for the user.
     *
     * @param tasks Current list of tasks.
     * @param cachedTasks Save file.
     * @return List of commands.
     */
    @Override
    public String execute(TaskList tasks, File cachedTasks) {
        return Ui.getCommands();
    }

    /**
     * Returns false as it is not an exit command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

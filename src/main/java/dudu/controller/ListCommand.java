package dudu.controller;

import dudu.model.Task;
import dudu.model.TaskList;

import dudu.utils.Ui;

import java.io.File;
import java.util.ArrayList;

public class ListCommand implements Command {

    /**
     * Returns the current list of tasks.
     *
     * @param tasks Current list of tasks.
     * @param cachedTasks Save file.
     * @return Current list of tasks.
     */
    @Override
    public String execute(TaskList tasks, File cachedTasks) {
        ArrayList<Task> tasksToPrint = tasks.getAllTasks();

        if (tasksToPrint.isEmpty()) {
            return "You have no tasks to show.";
        }

        String outputText = "Here are your current list of tasks!\n\n";
        for (int i = 0; i < tasksToPrint.size(); i++) {
            Task curr = tasksToPrint.get(i);
            String result = (i + 1) + ". " + curr + "\n";
            outputText += result;
        }

        return outputText;
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

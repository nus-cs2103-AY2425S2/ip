package dudu.controller;

import dudu.model.Task;
import dudu.model.TaskList;
import dudu.utils.DuduException;
import dudu.utils.Ui;

import java.io.File;
import java.io.IOException;

public class FindCommand implements Command {

    private final String description;

    /**
     * Creates FindCommand object.
     *
     * @param description Arguments of command.
     */
    public FindCommand(String description) {
        this.description = description;
    }

    /**
     * Finds and prints out list of tasks with given keyword.
     *
     * @param tasks Current list of tasks.
     * @param cachedTasks Save file.
     * @return String containing the current list of tasks.
     */
    @Override
    public String execute(TaskList tasks, File cachedTasks) {
        int taskCounter = 1;
        String outputText = "";
        for (int i = 0; i < tasks.getSize(); i++) {
            Task currTask = tasks.get(i);
            String taskName = currTask.getName().toLowerCase();
            String searchInput = description.toLowerCase();

            if (taskName.contains(searchInput)) {
                if (taskCounter == 1) {
                    outputText += "Here are the matching tasks in your list:\n";
                }
                outputText += taskCounter + ". " + currTask + "\n";
                taskCounter++;
            }
        }
        if (outputText.isEmpty()){
            return "No tasks found with given keyword.";
        } else {
            return outputText;
        }
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

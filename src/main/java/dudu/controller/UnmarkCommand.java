package dudu.controller;

import dudu.model.Task;
import dudu.model.TaskList;

import dudu.utils.DuduException;
import dudu.utils.Ui;

import java.io.File;
import java.io.IOException;

public class UnmarkCommand implements Command {
    private final String description;

    /**
     * Creates UnmarkCommand object.
     *
     * @param description Arguments of command.
     */
    public UnmarkCommand(String description) {
        this.description = description;
    }

    private static void validateIndex(int index, TaskList taskList) throws DuduException {
        if (index < 0 || index >= taskList.getSize()) {
            throw new DuduException("Invalid task number! Please enter a valid task number.");
        }
    }

    /**
     * Marks selected task as not done.
     *
     * @param tasks Current list of tasks.
     * @param cachedTasks Save file.
     * @return String containing the task that was marked not done.
     * @throws DuduException If task has already been marked as not done.
     * @throws IOException If write operation is interrupted.
     */
    @Override
    public String execute(TaskList tasks, File cachedTasks) throws DuduException, IOException {
        try {
            int taskIndex;
            boolean isMarkedDone;
            taskIndex = Integer.parseInt(description) - 1;
            validateIndex(taskIndex, tasks);

            Task curr = tasks.get(taskIndex);
            isMarkedDone = curr.getIsDone();
            if (isMarkedDone) {
                curr.toggleDone();
                String toPrint = "Alright, I've marked this task as not done yet:\n" + curr;
                FileOperation.overwriteFile(cachedTasks, tasks);
                return toPrint;
            } else {
                return "Task is already marked as not done!";
            }
        } catch (DuduException e) {
            return e.getMessage();
        } catch (NumberFormatException e) {
            return "Invalid task number! Please enter a valid task number.";
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

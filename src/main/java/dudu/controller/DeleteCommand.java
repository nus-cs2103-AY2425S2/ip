package dudu.controller;

import dudu.model.TaskList;

import dudu.utils.DuduException;
import dudu.utils.Ui;

import java.io.File;
import java.io.IOException;

public class DeleteCommand implements Command {

    private final String description;

    /**
     * Creates DeleteCommand object.
     *
     * @param description Arguments of command.
     */
    public DeleteCommand(String description) {
        this.description = description;
    }

    private static void validateIndex(int index, TaskList taskList) throws DuduException {
        if (index < 0 || index >= taskList.getSize()) {
            throw new DuduException("Invalid task number! Please enter a valid task number.");
        }
    }

    /**
     * Deletes selected task from the current tasks and overwrites the save file.
     *
     * @param tasks Current Tasks in the list.
     * @param cachedTasks Save file.
     * @return String containing task that was deleted.
     * @throws DuduException If task number is out of bounds or file operation does not work.
     */
    @Override
    public String execute(TaskList tasks, File cachedTasks) throws DuduException {
        try {
            int taskIndex;
            String deleteMessage;
            taskIndex = Integer.parseInt(description) - 1;
            validateIndex(taskIndex, tasks);
            deleteMessage = tasks.deleteTask(taskIndex);
            FileOperation.overwriteFile(cachedTasks, tasks);
            return deleteMessage;
        } catch (DuduException e) {
            return e.getMessage();
        } catch (NumberFormatException e) {
            return "Invalid task number! Please enter a valid task number.";
        }
        catch (IOException e) {
            throw new RuntimeException(e);
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

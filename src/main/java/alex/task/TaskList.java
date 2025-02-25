package alex.task;

import java.io.IOException;
import java.util.ArrayList;

import alex.exceptions.ListOutOfBoundException;
import alex.Storage;
import alex.Ui;

/**
 * Keeps track of the list of the tasks
 */
public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Add a task into the task list and saves it into storage
     * @param task task to be added
     * @param ui current ui
     * @param storage the storage to save the task
     */
    public void addItem(Task task, Ui ui, Storage storage) {
        list.add(task);
        int itemCount = list.size();
        ui.addItemResponse(task.toString(), itemCount);
        storage.saveData(task.getSavedDataFormat());
    }

    /**
     * Display all the tasks in the list
     * @param ui current ui
     */
    public void displayList(Ui ui) {
        StringBuilder resultMessage;
        if (list.size() == 0) {
            resultMessage = new StringBuilder("Seems like you haven't created any task. Add them now!");
        } else {
            resultMessage = new StringBuilder("Here are the tasks in your list:\n");
        }

        int count = 1;
        for (Task task : list) {
            resultMessage.append(count).append(". ").append(task).append("\n");
            count++;
        }
        ui.printMsg(resultMessage.toString());
    }

    /**
     * Set the status of a task of a given index
     * @param index index of the task counting from 1
     * @param isDone the target status
     * @param ui system ui
     * @param storage storage to save data
     */
    public void mark(int index, boolean isDone, Ui ui, Storage storage) {
        try {
            String responseMsg = list.get(index - 1).setStatus(isDone);
            storage.updateLineInFile(index, list.get(index - 1).getSavedDataFormat().stripTrailing());
            ui.printMsg(responseMsg);
        } catch (IOException e) {
            ui.showErrorMsg(e);
        }
    }

    /**
     * Set the status of a task of a given index
     * @param range range of indices of the task counting from 1
     * @param isDone the target status
     * @param ui system ui
     * @param storage storage to save data
     */
    public void mark(int[] range, boolean isDone, Ui ui, Storage storage) {
        try {
            StringBuilder responseMsg = new StringBuilder(getMarkInitialMsg(isDone));
            for (int i = range[0]; i <= range[1]; i++) {
                list.get(i - 1).setStatus(isDone);
                responseMsg.append(list.get(i - 1) + "\n");
                storage.updateLineInFile(i, list.get(i - 1).getSavedDataFormat().stripTrailing());
            }
            ui.printMsg(responseMsg.toString());
        } catch (IOException e) {
            ui.showErrorMsg(e);
        }
    }

    private String getMarkInitialMsg(boolean isDone) {
        if (isDone) {
            return "Nice! I've marked these tasks as done:\n";
        } else {
            return "Ok, I've marked these tasks as not done yet:\n";
        }
    }

    /**
     * Deletes a task from the list
     * @param index index of the task to delete counting form 1
     * @param ui
     * @param storage
     */
    public void delete(int index, Ui ui, Storage storage) {
        try {
            String task = list.remove(index - 1).toString();
            ui.deleteTaskResponse(task, list.size());
            storage.deleteLineFromFile(index);
        } catch (IOException e) {
            ui.showErrorMsg(e);
        }
    }

    /**
     * Deletes a range of tasks from the list
     * @param range range of indices of the task to delete counting form 1
     * @param ui
     * @param storage
     */
    public void delete(int[] range, Ui ui, Storage storage) {
        try {
            StringBuilder responseMsg = new StringBuilder("Noted. I've removed these tasks:\n");
            for (int i = range[1]; i >= range[0]; i--) {
                responseMsg.append(list.get(i - 1) + "\n");
                list.remove(i - 1);
                storage.deleteLineFromFile(i);
            }
            ui.printMsg(responseMsg.toString());
        } catch (IOException e) {
            ui.showErrorMsg(e);
        }
    }

    /**
     * Checks if the index is within the bound
     * @param index counting from 1
     * @return
     * @throws ListOutOfBoundException
     */
    public boolean checkInBound(int index) throws ListOutOfBoundException {
        if (index > list.size() || index <= 0) {
            throw new ListOutOfBoundException();
        }
        return true;
    }

    /**
     * Get task of a given index
     * @param index counting from 1
     * @return task of the index
     */
    public Task getTask(int index) {
        assert index > 0 && index <= list.size();

        return list.get(index - 1);
    }

    /**
     * Get all the tasks in the list
     * @return list of tasks
     */
    public ArrayList<Task> getTasks() {
        return list;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Integer i = 1; i <= list.size(); i++) {
            str.append(i + ". " + list.get(i - 1).toString());
            str.append("\n");
        }
        return str.toString();
    }
}

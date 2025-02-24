package model.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.exception.InvalidIndexException;

public class TaskList {

    private final List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = new ArrayList<>();
        for (Task task : taskList) {
            this.taskList.add(task);
        }
    }

    public TaskList(Task... tasks) {
        this.taskList = new ArrayList<>();
        taskList.addAll(Arrays.asList(tasks));
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to add
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Deletes a task from the task list at the specified index.
     *
     * @param index the index of the task to delete
     * @throws InvalidIndexException if the index is out of range
     */
    public void deleteTask(int index) throws InvalidIndexException {
        if (index < 0 || index >= taskList.size()) {
            throw new InvalidIndexException();
        }
        this.taskList.remove(index);
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index the index of the task to return
     * @return the task at the specified index
     * @throws InvalidIndexException if the index is out of range
     */
    public Task getTask(int index) throws InvalidIndexException {
        if (index < 0 || index >= taskList.size()) {
            throw new InvalidIndexException();
        }
        return this.taskList.get(index);
    }

    public List<Task> asList() {
        return this.taskList;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return the number of tasks in the task list
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Returns a string representation of the task list.
     *
     * @return a string representation of the task list
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            sb.append(i + 1).append(". ").append(this.taskList.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns a data string representation of the task list.
     *
     * @return a data string representation of the task list
     */
    public String toDataString() {
        StringBuilder sb = new StringBuilder();
        for (Task task : this.taskList) {
            sb.append(task.toDataString()).append("\n");
        }
        return sb.toString();
    }

}

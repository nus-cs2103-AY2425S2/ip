package bun.ui;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    /**
     * Constructs a TaskList from the given ArrayList.
     *
     * @param taskList list of tasks to include.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds a task into the task list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Delete a task from the task list
     *
     * @param index Index of the task to be removed.
     * @return The updated task list.
     * @throws InvalidIndexException If index is out of bound of the current task list.
     */
    public Task deleteTask(int index) throws InvalidIndexException {
        Task taskToDelete;
        try {
            taskToDelete = this.taskList.get(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidIndexException(index + 1, this.taskList.size());
        }
        this.taskList.remove(index);
        return taskToDelete;
    }

    /**
     * Marks a task as done
     *
     * @param index Index of the task to be marked.
     * @return The updated task list.
     * @throws InvalidIndexException If index is out of bound of the current task list.
     */
    public Task markTask(int index) throws InvalidIndexException {
        Task curTask;
        try {
            curTask = this.taskList.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException(index + 1, this.taskList.size());
        }
        curTask.markAsDone();
        return curTask;
    }

    /**
     * Unmarks a task as not done
     *
     * @param index Index of the task to be unmarked.
     * @return The updated task list.
     * @throws InvalidIndexException If index is out of bound of the current task list.
     */
    public Task unmarkTask(int index) throws InvalidIndexException {
        Task curTask;
        try {
            curTask = this.taskList.get(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidIndexException(index + 1, this.taskList.size());
        }
        curTask.markAsNotDone();
        return curTask;
    }

    /**
     * Filter out the tasks with a specified keyword.
     *
     * @param word Keyword to match.
     * @return List of matching tasks.
     */
    public TaskList filterByWord(String word) {
        ArrayList<Task> filteredTaskList = new ArrayList<>();
        for (Task task : this.taskList) {
            if (task.containsKeyword(word)) {
                filteredTaskList.add(task);
            }
        }
        return new TaskList(filteredTaskList);
    }

    /**
     * Returns the size of the task list.
     *
     * @return Size of the task list.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Checks whether the task list is empty.
     *
     * @return True if it is empty and False otherwise.
     */
    public boolean isEmpty() {
        return this.taskList.isEmpty();
    }

    /**
     * Returns the String representation of the taskList to be printed.
     *
     * @return String representation of the taskList.
     */
    @Override
    public String toString() {
        StringBuilder taskListDisplay = new StringBuilder();
        int index = 1;
        for (Task task : this.taskList) {
            taskListDisplay.append("      ").append(index).append(". ").append(task.toString()).append("\n");
            index++;
        }
        return taskListDisplay.toString();
    }

    /**
     * Returns the String representation of the taskList to be stored locally.
     *
     * @return String representation of the taskList.
     */
    public String toStoredContent() {
        StringBuilder taskListstored = new StringBuilder();
        for (Task task : this.taskList) {
            taskListstored.append(task.getStoredString());
            taskListstored.append("\n");
        }
        return taskListstored.toString();
    }
}

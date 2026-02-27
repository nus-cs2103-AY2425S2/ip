package seb.ui;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds task to ArrayList
     *
     * @param t task to be added
     * @throws SebException if task is invalid
     */
    public void addTask(Task t) throws SebException {
        tasks.add(t);
    }

    /**
     * Remove task from ArrayList
     *
     * @param i index of task to be removed from ArrayList
     * @throws IndexOutOfBoundsException if index entered
     * is exceeding number of items in list or < 0
     */
    public void removeTask(int i) throws IndexOutOfBoundsException {
        assert i >= 0 : "Index cannot be negative";

        tasks.remove(i);
    }

    /**
     * Returns number of items in ArrayList
     *
     * @return integer number
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns Task object from ArrayList
     *
     * @param i index of Task to be obtained
     * @return Task
     * @throws IndexOutOfBoundsException for invalid index
     */
    public Task getTask(int i) throws IndexOutOfBoundsException {
        assert i >= 0 : "Index cannot be negative";

        return tasks.get(i);
    }

    /**
     * Returns ArrayList of entire task list
     *
     * @return ArrayList
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * Updates task in tasklist to new task
     *
     * @param task new task to be replaced in the list
     * @param index index of task to be replaced
     */
    public void updateTask(Task task, int index) {
        this.tasks.set(index, task);
    }

}

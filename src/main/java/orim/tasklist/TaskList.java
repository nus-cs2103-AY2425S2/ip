package orim.tasklist;

import orim.task.Task;

import java.util.ArrayList;

/**
 * Contains the task list
 */
public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * @return the number of the tasks.
     */
    public int size() { // returns number of tasks
        return tasks.size();
    }

    /**
     * Gets the ith task.
     *
     * @param index the number of task.
     * @return the ith task.
     */
    public Task get(int index) { // returns ith task
        return tasks.get(index);
    }

    /**
     * Adds a new task to the list.
     *
     * @param task task to be added.
     */
    public void add(Task task) { // adds new task to the list
        tasks.add(task);
    }

    /**
     * Deletes ith task
     *
     * @param index the number of task to be deleted.
     * @return the deleted task.
     */
    public Task delete(int index) {
        Task temp = tasks.get(index);
        tasks.remove(index);
        return temp;
    }

    public TaskList find(String searchKey) {
        TaskList ans = new TaskList();
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).containKeyword(searchKey)) {
                ans.add(this.get(i));
            }
        }
        return ans;
    }

    public TaskList findUnmarkTasks() {
        TaskList ans = new TaskList();
        for (int i = 0; i < this.size(); i++) {
            if (!this.get(i).getIsMark()) {
                ans.add(this.get(i));
            }
        }
        return ans;
    }

}

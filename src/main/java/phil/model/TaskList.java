package phil.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private List<Task> listOfTasks;

    /**
     * Sets up the TaskList object.
     */
    public TaskList() {
        this.listOfTasks = new ArrayList<Task>();
    }

    /**
     * Returns number of tasks.
     *
     * @return number of tasks stored.
     */
    public int getNumberOfTasks() {
        return this.listOfTasks.size();
    }

    /**
     * Adds task to task list, and returns String output for success.
     *
     * @param task Task to be added into task list.
     * @return String output upon adding task to task list.
     */
    public String addTask(Task task) {
        this.listOfTasks.add(task);
        return "Got it! I've added this task: \n" + task.toString()
                + "\nNow you have " + this.getNumberOfTasks() + " tasks in the list.";
    }

    /**
     * Removes task from task list, and returns String output for success.
     *
     * @param index index of task (1-indexed) to be removed from list.
     * @return String output upon removing task from task list.
     */
    public String deleteTask(int index) {
        String resultStr = "Noted. I've removed this task: \n" + this.listOfTasks.get(index - 1).toString();
        this.listOfTasks.remove(index - 1);
        return resultStr + "\nNow you have " + this.getNumberOfTasks() + " tasks in the list.";
    }

    /**
     * Marks task as done on task list, and returns String output for success.
     *
     * @param index index of task (1-indexed) to be marked as done on list.
     * @return String output upon marking task as done on list.
     */
    public String markTaskAsDone(int index) {
        this.listOfTasks.get(index - 1).markDone();
        return "Nice! I've marked this task as done: \n" + this.listOfTasks.get(index - 1).toString();
    }

    /**
     * Marks task as not done on task list, and returns String output for success.
     *
     * @param index index of task (1-indexed) to be marked as not done on list.
     * @return String output upon marking task as not done on list.
     */
    public String markTaskAsNotDone(int index) {
        this.listOfTasks.get(index - 1).markNotDone();
        return "OK, I've marked this task as not done: \n" + this.listOfTasks.get(index - 1).toString();
    }

    /**
     * Returns string representation of task list, used when list is called.
     *
     * @return String representing the task list and each task.
     */
    @Override
    public String toString() {
        StringBuilder listToPrint = new StringBuilder();
        listToPrint.append("Here are the tasks in your list: \n");
        for (int i = 0; i < this.getNumberOfTasks(); i++) {
            listToPrint.append((i + 1)).append(". ").append(this.listOfTasks.get(i).toString()).append("\n");
        }
        if (this.getNumberOfTasks() > 10) {
            listToPrint.append("That's a lot of tasks! Remember to clear tasks "
                    + "you are done with using the `delete <index-of-task>` command.");
        }
        return listToPrint.toString();
    }

    /**
     * Returns list of tasks stored in the task list.
     *
     * @return list of tasks stored.
     */
    public List<Task> getListOfTasks() {
        return this.listOfTasks;
    }

    /**
     * Returns filtered tasks based on search term.
     *
     * @param searchTerm String representing the search term to use.
     * @return String representing all filtered tasks.
     */
    public String filteredTasksToString(String searchTerm) {
        StringBuilder listToPrint = new StringBuilder();
        listToPrint.append("Here are the matching tasks in your list: \n");
        for (int i = 0; i < this.getNumberOfTasks(); i++) {
            Task task = this.listOfTasks.get(i);
            if (task.descContains(searchTerm)) {
                listToPrint.append(i + 1).append(". ").append(task.toString()).append("\n");
            }
        }
        return listToPrint.toString();
    }
}

package datastructure;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import taskObjects.AbstractTask;

/**
 * {@code TaskList} class that encapsulates a list of Tasks
 */
public class TaskList {

    private List<AbstractTask> tasklist;

    /**
     * Constructs a {@code TaskList} instance with a new Arraylist
     */
    public TaskList() {
        this.tasklist = new ArrayList<>();
    }

    /**
     * Filters the list for task with description that matches keyword
     *
     * @param keyword String to be matched with
     * @return Filtered list with Task description matching keyword
     */
    public String find(String keyword) {
        List<AbstractTask> filteredList = new ArrayList<>();
        filteredList = this.tasklist.stream().filter(x -> x.getDescription()
                .contains(keyword)).toList();
        if (filteredList.isEmpty()) {
            return "Sorry Commander, I couldn't find any task that fit your description";
        }
        StringBuilder result = new StringBuilder();
        int count = 0;
        for (AbstractTask task : filteredList) {
            count++;
            result.append(count).append(". ").append(task).append("\n");
        }
        return "Here are the task that matches your search\n" + result.toString();
    }

    public String sortTasksByDeadline() {
        tasklist.sort(Comparator.comparing(
                AbstractTask::getDeadline, // Sort by deadline
                Comparator.nullsLast(Comparator.naturalOrder()) // Null (todos) go last
        ));
        return "The task has been sorted\n" + this.getTaskList();
    }

    /**
     * Gets the ArrayList containing all the tasks
     *
     * @return List of Task
     */
    public List<AbstractTask> getlist() {
        return this.tasklist;
    }

    /**
     * Loads task from storage into ArrayList
     *
     * @param task Task object taken from {@code Storage} class
     */
    public void load(AbstractTask task) {
        this.tasklist.add(task);
    }

    /**
     * adds a task to the list
     *
     * @param task Task object to be added
     * @return String message successful addition
     */
    public String add(AbstractTask task) {
        this.tasklist.add(task);
        return ("Task has been successfully added\n" + task.toString() + "\n" + this.count());
    }

    /**
     * Deletes a task from the list
     *
     * @param id Id of the Task Object to be deleted
     * @return String message of successful deletion
     */
    public String delete(int id) {
        AbstractTask deletedTask = this.tasklist.remove(id - 1);
        return ("Commander, the task has been successfully deleted\n" + deletedTask.toString()
                + "\n" + this.count());
    }

    /**
     * Marks a specific task in list as complete
     *
     * @param id Id of task to be marked completed
     * @return String message of successful marking
     */
    public String mark(int id) {
        this.tasklist.get(id - 1).markDone();
        return ("Commander, task " + id + " has been marked completed\n"
                + tasklist.get(id - 1).toString());
    }

    /**
     * Mark a specific task in the list an incomplete
     *
     * @param id Id of the task to be marked incomplete
     * @return String of message of successful marking
     */
    public String unmarked(int id) {
        this.tasklist.get(id - 1).markUndone();
        return ("Commander, taks " + id + " has been mark incomplete\n"
                + tasklist.get(id - 1).toString());
    }

    /**
     * Returns a count of the number of task in the list
     *
     * @return String message of the count of task in the list
     */
    public String count() {
        int count = this.tasklist.size();
        return "Commander, you currently have " + count + " tasks";
    }

    /**
     * Gets the List of Task in String representation
     *
     * @return String representation of TaskList
     */
    public String getTaskList() {
        return this.toString();
    }

    /**
     * Gets the String representation of TaskList
     *
     * @return String representation of TaskList
     */
    public String toString() {
        if (tasklist.isEmpty()) {
            return "Commander, currently you have no outstanding task";
        }
        StringBuilder result = new StringBuilder();
        int count = 0;
        for (AbstractTask command : tasklist) {
            count++;
            result.append(count).append(". ").append(command).append("\n");
        }

        return result.toString();
    }

}

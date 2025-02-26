package seedu.SirDuke.backend;

import seedu.SirDuke.backend.task.DeadlineTask;
import seedu.SirDuke.backend.task.ToDoTask;
import seedu.SirDuke.backend.exception.IllegalStartAndEndTimeException;
import seedu.SirDuke.backend.task.Task;
import seedu.SirDuke.backend.task.EventTask;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents a list that serves as the database for the tasks.
 * Prints responses in response to commands as well.
 * Index starts from O, but will be printed as starting from 1 in showList().
 */
public class ToDoList {

    ArrayList<Task> tasks;

    public ToDoList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Creates a new ToDoTask and adds it to the toDoList.
     * @param description name of the task
     */
    public void createToDoTask(String description) {
        Task toDo = new ToDoTask(description);
        tasks.add(toDo);
    }

    /**
     * Creates a new DeadlineTask and adds it to the toDoList.
     * @param description name of the task
     * @param toBeCompletedBy the time that the task must be completed by
     */
    public void createDeadlineTask(String description, String toBeCompletedBy)
            throws DateTimeParseException {
        Task deadline = new DeadlineTask(description, toBeCompletedBy);
        tasks.add(deadline);
    }

    /**
     * Creates a new EventTask and adds it to the toDoList.
     * @param description name of the task
     * @param startTime the time that the event starts
     * @param endTime the time that the event ends
     */
    public void createEventTask(String description, String startTime, String endTime)
            throws DateTimeParseException, IllegalStartAndEndTimeException {
        Task event = new EventTask(description, startTime, endTime);
        tasks.add(event);
    }

    /**
     * Prints the index and string representation of every task in the toDoList.
     * Index starts from 0 in ArrayList, but will be printed out as starting from 1.
     */
    public String showList() {
        StringBuilder temp = new StringBuilder();
        tasks.forEach((task) ->
                temp.append(tasks.indexOf(task) + 1 + ". " + task.toString() + "\n"));
        return temp.toString();
    }

    /**
     * Marks tasks in the list as done.
     * @param index the index of the task in the <code>ArrayList toDoList</code>
     * @throws IndexOutOfBoundsException in the case that the task does not exist
     */
    public void markTaskAsDone(int index) throws IndexOutOfBoundsException {
        Task task = tasks.get(index);
        task.markAsDone();
    }

    /**
     * Marks tasks in the list as done.
     * @param index the index of the task in the <code>ArrayList toDoList</code>
     * @throws IndexOutOfBoundsException in the case that the task does not exist
     */
    public void unmarkTaskAsDone (int index) throws IndexOutOfBoundsException {
        Task task = tasks.get(index);
        task.unmarkAsDone();
    }

    /**
     * Delete a task in the list.
     * @param index the index of the task in the <code>ArrayList toDoList</code>
     * @throws IndexOutOfBoundsException in the case that the task does not exist
     */
    public Task deleteTask(int index) throws IndexOutOfBoundsException {
        return tasks.remove(index);
    }

    /**
     * Get a task from the list.
     * @param index the index of the task in the <code>ArrayList toDoList</code>
     * @return the task at the specified index
     * @throws IndexOutOfBoundsException in the case that the task does not exist
     */
    public Task getTask(int index) throws IndexOutOfBoundsException {
        return tasks.get(index);
    }

    public int getLength() {
        return this.tasks.size();
    }

    /**
     * Finds tasks in the list that contain the keyword.
     * @param keyword the keyword of the task in the <code>ArrayList toDoList</code>
     * @throws IndexOutOfBoundsException in the case that the task does not exist
     * @return String representation of the ArrayList of tasks with the keyword.
     * Will return empty String representation of ArrayList if no tasks are found.
     */
    public String findTask(String keyword) {
        StringBuilder tasksFound = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.contains(keyword)) {
                tasksFound.append(tasks.indexOf(task) + 1).append(". ").append(task.toString()).append("\n");
            }
        }
        return tasksFound.toString();
    }

    @Override public String toString() {
        StringBuilder temp = new StringBuilder();
        for (Task task : tasks) {
            temp.append(task.toString()).append("\n");
        }
        return temp.toString();
    }
}

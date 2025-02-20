package backend;

import backend.exceptions.IllegalStartAndEndDateException;
import backend.task.Task;
import backend.task.ToDoTask;
import backend.task.DeadlineTask;
import backend.task.EventTask;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents a list that serves as the database for the tasks.
 * Prints responses in response to commands as well.
 * Index starts from O, but will be printed as starting from 1 in showList().
 */
public class ToDoList {

    public static final String HORIZONTAL_LINE =
            "____________________________________________________________";
    ArrayList<Task> tasks;

    public ToDoList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    /**
     * Creates a new ToDoTask and adds it to the toDoList.
     * Prints message to inform user that task has been added.
     *
     * @param description name of the task
     */
    public void createToDoTask(String description) {
        ToDoTask toDo = new ToDoTask(description);
        tasks.add(toDo);
    }

    /**
     * Creates a new DeadlineTask and adds it to the toDoList.
     * Prints message to inform user that task has been added.
     *
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
     * Prints message to inform user that task has been added.
     *
     * @param description name of the task
     * @param startTime the time that the event starts
     * @param endTime the time that the event ends
     */
    public void createEventTask(String description, String startTime, String endTime)
            throws DateTimeParseException, IllegalStartAndEndDateException {
        Task event = new EventTask(description, startTime, endTime);
        tasks.add(event);
        System.out.println(HORIZONTAL_LINE + "\n"
                + "I have added the following Event to your list: "+ description + "\n"
                + HORIZONTAL_LINE);
    }

    /**
     * Prints the index and string representation of every task in the toDoList.
     * Index starts from 0 in ArrayList, but will be printed out as starting from 1.
     */
    public void showList() {
        System.out.println(HORIZONTAL_LINE + "\n");
        tasks.forEach((task) ->
                System.out.println(tasks.indexOf(task) + 1 + ". " + task.toString() + "\n"));
        System.out.println(HORIZONTAL_LINE + "\n");
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
    public void deleteTask(int index) throws IndexOutOfBoundsException {
        tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getLength() {
        return this.tasks.size();
    }

    @Override public String toString() {
        StringBuilder temp = new StringBuilder();
        for (Task task : tasks) {
            temp.append(task.toString()).append("\n");
        }
        return temp.toString();
    }
}

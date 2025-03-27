package qwerty.ui;

import java.util.ArrayList;
import java.util.List;

import qwerty.exceptions.BotException;
import qwerty.exceptions.IncorrectFormatException;
import qwerty.exceptions.InvalidIndexException;
import qwerty.task.Deadline;
import qwerty.task.Event;
import qwerty.task.Loan;
import qwerty.task.Task;
import qwerty.task.ToDo;

/**
 * The TaskList class manages a list of tasks.
 * It allows for adding, removing, marking, and unmarking tasks.
 * It also prints the current task list and provides status updates.
 */
public class TaskList {

    private ArrayList<Task> list; // List to hold tasks.

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param list The initial list of tasks.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return An ArrayList of tasks.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Prints the tasks in the list. If the list is empty, informs the user.
     */
    public void printList() {
        if (list.isEmpty()) {
            System.out.println("Surprise, surprise! There is nothing in your list!");
        } else {
            System.out.println("Here are the tasks in your never-ending list:");
            for (int i = 0; i < list.size(); i++) {
                int index = i + 1;
                System.out.println(index + ". " + list.get(i));
            }
        }
    }

    /**
     * Prints the status of the task list, showing the current number of tasks.
     */
    public void printListStatus() {
        System.out.println("You now have " + list.size() + " task" + (list.size() == 1 ? "" : "s")
            + ", waiting to probably be ignored.");
    }

    /**
     * Adds a ToDo task to the list.
     *
     * @param desc The description of the task.
     * @throws BotException If the task description is invalid.
     */
    public void addToDoToList(String desc) throws BotException {
        Task newTask = new ToDo(desc);
        list.add(newTask);
        System.out.println("Ok. I've added this todo:");
        System.out.println(newTask + "\n");
        printListStatus();
    }

    /**
     * Adds a Deadline task to the list.
     *
     * @param desc The description of the task, including the deadline.
     * @throws BotException If the task description is incorrectly formatted.
     */
    public void addDeadlineToList(String desc) throws BotException {
        String[] parts = desc.split(" /by ");
        if (parts.length != 2) {
            throw new IncorrectFormatException("deadline");
        }
        Task newTask = new Deadline(parts[0], parts[1]);
        list.add(newTask);
        System.out.println("Fine, I've added this deadline:");
        System.out.println(newTask + "\n");
        printListStatus();
    }

    /**
     * Adds an Event task to the list.
     *
     * @param desc The description of the task, including the event's start and end time.
     * @throws BotException If the task description is incorrectly formatted.
     */
    public void addEventToList(String desc) throws BotException {
        String[] parts = desc.split(" /from | /to ");
        if (parts.length != 3) {
            throw new IncorrectFormatException("event");
        }
        Task newTask = new Event(parts[0], parts[1], parts[2]);
        list.add(newTask);
        System.out.println("Fine, I've added this event:");
        System.out.println(newTask + "\n");
        printListStatus();
    }

    /**
     * Adds an Loan task to the list.
     *
     * @param desc The description of the task.
     * @throws BotException If the task description is incorrectly formatted.
     */
    public void addLoanToList(String desc) throws BotException {
        String[] parts = desc.split(" /from | /to ");
        if (parts.length != 3) {
            throw new IncorrectFormatException("loan");
        }
        Task newTask = new Loan(parts[0], parts[1], parts[2]);
        list.add(newTask);
        System.out.println("Fine, I've added this loan:");
        System.out.println(newTask + "\n");
        printListStatus();
    }

    /**
     * Validates the given index, ensuring it is within the bounds of the task list.
     *
     * @param index The index to validate.
     * @return The validated index.
     * @throws BotException If the index is invalid.
     */
    public int validateIndex(int index) throws BotException {
        if ((index < 0) || (index >= list.size())) {
            throw new InvalidIndexException();
        }
        return index;
    }

    /**
     * Marks a task as done by its index.
     *
     * @param i The index of the task to mark as done.
     * @throws BotException If the index is invalid.
     */
    public void markAsDone(int i) throws BotException {
        i = validateIndex(i);
        Task task = list.get(i);
        task.markTaskDone();
        list.set(i, task);
        System.out.println("Took you long enough!\n\nI've marked this task as done:");
        System.out.println(list.get(i));
    }

    /**
     * Unmarks a task as done by its index.
     *
     * @param i The index of the task to unmark.
     * @throws BotException If the index is invalid.
     */
    public void unmarkAsDone(int i) throws BotException {
        i = validateIndex(i);
        Task task = list.get(i);
        task.unmarkTaskDone();
        list.set(i, task);
        System.out.println("Jumped the gun, huh?\n\nI've marked this task as not done yet:");
        System.out.println(list.get(i));
    }

    /**
     * Deletes a task from the list by its index.
     *
     * @param i The index of the task to delete.
     * @throws BotException If the index is invalid.
     */
    public void deleteTask(int i) throws BotException {
        i = validateIndex(i);
        Task task = list.get(i);
        list.remove(i);
        System.out.println("I've removed this task:");
        System.out.println(task + "\n");
        if (!task.isDone()) {
            System.out.println("Not like you were going to finish it anyway.\n");
        }
        printListStatus();
    }

    /**
     * Prints a filtered list of tasks that contain the specified keyword.
     *
     * @param keyword The keyword to search for in the task list.
     */
    public void printFilteredList(String keyword) {

        // Filter the tasks where the toString() representation contains the keyword
        List<Task> filteredTasks = this.list.stream()
                .filter(task -> task.toString().contains(keyword))
                .toList();

        // Print the results
        if (filteredTasks.isEmpty()) {
            System.out.println("No tasks found containing the keyword: " + keyword);
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < filteredTasks.size(); i++) {
                Task task = filteredTasks.get(i);
                int index = i + 1;
                System.out.println(index + ". " + task.toString());
            }
        }
    }

}

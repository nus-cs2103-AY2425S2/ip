package steve.tasks;

import java.util.ArrayList;
import java.util.List;

import steve.exceptions.InvalidTaskNumberException;

/**
 * Manages a collection of tasks.
 * This class is responsible for adding tasks, marking tasks as done or undone,
 * deleting tasks, and retrieving the list of tasks.
 */
public class TaskManager {
    private static ArrayList<Task> tasks;
    private static ArrayList<Contact> clients;

    /**
     * Constructs a TaskManager with an initial list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskManager with.
     */
    public TaskManager(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.clients = new ArrayList<Contact>();
    }

    /**
     * Returns number of tasks
     */
    public static int getTaskSize() {
        return tasks.size();
    }

    /**
     * Validates user input of task number.
     */
    public boolean validTaskNumber(int taskNum) {
        assert taskNum > 0 : "Task number cannot be less than 0";
        assert taskNum <= tasks.size() : "Task number out of range";
        return taskNum > 0 && taskNum <= tasks.size();
    }

    /**
     * Adds a task to the task manager if it is valid.
     *
     * @param task The task to be added into the task list.
     */
    public void addTask(Task task) {
        if (task.isValid()) {
            tasks.add(task);
            Task.saveTasks(task);
        }
    }

    /**
     * Adds a contact to the client list
     *
     * @param contact The contact to be added.
     */
    public void addContact(Contact contact) {
        clients.add(contact);
    }

    /**
     * Marks a task as done or undone based on the specified task number.
     *
     * @param taskNumber The number of the task to mark as done.
     * @param isDone     True to mark the task as done, false to unmark it.
     * @throws InvalidTaskNumberException If the task number is invalid.
     */
    public void markTask(int taskNumber, boolean isDone) throws InvalidTaskNumberException {
        if (validTaskNumber(taskNumber)) {
            Task task = tasks.get(taskNumber - 1);
            String result = isDone ? "mark" : "unmark";
            if (task.isDone() == isDone) {
                System.out.println("Task " + taskNumber + " is already " + result);
                return;
            }
            task.markDone(isDone);
            task.message(result);
        } else {
            throw new InvalidTaskNumberException("Invalid task number: " + taskNumber);
        }
    }

    /**
     * Deletes a task based on the specified task number.
     *
     * @param taskNumber The number of the task to delete.
     * @throws InvalidTaskNumberException If the task number is invalid.
     */
    public void deleteTask(int taskNumber) throws InvalidTaskNumberException {
        if (validTaskNumber(taskNumber)) {
            tasks.remove(taskNumber - 1);
            Task.saveAllTasks(tasks);
        } else {
            throw new InvalidTaskNumberException("Invalid task number: " + taskNumber);
        }
    }

    /**
     * Retrieves the list of tasks managed by the TaskManager.
     *
     * @return A list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Retrieves the list of contacts managed by the Client array list.
     *
     * @return A list of contacts.
     */
    public List<Contact> getClients() {
        return clients;
    }
}

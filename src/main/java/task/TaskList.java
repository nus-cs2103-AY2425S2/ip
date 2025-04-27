package task;

import java.util.ArrayList;

import dew.DewException;

/**
 * The TaskList class manages a collection of tasks, allowing addition, deletion,
 * marking, unmarking, and listing of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with a predefined list of tasks.
     *
     * @param tasks The list of tasks to initialize with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list and prints a confirmation message.
     *
     * @param task The task to be added.
     */
    public String addTask(Task task) {
        StringBuilder sb = new StringBuilder();
        tasks.add(task);
        sb.append("Got it. I've added this task:\n");
        sb.append(task).append("\n");
        sb.append("You now have ").append(tasks.size()).append(" tasks in the list.");
        return sb.toString();
    }

    /**
     * Deletes a task from the list by index and prints a confirmation message.
     *
     * @param index The index of the task to be removed.
     */
    public String deleteTask(int index) {
        if (tasks.isEmpty()) {
            return "There is no tasks in the list";
        } else if (index < 0 || index >= tasks.size()) {
            return "Please select a task from the list";
        }
        Task removedTask = tasks.remove(index);
        return "Noted. I've removed this task:\n   " + removedTask;
    }

    /**
     * Marks a task as completed by index and prints a confirmation message.
     *
     * @param index The index of the task to be marked.
     * @return A confirmation message indicating the task has been marked.
     * @throws DewException If the task index is out of bounds.
     */
    public String markTask(int index) throws DewException {
        if (index < 0 || index >= tasks.size()) {
            throw new DewException("Invalid task index! Task number does not exist.");
        }

        tasks.get(index).markStatusIcon();
        return "Nice! I've marked this task as done:\n   " + tasks.get(index);
    }

    /**
     * Unmarks a task, setting it as incomplete, and prints a confirmation message.
     *
     * @param index The index of the task to be unmarked.
     * @return A confirmation message indicating the task has been unmarked.
     * @throws DewException If the task index is out of bounds.
     */
    public String unmarkTask(int index) throws DewException {
        if (index < 0 || index >= tasks.size()) {
            throw new DewException("Invalid task index! Task number does not exist.");
        }

        tasks.get(index).unMarkStatusIcon();
        return "OK, I've marked this task as not done yet:\n   " + tasks.get(index);
    }


    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Lists all tasks in the task list, displaying them with their index.
     */
    public String listTasks() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:" + "\n");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
            sb.append((i + 1) + "." + tasks.get(i) + "\n");
        }
        return sb.toString();
    }

    /**
     * Finds and displays tasks that contain the specified keyword.
     *
     * @param keyword The keyword to search for.
     */
    public String findTasks(String keyword) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        System.out.println("Here are the matching tasks in your list:");
        int index = 1;
        for (Task task : tasks) {
            if (task.toString().contains(keyword)) {
                System.out.println(index + "." + task);
                sb.append(index + "." + task + System.lineSeparator());
            }
            index++;
        }
        return sb.toString();
    }

    /**
     * Returns the number of tasks currently in the task list.
     *
     * @return The total count of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Retrieves a task by its index.
     *
     * @param taskIndex The index of the task to retrieve (zero-based index).
     * @return The Task object at the specified index.
     * @throws IndexOutOfBoundsException If the index is invalid.
     */
    public Task get(int taskIndex) {
        return tasks.get(taskIndex);
    }
}

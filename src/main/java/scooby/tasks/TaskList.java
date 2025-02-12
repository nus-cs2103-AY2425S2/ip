package scooby.tasks;

import java.util.ArrayList;
import java.util.stream.IntStream;

import scooby.ui.Storage;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>(); // Dynamic list to hold tasks
    private static final Storage storage = new Storage();

    /**
     * Loads tasks from the tasks.txt file if it exists.
     */
    public void loadFromFile() {
        this.tasks = storage.loadFromFile();
        assert tasks != null : "Error: Loaded tasks list is null!";
    }

    /**
     * Adds a task to the list
     *
     * @param command the name of the chatbot.
     */
    public String addTask(String command) {
        assert command != null && !command.isEmpty() : "Error, command cannot be empty";

        Task newTask;
        if (command.startsWith("todo ")) {
            String description = command.substring(5).trim();
            assert !description.isEmpty() : "Error: ToDo description cannot be empty!";

            newTask = new ToDo(description);
        } else if (command.startsWith("deadline ")) {
            String[] parts = command.substring(9).split(" /by ");
            String description = parts[0].trim();
            assert parts.length == 2 : "Error: Incorrect Deadline format!";

            String by = parts[1];
            newTask = new Deadline(description, by);
        } else if (command.startsWith("event ")) {
            String[] parts = command.substring(6).split(" /from | /to ");
            String description = parts[0].trim();
            assert parts.length == 3 : "Error: Incorrect Event format!";

            String from = parts[1];
            String to = parts[2];
            newTask = new Event(description, from, to);
        } else {
            return "I'm sorry, but I don't know what that means.";
        }
        
        if (tasks.contains(newTask)) {
            return "Task already exists in the list:\n " + newTask;
        }

        tasks.add(newTask); // Add task to ArrayList
        assert tasks.contains(newTask) : "Error: Task was not added successfully!";

        return "Got it. I've added this task:\n " + newTask + "\nNow you have "
                + tasks.size() + " tasks in the list.";
    }

    /**
     * Lists down all the tasks in the list.
     */
    public String listTasks() {
        return IntStream.range(0, tasks.size())
                .mapToObj(index -> (index + 1) + ". " + tasks.get(index).toString() + "\n")
                .reduce("", (task1, task2) -> task1 + task2);
    }

    /**
     * Updates a task's details in the task list.
     *
     * @param index the index of the task to update.
     * @param newDetails the new details to update the task with.
     * @return a message indicating the result of the update.
     */
    public String updateTask(int index, String newDetails) {
        if (index < 0 || index >= tasks.size()) {
            return "Invalid task index. Please try again.";
        }

        Task taskToUpdate = tasks.get(index);

        if (taskToUpdate instanceof ToDo todo) {
            todo.updateDetails(newDetails);
        } else if (taskToUpdate instanceof Deadline deadline) {
            String[] parts = newDetails.split(" /by ", 2);
            if (parts.length < 2) {
                return "Error: Invalid update format. Use: <new description> /by <new deadline>";
            }
            deadline.updateDetails(parts[0], parts[1]);
        } else if (taskToUpdate instanceof Event event) {
            String[] parts = newDetails.split(" /from | /to ", 3);
            if (parts.length < 3) {
                return "Error: Invalid update format. Use: <new description> /from <start time> /to <end time>";
            }
            event.updateDetails(parts[0], parts[1], parts[2]);
        } else {
            return "Error: Unsupported task type.";
        }

        // Save updated tasks to file
        saveToFile();

        return "Task updated successfully:\n" + taskToUpdate;
    }


    /**
     * Gets a task from the task list
     *
     * @param index is the index of the task from the task list.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Marks a task as completed.
     *
     * @param index is the index of the task from the task list.
     */
    public void checkTask(int index) {
        tasks.get(index).setChecked();
    }

    /**
     * Unmarks a task from the task list.
     *
     * @param index is the index of the task from the task list.
     */
    public void uncheckTask(int index) {
        tasks.get(index).setUnchecked();
    }

    /**
     * Deletes a task from the list
     *
     * @param index is the index of the task from the task list.
     */
    public String deleteTask(int index) {
        // Ensure index is within bounds
        if (index >= 0 && index < tasks.size()) {
            Task deletedTask = tasks.get(index);
            tasks.remove(index); // Remove task from ArrayList
            return "Noted. I've removed this task:\n " + deletedTask + "\nNow you have "
                    + tasks.size() + " tasks in the list.";
        } else {
            return "Invalid task index. Please try again.";
        }
    }

    /**
     * Finds and lists tasks that contain the given keyword.
     *
     * @param keyword The keyword to search for.
     */
    public String find(String keyword) {
        String response = this.tasks.stream()
                .filter(task -> task.toString().toLowerCase().contains(keyword.toLowerCase()))
                .map(task -> (tasks.indexOf(task) + 1) + ". " + task.toString())
                .reduce("",
                        (task1, task2) -> task1 + "\n" + task2);
        return response.isEmpty() ? "No matching tasks found" :
                "Here are the matching tasks in your list:\n" + response;
    }

    /**
     * Saves the content in the task list to a file.
     */
    public void saveToFile() {
        storage.saveToFile(this.tasks);
    }

    /**
     * returns whether the list is empty or not
     * returns True when it is empty
     * returns False when it is not empty
     *
     * @return returns a boolean value whether the list is empty or not
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Returns the size of the taskList
     *
     * @return returns the size of the taskList
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns whether the task is inside the task list
     *
     * @param task is the task to be checked
     * @return returns whether the task is inside the task list or not
     */
    public boolean containsTask(Task task) {
        return this.tasks.contains(task);
    }
}

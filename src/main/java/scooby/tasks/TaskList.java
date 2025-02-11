package scooby.tasks;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;

import scooby.exception.UnrecognisableException;
import scooby.exception.EmptyException;
import scooby.ui.Line;
import scooby.ui.Storage;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>(); // Dynamic list to hold tasks
    private static final Line LINE = new Line();
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

        tasks.add(newTask); // Add task to ArrayList
        assert tasks.contains(newTask) : "Error: Task was not added successfully!";

        // LINE.print();
        return "Got it. I've added this task:\n " + newTask + "\nNow you have "
                + tasks.size() + " tasks in the list.";
        // LINE.print();
    }

    /**
     * Lists down all the tasks in the list.
     */
    public String listTasks() {
        // LINE.print();
        String response = "";
        if (tasks.size() == 0) {
            return "No tasks in the list.";
        } else {
            assert tasks.size() > 0;
            for (int i = 0; i < tasks.size(); i++) {
                response += ((i + 1) + ". " + tasks.get(i) + "\n");
            }
        }
        return response;
        // LINE.print();
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
            // LINE.print();
            return "Noted. I've removed this task:\n " + deletedTask + "\nNow you have "
                    + tasks.size() + " tasks in the list.";
            // LINE.print();
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
        // LINE.print();
        String response = "";
        ArrayList<Task> foundTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                foundTasks.add(task);
            }
        }

        if (foundTasks.isEmpty()) {
            return "No matching tasks found.";
        } else {
            assert foundTasks.size() > 0;
            response += "Here are the matching tasks in your list:\n";
            for (int i = 0; i < foundTasks.size(); i++) {
                response += ((i + 1) + ". " + foundTasks.get(i));
            }
        }
        return response;
        // LINE.print();
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
     * @return returns a boolean value of
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
}

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

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>(); // Dynamic list to hold tasks
    private static final Line LINE = new Line();

    /**
     * Loads tasks from the tasks.txt file if it exists.
     */
    public void loadFromFile() {
        tasks.clear();
        File file = new File("tasks.txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // Parse the task and add it to the task list
                    Task task = parseTask(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                }
            } catch (IOException e) {
                System.err.println("An error occurred while loading tasks: " + e.getMessage());
            }
        }
    }

    /**
     * Parses a task string and creates the appropriate Task object.
     *
     * @param line the string representation of a task from tasks.txt.
     * @return the Task object, or null if the line is invalid.
     */
    private Task parseTask(String line) {
        try {
            // Identify task type from the string format, e.g., "[T][ ] description"
            char taskType = line.charAt(1); // T for ToDo, D for Deadline, E for Event
            boolean isDone = line.charAt(4) == 'X'; // X indicates task is marked as done

            if (taskType == 'T') {
                // Format: [T][ ] description
                String description = line.substring(7);
                ToDo todo = new ToDo(description);
                if (isDone) {
                    todo.setChecked();
                }
                return todo;
            } else if (taskType == 'D') {
                // Format: [D][ ] description (by: time)
                int byIndex = line.indexOf("(by: ");
                String description = line.substring(7, byIndex - 1);
                String by = line.substring(byIndex + 5, line.length() - 1);
                Deadline deadline = new Deadline(description, by);
                if (isDone) {
                    deadline.setChecked();
                }
                return deadline;
            } else if (taskType == 'E') {
                // Format: [E][ ] description (from: time1 to: time2)
                int fromIndex = line.indexOf("(from: ");
                int toIndex = line.indexOf(" to: ");
                String description = line.substring(7, fromIndex - 1);
                String from = line.substring(fromIndex + 7, toIndex);
                String to = line.substring(toIndex + 5, line.length() - 1);
                Event event = new Event(description, from, to);
                if (isDone) {
                    event.setChecked();
                }
                return event;
            }
        } catch (Exception e) {
            System.err.println("Error parsing task: " + line);
        }
        return null;
    }

    /**
     * Adds a task to the list
     *
     * @param command the name of the chatbot.
     */
    public void addTask(String command) {
        Task newTask;
        try {
            if (command.startsWith("todo ")) {
                String description = command.substring(5).trim();
                if (description.isEmpty()) {
                    throw new EmptyException("Description of a task cannot be empty. Try again");
                }
                newTask = new ToDo(description);
            } else if (command.startsWith("deadline ")) {
                String[] parts = command.substring(9).split(" /by ");
                String description = parts[0].trim();
                if (description.isEmpty()) {
                    throw new EmptyException("Description of a task cannot be empty. Try again");
                }
                String by = parts[1];
                newTask = new Deadline(description, by);
            } else if (command.startsWith("event ")) {
                String[] parts = command.substring(6).split(" /from | /to ");
                String description = parts[0].trim();
                if (description.isEmpty()) {
                    throw new EmptyException("Description of a task cannot be empty. Try again");
                }
                String from = parts[1];
                String to = parts[2];
                newTask = new Event(description, from, to);
            } else {
                throw new UnrecognisableException("I'm sorry, but I don't know what that means.");
            }

            tasks.add(newTask); // Add task to ArrayList
            LINE.print();
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + newTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            LINE.print();

        } catch (EmptyException | UnrecognisableException e) {
            LINE.print();
            System.out.println(e.getMessage());
            LINE.print();
        }
    }

    /**
     * Lists down all the tasks in the list.
     */
    public String listTasks() {
        // LINE.print();
        String response = "";
        if (tasks.size() == 0) {
            response = "No tasks in the list.";
        } else {
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
    public void deleteTask(int index) {
        // Ensure index is within bounds
        if (index >= 0 && index < tasks.size()) {
            Task deletedTask = tasks.get(index);
            tasks.remove(index); // Remove task from ArrayList
            LINE.print();
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + deletedTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            LINE.print();
        } else {
            System.out.println("Invalid task index. Please try again.");
        }
    }

    /**
     * Finds and lists tasks that contain the given keyword.
     *
     * @param keyword The keyword to search for.
     */
    public void find(String keyword) {
        LINE.print();
        ArrayList<Task> foundTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                foundTasks.add(task);
            }
        }

        if (foundTasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < foundTasks.size(); i++) {
                System.out.println((i + 1) + ". " + foundTasks.get(i));
            }
        }

        LINE.print();
    }

    /**
     * Saves the content in the task list to a file.
     */
    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("tasks.txt"))) {
            for (Task task : tasks) {
                writer.write(task.toRawString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("An error occurred while saving tasks: " + e.getMessage());
        }
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

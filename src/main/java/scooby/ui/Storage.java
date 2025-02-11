package scooby.ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import scooby.tasks.Deadline;
import scooby.tasks.Event;
import scooby.tasks.Task;
import scooby.tasks.ToDo;

public class Storage {
    private static final String FILELOCATION = "tasks.txt";

    /**
     * Loads tasks from the tasks.txt file if it exists.
     * @return List of tasks loaded from the file.
     */
    public ArrayList<Task> loadFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILELOCATION);

        if (!file.exists()) {
            return tasks; // Return empty list if file doesn't exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                assert !line.isEmpty() : "Error: Encountered an empty line in tasks file.";

                Task task = parseTask(line);
                assert task != null : "Error: Failed to parse task from line: " + line;
                tasks.add(task);
            }
        } catch (IOException e) {
            System.err.println("Error loading tasks: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Parses a task string and creates the appropriate Task object.
     * @param line The string representation of a task from tasks.txt.
     * @return The Task object, or null if the line is invalid.
     */
    private Task parseTask(String line) {
        try {
            char taskType = line.charAt(1); // T, D, or E
            boolean isDone = line.charAt(4) == 'X'; // X indicates completed task
            String description;
            Task task = null;

            switch (taskType) {
            case 'T' -> {
                description = line.substring(7);
                task = new ToDo(description);
            }
            case 'D' -> {
                int byIndex = line.indexOf("(by: ");
                assert byIndex > 7 : "Error: Invalid deadline format!";
                description = line.substring(7, byIndex - 1);
                String by = line.substring(byIndex + 5, line.length() - 1);
                task = new Deadline(description, by);
            }
            case 'E' -> {
                int fromIndex = line.indexOf("(from: ");
                int toIndex = line.indexOf(" to: ");
                assert fromIndex > 7 && toIndex > fromIndex : "Error: Invalid event format!";
                description = line.substring(7, fromIndex - 1);
                String from = line.substring(fromIndex + 7, toIndex);
                String to = line.substring(toIndex + 5, line.length() - 1);
                task = new Event(description, from, to);
            }
            default -> System.err.println("Unknown task type: " + taskType);
            }

            if (task != null && isDone) {
                task.setChecked();
            }
            return task;
        } catch (Exception e) {
            System.err.println("Error parsing task: " + line);
            return null;
        }
    }

    /**
     * Saves the task list to a file.
     * @param tasks List of tasks to be saved.
     */
    public void saveToFile(ArrayList<Task> tasks) {
        assert tasks != null : "Error: Task list cannot be null when saving.";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILELOCATION))) {
            for (Task task : tasks) {
                writer.write(task.toRawString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }
}


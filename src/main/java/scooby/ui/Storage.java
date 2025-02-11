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
     */
    public ArrayList<Task> loadFromFile() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        File file = new File(FILELOCATION);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    assert !line.isEmpty();
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
        assert tasks != null;
        return tasks;
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
     * Saves the content in the task list to a file.
     */
    public void saveToFile(ArrayList<Task> tasks) {
        assert tasks != null;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILELOCATION))) {
            for (Task task : tasks) {
                writer.write(task.toRawString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("An error occurred while saving tasks: " + e.getMessage());
        }
    }
}

package nickiminaj;

import nickiminaj.tasks.Deadline;
import nickiminaj.tasks.Event;
import nickiminaj.tasks.Task;
import nickiminaj.tasks.Todo;
import nickiminaj.tasks.ErrorTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String filePath; //stores the path to the file where tasks will be saved

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            //create the file and directories if they don't exist
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Task task = parseTask(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        }
        return tasks;
    }

    public void saveTasks(List<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.write(task.serialize());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];

        if (parts.length < 2) {
            return new ErrorTask("Invalid task format: " + line);
        }

        boolean isDone = parts[1].equals("1");
        switch (type) {
        case "T":
            if (parts.length < 3) {
                return new ErrorTask("Invalid Todo format: " + line);
            }
            return new Todo(parts[2], isDone);
            // Fallthrough
        case "D":
            if (parts.length < 4) {
                return new ErrorTask("Invalid Deadline format: " + line);
            }
            try {
                LocalDateTime deadline = LocalDateTime.parse(parts[3], DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
                return new Deadline(parts[2], deadline, isDone);
            } catch (DateTimeParseException e) {
                return new ErrorTask("Invalid date format for Deadline: " + line);
            }
        case "E":
            if (parts.length < 5) {
                return new ErrorTask("Invalid Event format: " + line);
            }
            try {
                LocalDateTime start = LocalDateTime.parse(parts[3], DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
                LocalDateTime end = LocalDateTime.parse(parts[4], DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
                return new Event(parts[2], start, end, isDone);
            } catch (DateTimeParseException e) {
                return new ErrorTask("Invalid date format for Event: " + line);
            }
            // Fallthrough
        default:
            return new ErrorTask("Unknown task type: " + type);
        }
    }
}
package taskmax.storage;

import taskmax.task.Task;
import taskmax.task.ToDo;
import taskmax.task.Deadline;
import taskmax.task.Event;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;

import java.time.format.DateTimeFormatter;


public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveTasks(List<Task> tasks) throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();

        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(serializeTask(task) + System.lineSeparator());
            }
        }
    }

    public List<Task> loadTasks() throws IOException {
        File file = new File(filePath);
        List<Task> tasks = new ArrayList<>();

        if (!file.exists()) {
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = deserializeTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        }
        return tasks;
    }

    private String serializeTask(Task task) {
        String typeCode = "";
        String extraData = "";

        if (task instanceof ToDo) {
            typeCode = "T";
        } else if (task instanceof Deadline) {
            typeCode = "D";
            extraData = " | " + ((Deadline) task).getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } else if (task instanceof Event) {
            typeCode = "E";
            extraData = " | " + ((Event) task).getStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                    + " | " + ((Event) task).getEnd().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        }

        return typeCode + " | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription() + extraData;
    }

    private Task deserializeTask(String line) {
        try {
            String[] parts = line.split(" \\| ");
            if (parts.length < 3) {
                System.out.println("WARNING: Skipping invalid task format! " + line);
                return null;
            }

            String typeCode = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            Task task;
            switch (typeCode) {
                case "T":
                    task = new ToDo(description);
                    break;
                case "D":
                    task = new Deadline(description, parts[3]);
                    break;
                case "E":
                    task = new Event(description, parts[3], parts[4]);
                    break;
                default:
                    return null;
            }

            if (isDone) task.markAsDone();
            return task;
        } catch (Exception e) {
            System.out.println("WARNING: Error parsing task! " + line);
            return null;
        }
    }
}



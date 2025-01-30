import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveTasks(List<Task> tasks) throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();

        FileWriter writer = new FileWriter(filePath);
        for (Task task : tasks) {
            writer.write(serializeTask(task) + System.lineSeparator());
        }
        writer.close();
    }

    public List<Task> loadTasks() throws IOException {
        File file = new File(filePath);
        List<Task> tasks = new ArrayList<>();

        if (!file.exists()) {
            return tasks;
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            Task task = deserializeTask(line);
            if (task != null) {
                tasks.add(task);
            }
        }
        reader.close();
        return tasks;
    }

    private String serializeTask(Task task) {
        String typeCode = "";
        String extraData = "";

        if (task instanceof ToDo) {
            typeCode = "T";
        } else if (task instanceof Deadline) {
            typeCode = "D";
            extraData = " | " + ((Deadline) task).dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } else if (task instanceof Event) {
            typeCode = "E";
            extraData = " | " + ((Event) task).start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                    + " | " + ((Event) task).end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        }

        return typeCode + " | " + (task.isDone ? "1" : "0") + " | " + task.description + extraData;
    }

    private Task deserializeTask(String line) {
        try {
            String[] parts = line.split(" \\| ");
            if (parts.length < 3) {
                System.out.println("WARNING: Skipping invalid task format!"
                        + "Please ask my creator for help!" + line);
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
                    if (parts.length < 4) return null;
                    String dateTimeString = parts[3];
                    task = new Deadline(description, dateTimeString);
                    break;
                case "E":
                    if (parts.length < 5) return null;
                    String startString = parts[3];
                    String endString = parts[4];
                    task = new Event(description, startString, endString);
                    break;
                default:
                    return null;
            }

            if (isDone) task.markAsDone();
            return task;
        } catch (Exception e) {
            System.out.println("WARNING: Error parsing task!"
                    + "Please ask my creator for help! " + line);
            return null;
        }
    }
}

import java.util.ArrayList;
import java.util.List;
import java.io.*;
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if(!file.exists()) {
            file.getParentFile().mkdirs();
            return tasks;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return tasks;
    }

    public void saveTasks(List<Task> tasks) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                bw.write(task.toFileFormat());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private Task parseTask(String line) {
        try {
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            Task task;
            switch (type) {
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

            if (isDone) {
                task.markDone();
            }
            return task;
        } catch (Exception e) {
            System.out.println("Skipping corrupt task entry: " + line);
            return null;
        }
    }


}

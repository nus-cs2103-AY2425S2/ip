package lebum.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import lebum.exception.DukeException;
import lebum.task.Deadline;
import lebum.task.Event;
import lebum.task.Task;
import lebum.task.ToDo;



/**
 * Class to load tasks and save tasks to txt file
 */
public class Storage {

    //store the file path to the txt file
    private String path;



    public Storage(String path) {
        this.path = path;
    }

    /**
     * Save tasks to storage file.
     * @param tasks the arraylists of tasks to save to txt file, before formatting
     */

    public void saveToFile(TaskList tasks) {
        try {
            // Create directories if they don't exist
            File directory = new File("data");
            directory.mkdirs();

            // Create and write to file
            FileWriter fw = new FileWriter(path);
            for (Task t : tasks.array()) {
                fw.write(formatTask(t));
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    /**
     * Format tasks to be saved to file as strings.
     * @param task to format accroding to txt file
     * @return the string of the formatted task
     */
    public String formatTask(Task task) {
        String type = task instanceof Deadline ? "D"
                    : task instanceof ToDo ? "T" : "E";
        String status = task.getStatus();
        String basic = String.format("%s | %s | %s", type, status, task.getTitle());

        if (task instanceof Deadline) {
            return basic + " | " + ((Deadline) task).getEndDateFile() + "\n";
        } else if (task instanceof Event) {
            return basic + " | " + ((Event) task).getStartDate() + " | " + ((Event) task).getEndDate() + "\n";
        }
        return basic + "\n";
    }

    /**
     * Load tasks from storage file.
     * @return the list of tasks parsed from the txt file to be loaded when user runs for the first time
     */
    public TaskList loadTasks() {
        TaskList tasks = new TaskList();
        try {
            File tasksFile = new File(path);
            //create the file if user is opening for the first time
            if (tasksFile.createNewFile()) {
                System.out.println("File created successfully!");
            }
            Scanner sc = new Scanner(tasksFile);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Task task = parseTasks(line);
                tasks.addInitialTask(task);
            }
            sc.close();
            return tasks;
        }
        catch (FileNotFoundException e) {
            return tasks;
        }
        catch (IOException e) {
            System.out.println("Creating file");
        }
        catch (DukeException e) {
            System.out.println("Error parsing tasks");
        }
        return tasks;
    }

    /**
     * Parse tasks from string.
     * @param line current line of txt file it is parsing
     * @return the task that the current txt line represents
     * @throws DukeException if tasks in txt file cant be parsed
     */

    public Task parseTasks(String line) throws DukeException {
        String[] parts = line.split(" \\| ");

        if (parts.length < 3) {
            throw new DukeException("Invalid task form!");
        }
        Task task = switch (parts[0]) {
            case "T" -> new ToDo(parts[2]);
            case "D" -> new Deadline(parts[2], parts[3]);
            case "E" -> new Event(parts[2], parts[3], parts[4]);
            default -> throw new DukeException("Unknown task type");
        };
        if (parts[1].equals("[X]")) {
            task.markDoneSilently();
        }
        return task;
    }

}

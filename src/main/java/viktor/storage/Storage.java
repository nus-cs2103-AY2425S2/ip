package viktor.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import viktor.tasks.DeadlineTask;
import viktor.tasks.EventTask;
import viktor.tasks.Task;
import viktor.tasks.TaskList;
import viktor.tasks.TodoTask;


/**
 * Handles the storage of tasks to and from a file.
 */
public class Storage {
    public static final String FILE_PATH = "./ip/data/viktor.txt";

    /**
     * Saves the tasks from the TaskList to a file.
     *
     * @param taskList The list of tasks to be saved.
     * @throws IOException If an I/O error occurs.
     */
    public static void save(TaskList taskList) throws IOException {
        File f = new File(FILE_PATH);
        f.getParentFile().mkdirs();
        f.createNewFile();
        try {
            FileWriter fw = new FileWriter(f);
            ArrayList<Task> tasks = new ArrayList<>(taskList.getTasks()); // Convert TaskList to ArrayList<Task>
            for (Task t : tasks) {
                fw.write(t.toSave() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new IOException("Error saving file");
        }
    }

    /**
     * Loads the tasks from the file.
     *
     * @return A list of tasks loaded from the file.
     * @throws IOException If an I/O error occurs.
     */
    public static ArrayList<Task> load() throws IOException {
        File f = new File(FILE_PATH);
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] parts = line.split(" \\| ");
                if (parts[0].equals("T")) {
                    tasks.add(new TodoTask(parts[2]));
                    if (parts[1].equals("X")) {
                        tasks.get(tasks.size() - 1).beDone();
                    }
                } else if (parts[0].equals("D")) {
                    tasks.add(new DeadlineTask(parts[2], parts[3]));
                    if (parts[1].equals("X")) {
                        tasks.get(tasks.size() - 1).beDone();
                    }
                } else if (parts[0].equals("E")) {
                    tasks.add(new EventTask(parts[2], parts[3], parts[4]));
                    if (parts[1].equals("X")) {
                        tasks.get(tasks.size() - 1).beDone();
                    }
                }
            }
            s.close();
            return tasks;

        } catch (FileNotFoundException e) {
            throw new IOException("File not found");
        }
    }
}

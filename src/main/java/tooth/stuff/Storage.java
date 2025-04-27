package tooth.stuff;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import tooth.command.FileR;
import tooth.exception.InvalidFileFormatException;
import tooth.task.Task;

/**
 *  Stores and loads data into a save.txt
 */
public class Storage {

    public Storage() {};

    /**
     * Save data into save.txt
     *
     * @param memory the tasklist containing all the tasks
     */
    public void save(TaskList memory) {
        try {
            File dir = new File("data");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File f = new File(dir, "save.txt");
            if (!f.exists()) {
                f.createNewFile();
            }

            try (FileWriter fw = new FileWriter(f)) {
                memory.forEach((t) -> {
                    try {
                        fw.append(t.serialize()).append("\n");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        } catch (IOException e) {
            throw new RuntimeException("Error: Unable to create or write to the save file.", e);
        }
    }

    /**
     * Load data from save.txt
     *
     * @param memory the tasklist containing all the tasks
     */
    public void load(TaskList memory) {
        try {
            File f = new File("data/save.txt");

            // If file does not exist, create an empty one and return
            if (!f.exists()) {
                f.getParentFile().mkdirs(); // Ensure "data/" directory exists
                f.createNewFile(); // Create empty save.txt
                System.out.println("Warning: [save.txt] not found.");
                return;
            }

            memory.clear();
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                try {
                    Task t = FileR.create(s.nextLine());
                    memory.add(t);
                } catch (InvalidFileFormatException e) {
                    System.out.println(e.getMessage());
                }
            }
            s.close();
        } catch (Exception e) {
            System.out.println("Error: Unable to load save file.");
        }
    }
}

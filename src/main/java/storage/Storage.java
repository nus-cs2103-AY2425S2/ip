package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import command.Command;
import exceptions.ElmachoException;
import parser.Parser;
import task.Task;
import task.Tasklist;
import ui.Ui;

/**
 * Handles loading and updating of  stored task database.
 * <p> This class loads the saved tasklist when the user starts the system a
 *      and updates the stored database upon exit. </p>
 */
public class Storage {

    private final String fileName;
    private final Ui ui;

    /**
     * Creates a Storage for the tasks
     * @param fileName Name of the file used to store tasks.
     */
    public Storage(String fileName) {
        this.fileName = fileName;
        this.ui = new Ui();
    }

    /**
     * Updates the stored tasklist to the latest added task.
     * @param tasklist The tasklist used to store tasks.
     */
    public void updateList(Tasklist tasklist) {
        ArrayList<Task> tasks = tasklist.getTasks();
        assert fileName != null : "File must not be null.";
        try (FileWriter writer = new FileWriter(this.fileName, false)) {
            for (Task task : tasks) {
                writer.write(task.getInfo() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Unable to update list");
        }
    }

    /**
     * Loads the data from the file when the chatbot starts up.
     * @return The tasklist stored.
     */
    public Tasklist load(Tasklist tasklist, Tasklist otherTasklist) {
        File file = new File(fileName);
        File parentDir = file.getParentFile();
        Parser parser = new Parser();

        // To create new Directory
        if (parentDir != null && !parentDir.exists()) {
            if (!parentDir.mkdirs()) {
                System.out.println("Unable to create directories: " + parentDir.getAbsolutePath());
                return tasklist;
            }
        }

        // To create a new file in that directory
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Unable to create new file: " + fileName);
                return tasklist;
            }
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Command command = parser.parse(line);

                assert command != null : "Command must not be null.";
                command.execute(tasklist, otherTasklist, this.ui);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file: " + fileName);
        } catch (ElmachoException e) {
            System.out.println(e.getMessage());
        }
        return tasklist;
    }
}

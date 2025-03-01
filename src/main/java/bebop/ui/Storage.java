package bebop.ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import bebop.task.Deadline;
import bebop.task.Event;
import bebop.task.TaskList;
import bebop.task.Todo;



/**
 * Storage class that loads and deloads task from TaskList to Bebop.txt.
 */

public class Storage {
    private final String fileName;

    /**
     * Storage Constructor.
     *
     * @param fileName fileName of the text file.
     */
    public Storage(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Loads files into taskList.
     *
     * @param tasks Takes in an empty taskList.
     * @return TaskList after adding all the items in Bebop.txt.
     * @throws IOException if the file could not be created.
     */
    public TaskList load(TaskList tasks) throws IOException {
        File directory = new File("data");

        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(directory, "Bebop.txt");
        FileWriter fw = new FileWriter(this.fileName, true);
        // check if the file has any words
        try {
            boolean c = file.createNewFile();
            if (c) {
                System.out.println("\tA new save file has been created");
            } else {
                System.out.println("\tSaved task are still remembered");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
        }

        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNextLine()) {
            String[] tempStr = fileScanner.nextLine().split(" \\| ");
            boolean isDone = !tempStr[1].equals(" [ ]");
            switch (tempStr[0]) {
            case "T":
                tasks.addTask(new Todo(tempStr[2], isDone));
                continue;
            case "D":
                tasks.addTask(new Deadline(tempStr[2], isDone, tempStr[3]));
                continue;
            case "E":
                tasks.addTask(new Event(tempStr[2], isDone, tempStr[3], tempStr[4]));
                continue;
            default:
                break;
            }
        }
        fw.close();
        return tasks;
    }

    /**
     * Loads file into TaskList in the end.
     *
     * @param tasks Takes in an empty taskList.
     * @throws IOException if the file could not be created.
     */

    public void deload(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(this.fileName, false);
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.getTask(i) instanceof Todo t) {
                fw.write("T | " + t.getStatus() + " | " + t.getDescription() + "\n");
            } else if (tasks.getTask(i) instanceof Deadline d) {
                fw.write("D | " + d.getStatus() + " | " + d.getDescription() + " | " + d.getStart() + "\n");
            } else {
                Event e = (Event) tasks.getTask(i);
                fw.write("E | " + e.getStatus() + " | " + e.getDescription() + " | " + e.getStart() + " | "
                        + e.getEnd() + "\n");
            }
        }
        fw.close();
    }

}

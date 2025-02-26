package Krypto.IO;
import java.io.*;

import java.util.ArrayList;
import java.util.Scanner;

import Krypto.Exceptions.KryptoExceptions;

import Krypto.Task.Task;

import Krypto.Utils.TaskList;
import Krypto.Utils.Parser;

/**
 * Manages reading and writing tasks to and from a file.
 */
public class Storage {
    private final File file;
    private final String pathName;
    private static final String FILE_WRITE_EXCEPTION = "File could not be written.";
    private static final String FILE_READ_EXCEPTION = "File could not be read.";
    private static final String FILE_NOT_CREATED_EXCEPTION = "Target file could not be created.";

    /**
     * Constructs a Storage object that handles file operations.
     *
     * @param pathName The path to the file where tasks are stored.
     * @throws KryptoExceptions If the file cannot be created or accessed.
     */
    public Storage(String pathName) throws KryptoExceptions {
        this.file = new File(pathName);
        this.pathName = pathName;
        if (!file.exists()) {
            System.out.println("File not found. Creating new file.....");
            createFile();
        }
        assert file.exists() : "File should exist after creation";
    }

    /**
     * Creates a new file and its parent directories if they do not exist.
     *
     * @throws KryptoExceptions If the file cannot be created.
     */
    private void createFile() throws KryptoExceptions {
        try {
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }
            file.createNewFile();
        } catch (IOException e) {
            throw new KryptoExceptions(FILE_NOT_CREATED_EXCEPTION);
        }
    }

    /**
     * Loads tasks from the file and returns them as a list.
     *
     * @return A list of tasks loaded from the file.
     * @throws KryptoExceptions If the file cannot be read.
     */
    public ArrayList<Task> load() throws KryptoExceptions {
        ArrayList<Task> tasks = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task newTask = Parser.parseStorage(line);
                tasks.add(newTask);
            }
        } catch (FileNotFoundException e) {
            throw new KryptoExceptions(FILE_READ_EXCEPTION);
        }
        return tasks;
    }

    /**
     * Stores the tasks to the file.
     *
     * @param tasks The list of tasks to store.
     * @throws KryptoExceptions If the file cannot be written to.
     */
    public void store(TaskList tasks) throws KryptoExceptions {
        int len = tasks.getLength();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathName))) {
            for (int i = 0; i < len; i++) {
                writer.write(tasks.getTask(i).toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new KryptoExceptions(FILE_WRITE_EXCEPTION);
        }
    }
}

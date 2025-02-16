package Acheron.Storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import Acheron.Tasks.TaskList;
import Acheron.Tasks.TaskWriter;

/**
 * The storage manager class is responsible for handling anything pertaining to
 * changing or reading from the saved file. It also created the required folder and file
 */
public class StorageManager {
    private String storagePath;

    /**
     * Constructor for the storage manager
     * @param storagePath The path where the saved file is saved to
     * @param taskList The task list object which will be populated with task from the saved file if any
     * @throws Exception Trhow an exception related to wrongly provided path or other exceptions
     */
    public StorageManager(String storagePath, TaskList taskList) throws Exception {
        this.storagePath = storagePath;

        if (Files.notExists(Path.of(storagePath).getParent())) {
            new File(String.valueOf(Path.of(storagePath).getParent())).mkdir();
        }

        if (Files.notExists(Path.of(storagePath))) {
            try {
                Path file = Paths.get(storagePath);
                Files.createFile(file);
            } catch (Exception e) {
                System.out.println("Failed to create folder. Check you file permissions");
                throw e;
            }
        }

        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(storagePath));
            String line = fileReader.readLine();
            while (line != null) {
                TaskWriter.createSavedTask(line, taskList);
                line = fileReader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    /**
     * Updates the saved file with all the existing task's contents
     * @param taskList The task list object contains all existing tasks
     * @throws Exception Throws any exception picked up in any sub methods
     */
    public void updateSavedFile(TaskList taskList) throws Exception {
        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(this.storagePath, false));
            fileWriter.write(taskList.getAllTasksContent());
            fileWriter.close();
        } catch (Exception e) {
            throw e;
        }
    }
}

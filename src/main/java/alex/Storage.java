package alex;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import alex.exceptions.CorruptDataException;
import alex.exceptions.InvalidTaskTypeException;
import alex.task.Deadline;
import alex.task.Event;
import alex.task.Task;
import alex.task.TaskList;
import alex.task.ToDo;

/**
 * Handles the operations involving changing the storage
 */
public class Storage {
    private String path = "./data/alex.txt";
    private File dataDir = new File("./data");
    private File dataFile;
    private Path filePath;


    public Storage(String path) {
        this.path = path;
        this.dataFile = new File(path);
        this.filePath = Paths.get(path);
    }

    private Task loadTaskEntry(String data) throws CorruptDataException {
        try {
            String[] fields = data.split(" \\| ");

            assert fields.length >= 3 : "Corrupt data: " + data;

            String type = fields[0];
            String status = fields[1];
            String content = fields[2];
            switch (type) {
            case "T": return new ToDo(content, status);
            case "D": return new Deadline(content, status, fields[3]);
            case "E": return new Event(content, status, fields[3], fields[4]);
            default: throw new InvalidTaskTypeException();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new CorruptDataException(e.getMessage());
        }
    }

    /**
     * Load all the tasks stored in the file directory
     * @return a TaskList containing all the tasks
     */
    public TaskList load() {
        try {
            ArrayList<Task> taskList = new ArrayList<>();
            Scanner scanner = new Scanner(dataFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                taskList.add(loadTaskEntry(line));
            }
            return new TaskList(taskList);
        } catch (FileNotFoundException e) {
            System.out.println("The data file is missing. Please relaunch the program.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new TaskList();
    }

    /**
     * Saves the string data into data file
     * @param data data in the storage format
     */
    public void saveData(String data) {
        try {
            if (!dataDir.exists()) {
                dataDir.mkdirs(); // Create the ./data directory if not exists
            }

            // Write data to the file
            FileWriter writer = new FileWriter(dataFile, true);
            writer.write(data);
            writer.close();

            System.out.println("Data saved to " + dataFile);
        } catch (IOException e) {
            System.err.println("An error occurred while saving data: " + e.getMessage());
        }
    }

    /**
     * Delete a line in file
     * @param index the index of the line starting from 1
     * @throws IOException
     */
    public void deleteLineFromFile(int index) throws IOException {
        ArrayList<String> lines = new ArrayList<>(Files.readAllLines(filePath));
        lines.remove(index - 1);

        Files.write(filePath, lines);
    }

    /**
     * Update the line of the index with a new String
     * @param index index of line counting from 1
     * @param updated the new string to replace the original line
     * @throws IOException
     */
    public void updateLineInFile(int index, String updated) throws IOException {

        assert index > 0 : "Index should be greater than 0";

        ArrayList<String> lines = new ArrayList<>(Files.readAllLines(filePath));
        lines.set(index - 1, updated);

        Files.write(filePath, lines);
    }
}

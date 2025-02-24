package runny.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import runny.RunnyException;
import runny.commands.Command;
import runny.parser.Parser;
import runny.task.Task;
import runny.task.TaskList;


/**
 * Handles the loading and saving of task data for Runny Chatbot.
 * Manages writing to and reading from a specified file.
 */
public class Storage {
    private final String filePath;

    /**
     * Creates a Storage instance with the specified file path.
     *
     * @param filePath The file path where task data is stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads task data from the specified file and returns a TaskList.
     *
     * @return The TaskList containing the loaded tasks.
     * @throws RunnyException If there is an issue loading the data.
     */
    public TaskList load() throws RunnyException {
        TaskList list = new TaskList();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String currLine;
            while ((currLine = reader.readLine()) != null && !currLine.isEmpty()) {
                String[] currentLine = currLine.split(" ", 2);
                String done = currentLine[0];
                String command = currentLine[1];
                Command c = Parser.parse(command);
                c.loadTask(list);
                if (done.equals("1")) {
                    list.get(list.size() - 1).markTask();
                }
            }
            return list;
        } catch (FileNotFoundException e) {
            createFile();
            return list;
        } catch (IOException e) {
            throw new RunnyException("OOPS!! I am unable to load the data in the specified file.");
        }
    }

    /**
     * Edits and saves task data to the specified file.
     *
     * @param taskList The TaskList containing tasks to be saved.
     * @throws RunnyException If there is an issue saving the data.
     */
    public void writeToFile(TaskList taskList) throws RunnyException {
        try {
            createFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false));
            for (Task t : taskList) {
                writer.write(t.save() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RunnyException("Unable to write to file");
        }
    }

    /**
     * Creates a file to save data based on the filepath if no existing file is found.
     * @throws RunnyException
     */
    public void createFile() throws RunnyException {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                throw new RunnyException("Unable to create file safely.");
            }
        }
    }
}

package laffy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the storage of the application.
 */
public class Storage {
    private final String filepath;
    private ArrayList<ArrayList<String>> tasksData;

    /**
     * Constructor for Storage.
     *
     * @param filepath The file path to the data file.
     * @throws IOException If an I/O error occurs.
     */
    public Storage(String filepath) throws IOException {
        this.filepath = filepath;
        loadData();
    }

    private static boolean isValidType(String type) {
        return type.equals("T") || type.equals("D") || type.equals("E");
    }

    private static boolean isValidDone(String done) {
        return done.equals("0") || done.equals("1");
    }

    private static boolean isValidDesc(String desc) {
        return !desc.isBlank() && !desc.isEmpty();
    }

    private static boolean isValidBy(String by) {
        return !by.isBlank() && !by.isEmpty();
    }

    private static boolean isValidFrom(String from) {
        return !from.isBlank() && !from.isEmpty();
    }

    private static boolean isValidTo(String to) {
        return !to.isBlank() && !to.isEmpty();
    }

    private ArrayList<String> parseTaskDataLine(String line) {
        String[] task = line.split(" \\| ");
        String type = task[0];
        String isDone = task[1];
        String desc = task[2];
        assert isValidType(type) && isValidDone(isDone) && isValidDesc(desc);

        ArrayList<String> taskData = new ArrayList<>();
        taskData.add(type);
        taskData.add(isDone);
        taskData.add(desc);

        switch (type) {
        case "T" -> {
            assert task.length == 3;
        }
        case "D" -> {
            assert task.length == 4;
            String by = task[3];
            assert isValidBy(by);
            taskData.add(by);
        }
        case "E" -> {
            assert task.length == 5;
            String from = task[3];
            String to = task[4];
            assert isValidFrom(from) && isValidTo(to);
            taskData.add(from);
            taskData.add(to);
        }
        default -> {
            taskData.clear();
        }
        }
        return taskData;
    }

    /**
     * Loads data from the file. Called once when the class is instantiated.
     * If the file does not exist, a new file is created.
     * If the file is empty, an empty ArrayList is created.
     * If the file is not empty, the data is loaded into the ArrayList.
     * If the data is invalid, the data is not loaded. Instead, an empty ArrayList will be used.
     * If an external source modifies the file's data, the data will not be reloaded.
     * Instead, the loaded data will be used, and overwrite the modified data when saved.
     *
     * @throws IOException If an I/O error occurs.
     */
    private void loadData() throws IOException {
        ArrayList<ArrayList<String>> tasksData = new ArrayList<>();
        try {
            File file = new File(this.filepath);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.isBlank() || line.isEmpty()) {
                    break;
                }
                tasksData.add(parseTaskDataLine(line));
            }
            sc.close();
            this.tasksData = tasksData;
            return;
        } catch (FileNotFoundException e) {
            System.out.println("File not found, creating new file");
            File file = new File(this.filepath);
            boolean mkdirSuccess = file.getParentFile().mkdirs();
            boolean fileSuccess = file.createNewFile();
        }
        this.tasksData = tasksData;
    }

    /**
     * Gets the data from the file.
     *
     * @return The data from the file.
     */
    public ArrayList<ArrayList<String>> getTasksData() {
        return this.tasksData;
    }

    /**
     * Saves data to the file.
     *
     * @param tasksData The data to be saved.
     */
    public void saveData(ArrayList<ArrayList<String>> tasksData) {
        try {
            File file = new File(this.filepath);
            FileWriter fileWriter = new FileWriter(file);
            for (ArrayList<String> taskData : tasksData) {
                String line = String.join(" | ", taskData);
                assert line.split(" \\| ").length == taskData.size();
                fileWriter.write(line + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
}

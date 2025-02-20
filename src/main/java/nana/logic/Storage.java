package nana.logic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the storage of tasks to and from a file.
 */
public class Storage {

    private String filepath;
    private static ArrayList<ArrayList<String>> tasks;

    /**
     * Constructs a new Storage instance with the specified file path.
     *
     * @param filepath the path of the file to store tasks
     */
    public Storage(String filepath) {
        assert filepath != null : "Filepath should not be null";
        this.filepath = filepath;
        tasks = new ArrayList<>();
    }

    /**
     * Loads tasks from the file.
     *
     * @return a list of tasks loaded from the file
     * @throws IOException if an I/O error occurs
     */
    public ArrayList<ArrayList<String>> load() throws IOException {
        File f = new File(this.filepath);
        Scanner scanner = new Scanner(f);
        while (scanner.hasNext()) {
            ArrayList<String> inputArrayList = new ArrayList<>();
            String input = scanner.nextLine().trim();
            String[] inputList = input.split("\\s+");
            for (String s: inputList) {
                inputArrayList.add(s);
            }
            tasks.add(inputArrayList);
        }
        return tasks;

    }

    /**
     * Updates the task file with the current list of tasks.
     */
    public static void updateTxt() {
        try {
            Path directory = Paths.get("./data/Nana.txt");
            if (!Files.exists(directory)) {
                throw new IOException("File not found");
            }
            FileWriter fw = new FileWriter("./data/Nana.txt");
            fw.write(TaskList.listTxt());
            fw.close();
        } catch (IOException e) {
            Ui.printIoException(e);
        }
    }

    /**
     * Deletes the task file.
     */
    public static void deleteTxt() {
        try {
            Path directory = Paths.get("./data/Nana.txt");
            Files.delete(directory);
        } catch (IOException e) {
            System.out.println("    ____________________________________________________________\n"
                    +
                    "     Exception: " + e.getMessage() + "\n"
                    +
                    "    ____________________________________________________________");
        }
    }
}

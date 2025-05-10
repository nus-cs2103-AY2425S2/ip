package botato;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import botato.task.Task;

/**
 * Manages storage-related methods. It offers a load functionality that loads a text file containing
 * a serialized ArrayList containing Task data, if the file exists. It offers a save functionality that creates a new
 * save file if it does not exist yet, or overwrites the existing file to serialize and save an ArrayList containing
 * Task data.
 */
public class Storage {
    /**
     * Deserializes data from the data.txt file if it exists into an ArrayList.
     * Returns new ArrayList instead if the file is not found or another error is encountered.
     * @return ArrayList containing saved {@link Task}s.
     */
    public static ArrayList<Task> load() {
        try (FileInputStream fileIn = new FileInputStream("./data/data.txt");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            Object obj = in.readObject();
            @SuppressWarnings("unchecked")
            ArrayList<Task> data = (ArrayList<Task>) obj;
            System.out.println("Data has been loaded!");
            return data;
        } catch (Exception e) {
            System.out.println("Save file not found or encountered an unexpected error!");
            return new ArrayList<>();
        }
    }

    /**
     * Checks if a data directory exists, and creates one if it does not.
     * Serializes and saves taskArrayList into './data/data.txt'.
     * @param taskArrayList containing tasks to be saved locally.
     */
    public static String save(ArrayList<Task> taskArrayList) {
        String directoryPath = "./data/";
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try (FileOutputStream fileOut = new FileOutputStream("./data/data.txt");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(taskArrayList);
            System.out.println("Data has been saved!");
            return "Data has been saved!";
        } catch (Exception e) {
            System.out.println("An error was encountered and your data was unable to be saved...");
            return "An error was encountered and your data was unable to be saved!";
        }
    }
}

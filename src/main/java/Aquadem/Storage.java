package Aquadem;

import java.io.*;
import java.util.ArrayList;

/**
 * Class that encapsulates the storage functionality of the chatbot
 */
public class Storage {
    /**
     * The default constructor for the Storage class
     */
    public Storage() {

    }

    /**
     * Alternate constructor taking in a filepath to change default load/save file
     * @param path
     */
    public Storage(String path) {
        this.FILE_PATH = path;
    }
    private String FILE_PATH = "./src/main/data/Aquadem.ser";

    /**
     * Saves given tasklist to local file using ObjectStream and FileStream
     * @param tasks
     */
    public void saveTasks(TaskList tasks){
        try {
            File dataList = new File(FILE_PATH);
            if(dataList.createNewFile()) {
                //
            } else {
                //
            }
            FileOutputStream fileSerialized = new FileOutputStream(FILE_PATH);
            ObjectOutputStream taskSerialized = new ObjectOutputStream(fileSerialized);
            taskSerialized.writeObject(tasks);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            //
        }
    }

    /**
     * Loads a tasklist from local file using ObjectStream and FileStream
     * @return the looaded tasklist of type Tasklist
     */
    public TaskList loadTasks(){
        try {
            FileInputStream fileDeSerialized = new FileInputStream(FILE_PATH);
            ObjectInputStream taskDeSerialized = new ObjectInputStream(fileDeSerialized);
            TaskList tasks = (TaskList) taskDeSerialized.readObject();
            return tasks;

        } catch (IOException | ClassNotFoundException e) {
            return new TaskList();
        }
    }
}

package Aquadem;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    public Storage() {

    }

    public Storage(String path) {
        this.FILE_PATH = path;
    }
    private String FILE_PATH = "./src/main/data/Aquadem.ser";
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
            //
        }
    }

    public TaskList loadTasks(){
        try {
            FileInputStream fileDeSerialized = new FileInputStream(FILE_PATH);
            ObjectInputStream taskDeSerialized = new ObjectInputStream(fileDeSerialized);
            ArrayList<Task> tasks = (ArrayList<Task>) taskDeSerialized.readObject();
            return new TaskList(tasks);

        } catch (IOException | ClassNotFoundException e) {

            return new TaskList();
        }
    }
}

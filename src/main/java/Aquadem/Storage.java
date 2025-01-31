package Aquadem;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    public Storage() {

    }

    public Storage(String path) {
        this.filePath = path;
    }
    private String filePath = "./src/main/data/Aquadem.ser";
    public void saveTasks(TaskList tasks){
        try {
            File dataList = new File(this.filePath);
            if(dataList.createNewFile()) {
                //
            } else {
                //
            }
            FileOutputStream fileSerialized = new FileOutputStream(this.filePath);
            ObjectOutputStream taskSerialized = new ObjectOutputStream(fileSerialized);
            taskSerialized.writeObject(tasks);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            //
        }
    }

    public TaskList loadTasks(){
        try {
            FileInputStream fileDeSerialized = new FileInputStream(this.filePath);
            ObjectInputStream taskDeSerialized = new ObjectInputStream(fileDeSerialized);
            TaskList tasks = (TaskList) taskDeSerialized.readObject();
            return tasks;

        } catch (IOException | ClassNotFoundException e) {
            return new TaskList();
        }
    }
}

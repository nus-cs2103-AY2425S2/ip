package aquadem;

import java.io.*;

/**
 * Class that encapsulates the storage functionality of the chatbot.
 */
public class Storage {
    /**
     * Constructs an instance for the Storage class.
     */
    public Storage() {

    }

    /**
     * Constructs an instance of the Storage Class taking
     * in a filepath to change default load/save file.
     * @param path
     */
    public Storage(String path) {
        this.filePath = path;
    }

    private String home = System.getProperty("user.home");
    private String filePath = home + "/Aquadata/Aquadem.ser";

    /**
     * Saves given tasklist to local file using ObjectStream and FileStream.
     * @param tasks
     */
    public void saveTasks(TaskList tasks){
        try {

            File dataList = new File(this.filePath);
            dataList.getParentFile().mkdirs();
            if(dataList.createNewFile()) {
                //
            } else {
                //
            }
            FileOutputStream fileSerialized = new FileOutputStream(this.filePath);
            ObjectOutputStream taskSerialized = new ObjectOutputStream(fileSerialized);
            taskSerialized.writeObject(tasks);
        } catch (IOException e) {
//            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());
            //
        }
    }

    /**
     * Loads a tasklist from local file using ObjectStream and FileStream.
     * @return the looaded tasklist of type Tasklist.
     */
    public TaskList loadTasks(){
        try {
            FileInputStream fileDeSerialized = new FileInputStream(this.filePath);
            ObjectInputStream taskDeSerialized = new ObjectInputStream(fileDeSerialized);
            TaskList tasks;
            tasks = (TaskList) taskDeSerialized.readObject();
            return tasks;

        } catch (IOException | ClassNotFoundException e) {
            return new TaskList();
        }
    }
}

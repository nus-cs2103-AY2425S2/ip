package Acheron.Storage;

import Acheron.Tasks.TaskList;
import Acheron.Tasks.ToDos;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageWriterTest {
    static final String NONEXISTTENT_PATH_ERROR =  "java.lang.NullPointerException:" +
            " Cannot invoke \"java.nio.file.Path.getFileSystem()\" because \"path\" is null";
    static final String NONSENSICAL_TASK_ERROR = "Tasks cannot have null name!";

    /**
     * Used to check if stoage manager will throw an error if the path does not exist
     */
    @Test
    public void StorageManagerNonexistentPath(){
        try {
            StorageManager storageManager = new StorageManager("", new TaskList());
            storageManager.updateSavedFile(new TaskList());
        } catch (Exception e) {
            assertEquals(NONEXISTTENT_PATH_ERROR, e.toString());
        }
    }

    /**
     * Used to check if stoage manager will throw an error if the task is invalid
     */
    @Test
    public void StorageManagerNonsensicalTask(){
        try {
            TaskList taskList = new TaskList();
            taskList.addTask(new ToDos(null, false));
            StorageManager storageManager = new StorageManager("./data/duke.txt", taskList);
            storageManager.updateSavedFile(taskList);
        } catch (Exception e) {
            assertEquals(NONSENSICAL_TASK_ERROR, e.toString());
        }
    }

}
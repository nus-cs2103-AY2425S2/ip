package carolyn;

import java.io.EOFException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Handles file storage operations, ensuring that the necessary directory and file exist.
 */
public class Storage {
    String PATH = "data" + File.separator + "cc.txt";
    public Storage() {
        String dirPath = "data";
        File dir = new File(dirPath);
        if (!dir.exists() || !dir.isDirectory()) {
            new File("data").mkdir(); 
        }
        String path = "data" + File.separator + "cc.txt";
        File file = new File(path);
        if (!file.exists()) {
            try {
                System.out.println("to create file");
                file.createNewFile();
                System.out.println("file created");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Saves the provided {@link TaskList} to the storage file.
     *
     * @param list the {@link TaskList} to be saved.
     */
    public void save(TaskList list) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH))) {
            oos.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Loads the {@link TaskList} from the storage file.
     *
     * @return The loaded {@link TaskList} if deserialization is successful; otherwise, an empty {@link TaskList}.
     */
    public TaskList load() {
        TaskList list = new TaskList();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH))) {
            list = (TaskList) ois.readObject(); // Deserialize object
        } catch (EOFException e) {
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}

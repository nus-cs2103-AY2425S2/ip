package doot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * used for loading and saving lists.
 */
public class Storage {
    /**
     * Makes a tasklist
     * @param list of tasks
     * @throws IOException incase the file cant be made for some reason
     */
    public static void saveList(TaskList list) throws IOException {
        String filePath = "src" + File.separator + "main" + File.separator + "data" + File.separator + "list.txt";
        String folderpath = "src" + File.separator + "main" + File.separator + "data";
        File folder = new File(folderpath);
        if (!folder.exists()) {
            if (!folder.mkdirs()) {
                throw new IOException("folder creation broke");
            }
        }
        File f = new File(filePath);
        if (!f.exists()) {
            f.createNewFile();
        }
        FileWriter fw = new FileWriter(filePath);
        fw.write(list.listData());
        fw.close();
    }

    /**
     * this just creates an empty list and saves it, to empty anything that used to be there
     * @throws IOException if the save cant be overwritten
     */
    public static void clearSave() throws IOException {
        TaskList list = new TaskList();
        Storage.saveList(list);
    }

    /**
     * loads the tasklist saved
     * @param path to where the file is saved
     * @return the tasklist that is saved there
     */
    public static TaskList loadList(String path) throws FileNotFoundException, InvalidFormatException {
        File f = new File(path);
        TaskList list = new TaskList();
        if (f.exists()) {
            list.loadTask(f);
            Ui.showMessage(list.returnList());
            return list;
        }
        return list;
    }
}

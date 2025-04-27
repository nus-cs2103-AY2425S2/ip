package Ninon;

import Ninon.Task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String path;
    public Storage(String path) {
        this.path = path;
    }

    public ArrayList<String> load() throws NinonException {
        ArrayList<String> list = new ArrayList<>(100);
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                list.add(data);
            }
        } catch (FileNotFoundException e) {
            throw new NinonException("file not found");
        }
        return list;
    }

    public void export(TaskList taskList) {
        try (FileWriter writer = new FileWriter(path)) {
            for (Task item : taskList.getList()) {
                writer.write(item.formatOut() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("export failed");
        }
    }
}

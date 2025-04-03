package ziyang;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

public class Storage {
    public String filename;

    public Storage(String filename) {
        this.filename = filename;
    }

    public LinkedList<Task> read() {
        try {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);
            LinkedList<Task> tasks = (LinkedList<Task>) in.readObject();
            System.out.println("Using database");
            in.close();
            file.close();
            return tasks;
        } catch (Exception e) {
            System.out.println("No database found.");
            LinkedList<Task> tasks = new LinkedList<Task>();
            return tasks;
        }
    }


    public void write(LinkedList<Task> tasks) {
        try {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(tasks);
            out.close();
            file.close();
        } catch (Exception e) {
            System.out.println("Error writing to database.");
        }
    }
}

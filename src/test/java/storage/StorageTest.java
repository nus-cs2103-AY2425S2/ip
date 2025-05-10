package storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import task.Task;
import task.Tasklist;

public class StorageTest {

    /*
     * Tests if the function is able to load the tasks stored in the database correctly
     */
    @Test
    public void load_success() throws IOException {
        Storage storage = new Storage("StorageTestingFile.txt");
        Tasklist tasklist = new Tasklist();
        Tasklist archivedTasklist = new Tasklist();
        Tasklist resultTasklist = storage.load(tasklist, archivedTasklist);
        String expectedTask1 = "T 1/ma1522";
        String expectedTask2 = "D 0/ma1521/By2020-01-01 1900";
        ArrayList<Task> resultTask = (resultTasklist.getTasks());
        assertEquals(expectedTask1, resultTask.get(0).getInfo());
        assertEquals(expectedTask2, resultTask.get(1).getInfo());
    }
}

package botato;

import java.util.ArrayList;

import botato.task.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import botato.task.Task;

public class StorageTest {
    @BeforeEach
    void setUp() {
        // Clear any tasklist data present.
        TaskList.initialize(0);
    }

    @Test
    void testSaveLoadData() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("todo potato"));
        tasks.add(new Task());
        Storage.save(tasks);
        ArrayList<Task> tasks2 = Storage.load();
        assertEquals(tasks.get(0).toString(), tasks2.get(0).toString());
    }
}

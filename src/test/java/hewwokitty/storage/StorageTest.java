package hewwokitty.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hewwokitty.task.TaskList;

public class StorageTest {
    @BeforeEach
    void setup() {
        TaskList tasks = new TaskList();
        tasks.addTask("todo cry");
        Storage.writeToFile(tasks);
    }

    @Test
    public void testReadFromFile() {
        TaskList tasks = new TaskList();
        tasks.addTask("todo cry");
        Storage.writeToFile(tasks);

        TaskList tasks2 = Storage.readFromFile();
        assertEquals(tasks.getSize(), tasks2.getSize());
    }
}

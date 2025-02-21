package avocado.storage;

import avocado.task.Todo;
import avocado.task.Task;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {
    @Test
    void testSaveAndLoadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Finish assignment"));

        // Save tasks
        Storage.saveTasks(tasks);

        // Load tasks back
        ArrayList<Task> loadedTasks = Storage.loadTasks();

        assertEquals(1, loadedTasks.size(), "Task list size should match.");
        assertEquals("Finish assignment", loadedTasks.get(0).getDescription(), "Task description should match.");
    }

    @Test
    void testHandleMissingFile() {
        File file = new File("./data/tasks.txt");
        file.delete(); // Simulate missing file

        ArrayList<Task> tasks = Storage.loadTasks();
        assertNotNull(tasks, "Should return an empty task list if file is missing.");
        assertEquals(0, tasks.size(), "Should return an empty task list.");
    }
}

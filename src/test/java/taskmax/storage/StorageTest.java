package taskmax.storage;

import org.junit.jupiter.api.Test;
import taskmax.task.Task;
import taskmax.task.ToDo;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {
    @Test
    void testSaveAndLoadTasks() throws IOException {
        String testFilePath = "data/test_tasks.txt";
        Storage storage = new Storage(testFilePath);
        Task task = new ToDo("Test Storage");

        storage.saveTasks(List.of(task));
        List<Task> loadedTasks = storage.loadTasks();

        assertEquals(1, loadedTasks.size());
        assertEquals("Test Storage", loadedTasks.get(0).getDescription());

        new File(testFilePath).delete();
    }
}

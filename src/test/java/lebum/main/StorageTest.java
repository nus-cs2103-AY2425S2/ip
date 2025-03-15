package lebum.main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import lebum.task.*;

class StorageTest {
    @Test
    void saveAndLoad_todoTask_matchesOriginal() {
        Storage storage = new Storage("test.txt");
        TaskList originalList = new TaskList();
        ToDo task = new ToDo("Test todo");
        originalList.addInitialTask(task);

        storage.saveToFile(originalList);
        TaskList loadedList = storage.loadTasks();

        Task loadedTask = loadedList.array().get(0);
        assertEquals("Test todo", loadedTask.getTitle());
        assertTrue(loadedTask instanceof ToDo);
    }

    @Test
    void formatTask_deadline_correctFormat() {
        Storage storage = new Storage("test.txt");
        Deadline task = new Deadline("Test deadline", "2024-01-26");
        String formatted = storage.formatTask(task);
        assertEquals("D | [ ] | Test deadline | 2024-01-26\n", formatted);
    }
}
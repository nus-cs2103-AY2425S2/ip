package zea;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zea.task.Deadline;
import zea.task.Event;
import zea.task.Task;
import zea.task.Tasks;
import zea.task.Todo;




public class StorageTest {
    private Storage storage;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void testLoadValidData() throws ZeaException {
        Storage storage = new Storage("./data/test.txt");
        ArrayList<Task> expectedTasks = new ArrayList<>();
        expectedTasks.add(new Todo("read book"));
        expectedTasks.add(new Deadline("return book", LocalDateTime.parse("01/01/2025 10:00", Task.FORMATTER)));
        expectedTasks.add(new Event("wedding", LocalDateTime.parse("01/01/2025 10:00", Task.FORMATTER),
                LocalDateTime.parse("01/01/2025 10:00", Task.FORMATTER).plusHours(2)));

        ArrayList<Task> actualTasks = storage.load();

        assertEquals(expectedTasks, actualTasks);
    }

    @Test
    public void testSaveValidData() throws ZeaException, IOException {
        Storage storage = new Storage("./data/testsave.txt");
        Tasks tasks = new Tasks();
        tasks.addTask(new Todo("save me"));
        storage.save(tasks);
        List<String> lines = Files.readAllLines(Path.of("./data/testsave.txt"));
        assertEquals(1, lines.size());
        assertEquals("T|0|save me|", lines.get(0));
    }

}

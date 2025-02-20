package prophet.storage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import prophet.gui.Ui;
import prophet.task.ToDoTask;

public class SaveTest {
    private Ui ui;
    private Storage storage;
    private Path saveFilePath;
    @BeforeEach
    void setUp() {
        ui = new Ui();
        storage = new Storage();
        saveFilePath = Path.of("data/tasks.txt");
    }

    @Test
    public void testSaveCreatesFile() {
        storage.addToList(new ToDoTask("task 1"));
        storage.addToList(new ToDoTask("task 2"));

        Save.save(ui, storage);

        File savedFile = saveFilePath.toFile();
        assertTrue(savedFile.exists(), "Save file should be created");
    }

    @Test
    void testSaveWritesCorrectData() throws IOException {
        storage.addToList(new ToDoTask("task 1"));
        storage.addToList(new ToDoTask("task 2"));

        Save.save(ui, storage);

        String content = Files.readString(saveFilePath);
        assertEquals("T | [ ] task 1\nT | [ ] task 2",
                content.trim(), "File content should match saved tasks");
    }

    @Test
    void testLoadDoesNothingIfFileDoesNotExist() {
        assertDoesNotThrow(() -> Save.load(ui, storage));
    }

    @Test
    void testLoadReadsFile() throws IOException {
        Files.writeString(saveFilePath, "T | [ ] task 1\nT | [ ] task 2");

        Save.load(ui, storage);

        assertEquals(2, storage.getListSize(), "Storage should contain two tasks after loading");
    }
}

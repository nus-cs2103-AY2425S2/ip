package pluto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class StorageTest {

    @TempDir
    Path tempDir;

    @Test
    public void loadTasks_nonExistentFile_newEmptyList() {
        String fakeFilePath = tempDir.resolve("nonexistent.txt").toString();
        Storage storage = new Storage(fakeFilePath);

        List<Task> tasks = storage.loadTasks();

        assertTrue(tasks.isEmpty(), "Expected empty list when loading from non-existent file.");
    }

    @Test
    public void loadTasks_validFile_loadSuccess() throws IOException {
        File tempFile = tempDir.resolve("tasks.txt").toFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("T | 0 | Finish assignment");
        }

        Storage storage = new Storage(tempFile.getPath());
        List<Task> tasks = storage.loadTasks();

        assertEquals("[T] [ ] Finish assignment", tasks.get(0).toString());
    }
}

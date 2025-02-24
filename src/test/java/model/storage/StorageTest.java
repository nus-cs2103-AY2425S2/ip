package model.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import model.task.TaskList;
import utils.RandomUtils;

/**
 * Test class for the Storage class.
 */
public class StorageTest {

    private static final String TESTDIR = "testData";
    private static final String TESTFILE = "test.txt";

    /**
     * Sets up the test environment by creating test files and directories.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void setupTest() throws IOException {
        Path dirPath = Paths.get(TESTDIR);
        Path filePath = dirPath.resolve(TESTFILE);

        if (!Files.exists(dirPath)) {
            Files.createDirectory(dirPath);
        }
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }

        Files.write(filePath, List.of("T|0|" + RandomUtils.getRandomString(10),
                "D|0|" + RandomUtils.getRandomString(10) + "|" + RandomUtils.getRandomDateTimeString(),
                "E|0|" + RandomUtils.getRandomString(10) + "|" + RandomUtils.getRandomDateTimeString() + "|"
                + RandomUtils.getRandomDateTimeString()));
    }

    /**
     * Tears down the test environment by deleting test files and directories.
     *
     * @throws IOException If an I/O error occurs.
     */
    private void teardownTest() throws IOException {
        Path dirPath = Paths.get(TESTDIR);
        Path filePath = dirPath.resolve(TESTFILE);

        Files.deleteIfExists(filePath);
        Files.deleteIfExists(dirPath);
    }

    /**
     * Tests loading tasks from an empty file.
     *
     * @throws Exception If an error occurs during the test.
     */
    @Test
    public void loadTasks_emptyFile_emptyTaskList() throws Exception {
        Storage storage = new Storage(TESTDIR, TESTFILE);
        TaskList tasks = storage.loadTasks();
        assertEquals(0, tasks.size());
        teardownTest();
    }

    /**
     * Tests loading tasks from a non-empty file.
     *
     * @throws Exception If an error occurs during the test.
     */
    @Test
    public void loadTasks_nonEmptyFile_nonEmptyTaskList() throws Exception {
        setupTest();
        Storage storage = new Storage(TESTDIR, TESTFILE);
        TaskList tasks = storage.loadTasks();
        assertEquals(3, tasks.size());
        teardownTest();
    }

}

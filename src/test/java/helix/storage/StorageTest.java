package helix.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import helix.task.Task;
import helix.task.Todo;

/**
 * Tests the behavior of the {@code Storage} class.
 */
public class StorageTest {

    private Storage storage;

    /**
     * Sets up the test environment by creating a temporary file for testing.
     * This temporary file acts as the storage backend for the {@link Storage} class during tests.
     *
     * @throws IOException if an error occurs while creating the temporary file
     */
    @BeforeEach
    void setUp() throws IOException {
        Path tempFile = Files.createTempFile("test_storage", ".txt");
        storage = new Storage(tempFile.toString());
    }

    /**
     * Tests the {@link Storage#save(List)} and {@link Storage#load()} methods.
     * Verifies that a single task is correctly saved and subsequently loaded from storage.
     *
     * @throws IOException if an error occurs during file operations
     */
    @Test
    void save_tasksWithSingleTask_taskPersistedSuccessfully() throws IOException {
        // Arrange
        List<Task> tasks = List.of(new Todo("Sample Task"));

        // Act
        storage.save(tasks);
        List<Task> loadedTasks = storage.load();

        // Assert
        assertEquals(1, loadedTasks.size(), "The loaded task list should contain one task.");
        assertEquals("Sample Task", loadedTasks.get(0).getDescription(), "The task description should match.");
    }

    /**
     * Tests the {@link Storage#save(List)} and {@link Storage#load()} methods.
     * Verifies that saving an empty task list results in an empty list being loaded.
     *
     * @throws IOException if an error occurs during file operations
     */
    @Test
    void saveAndLoad_emptyTaskList_emptyListRetrieved() throws IOException {
        // Arrange
        List<Task> tasksToSave = List.of();

        // Act
        storage.save(tasksToSave);
        List<Task> loadedTasks = storage.load();

        // Assert
        assertEquals(0, loadedTasks.size(), "The loaded task list should be empty.");
    }
}

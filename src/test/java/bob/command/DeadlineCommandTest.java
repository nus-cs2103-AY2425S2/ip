package bob.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bob.storage.Storage;
import bob.task.TaskList;
import bob.util.Formatter;

/**
 * Tests whether DeadlineCommand is correctly executed.
 * Creates temporary task list for testing.
 */
public class DeadlineCommandTest {

    private static final int TASK_NUMBER = 1;

    @BeforeEach
    public void setUp() throws IOException {
        // Create a temporary file for task list
        Path filePath = Files.createTempFile("tasklist", ".csv");
        filePath.toFile().deleteOnExit();

        // Set the file path to the temporary file
        Storage.setFilePathTo(filePath);
    }

    @Test
    public void testDeadlineCommand_taskAdded() throws IOException {
        LocalDateTime by = LocalDateTime.of(2025, 3, 1, 23, 59);
        DeadlineCommand c = new DeadlineCommand("assignment 3", by);

        String actualResponse = c.execute();
        String expectedResponse = Formatter.format("Bob is on it! I've added this task:",
                "[D][ ] assignment 3 (by: Mar 1 2025 11:59PM)",
                "Now you have " + TaskList.getCount() + " task(s).");

        assertEquals(expectedResponse, actualResponse);
        assertEquals(TaskList.getCount(), TASK_NUMBER);
    }

    @AfterEach
    public void tearDown() throws IOException {
        TaskList.deleteTasks(List.of(TASK_NUMBER));
        Storage.resetFilePath();
    }
}

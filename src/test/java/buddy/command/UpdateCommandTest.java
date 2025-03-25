package buddy.command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buddy.exception.BuddyException;
import buddy.exception.BuddyTaskNotFoundException;
import buddy.storage.DataStorage;
import buddy.task.Deadline;
import buddy.task.Event;
import buddy.task.TaskList;

public class UpdateCommandTest {
    private TaskList taskList;
    private DataStorage dataStorage;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        dataStorage = new DataStorage("test.json");

        taskList.addTask(new Deadline("Submit report", java.time.LocalDateTime.of(2024, 2, 15, 23, 59)));
        taskList.addTask(new Event("Project presentation",
                java.time.LocalDateTime.of(2024, 2, 15, 23, 59), java.time.LocalDateTime.of(2024, 2, 18, 23, 59)));
    }

    /**
     * Test valid update of a deadline task's due date.
     */
    @Test
    public void execute_validUpdateDeadline_success() {
        ArrayList<String> args = new ArrayList<>();
        args.add("1"); // Task ID (1-based index)
        args.add("/by");
        args.add("2025-03-01 1200");

        UpdateCommand command = new UpdateCommand(args);
        assertDoesNotThrow(() -> {
            String result = command.execute(taskList, dataStorage);
            assertTrue(result.contains("Submit report"));
            assertTrue(result.contains("Mar 01 2025 12:00 PM"));
        });
    }

    /**
     * Test valid update of an event's start time.
     */
    @Test
    public void execute_validUpdateEventStartTime_success() {
        ArrayList<String> args = new ArrayList<>();
        args.add("2"); // Task ID (1-based index)
        args.add("/from");
        args.add("2025-03-10 1500");

        UpdateCommand command = new UpdateCommand(args);
        assertDoesNotThrow(() -> {
            String result = command.execute(taskList, dataStorage);
            assertTrue(result.contains("Project presentation"));
            assertTrue(result.contains("Mar 10 2025 03:00 PM"));
        });
    }

    /**
     * Test invalid update due to non-numeric task ID.
     */
    @Test
    public void execute_invalidTaskId_throwsException() {
        ArrayList<String> args = new ArrayList<>();
        args.add("abc"); // Invalid task ID
        args.add("/by");
        args.add("2025-03-01");

        UpdateCommand command = new UpdateCommand(args);
        assertThrows(BuddyException.class, () -> command.execute(taskList, dataStorage));
    }

    /**
     * Test updating a non-existing task ID (out of bounds).
     */
    @Test
    public void execute_taskIdOutOfBounds_throwsTaskNotFoundException() {
        ArrayList<String> args = new ArrayList<>();
        args.add("10"); // Task ID does not exist
        args.add("/by");
        args.add("2025-03-01");

        UpdateCommand command = new UpdateCommand(args);
        assertThrows(BuddyTaskNotFoundException.class, () -> command.execute(taskList, dataStorage));
    }

    /**
     * Test invalid update field.
     */
    @Test
    public void execute_invalidField_throwsException() {
        ArrayList<String> args = new ArrayList<>();
        args.add("1"); // Task ID
        args.add("/randomField");
        args.add("New Value");

        UpdateCommand command = new UpdateCommand(args);
        assertThrows(BuddyException.class, () -> command.execute(taskList, dataStorage));
    }

    /**
     * Test missing arguments.
     */
    @Test
    public void execute_missingArguments_throwsException() {
        ArrayList<String> args = new ArrayList<>();
        args.add("1"); // Task ID only, missing field and value

        UpdateCommand command = new UpdateCommand(args);
        assertThrows(BuddyException.class, () -> command.execute(taskList, dataStorage));
    }
}

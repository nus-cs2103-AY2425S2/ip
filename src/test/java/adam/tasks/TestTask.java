package adam.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import adam.exceptions.AdamException;

/**
 * Test class for Task.
 */
public class TestTask {
    /**
     * Tests the constructor of Task.
     */
    @Test
    public void testTask() {
        try {
            Task task = new StubTask("Test Task");
            assertEquals("[ ] Test Task", task.toString());
        } catch (AdamException e) {
            assertEquals(1, 0);
        }
    }

    /**
     * Tests the markDone method of Task.
     */
    @Test
    public void testMarkDone() {
        try {
            Task task = new StubTask("Test Task");
            task.markDone();
            assertEquals("[X] Test Task", task.toString());
        } catch (AdamException e) {
            assertEquals(1, 0);
        }
    }

    /**
     * Tests the markUndone method of Task.
     */
    @Test
    public void testMarkUndone() {
        try {
            Task task = new StubTask("Test Task");
            task.markDone();
            assertEquals("[X] Test Task", task.toString());
            task.unmarkDone();
            assertEquals("[ ] Test Task", task.toString());
        } catch (AdamException e) {
            assertEquals(1, 0);
        }
    }

    /**
     * Tests the isOn method of Task.
     */
    @Test
    public void testIsOn() {
        try {
            Task task = new StubTask("Test Task");
            assertEquals(false, task.isOn(LocalDate.now()));
        } catch (AdamException e) {
            assertEquals(1, 0);
        }
    }

    /**
     * Tests the containsQuery method of Task.
     */
    @Test
    public void testContainsQuery() {
        try {
            Task task = new StubTask("Test Task");
            assertEquals(true, task.containsQuery("Test"));
            assertEquals(false, task.containsQuery("Not"));
        } catch (AdamException e) {
            assertEquals(1, 0);
        }
    }
}

/**
 * Stub class for Task, since Task class is abstract.
 */
class StubTask extends Task {
    /**
     * Constructor for StubTask.
     *
     * @param description Description of the task.
     * @throws AdamException If the description is empty.
     */
    public StubTask(String description) throws AdamException {
        super(description);
    }

    @Override
    public boolean isOn(LocalDate date) {
        return false;
    }
}

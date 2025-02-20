package adam.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import adam.exceptions.AdamException;
import adam.tasks.Task;

/**
 * Test class for TaskList.
 */
public class TestTaskList {
    /**
     * Tests the listAll method.
     */
    @Test
    public void testListAll() {
        try {
            TaskList manager = new TaskList(new StubStorage());
            Task task1 = new StubTask("Test Task 1");
            Task task2 = new StubTask("Test Task 2");
            Task task3 = new StubTask("Test Task 3");

            manager.addTask(task1);
            manager.addTask(task2);
            manager.addTask(task3);

            assertEquals(3, manager.listAll().size());

            ArrayList<String> outputs = manager.listAll();
            assertEquals("1. " + task1.toString(), outputs.get(0));
            assertEquals("2. " + task2.toString(), outputs.get(1));
            assertEquals("3. " + task3.toString(), outputs.get(2));
        } catch (AdamException e) {
            assertEquals(1, 0);
        }
    }

    /**
     * Tests the markDone method.
     */
    @Test
    public void testMarkDone() {
        try {
            TaskList manager = new TaskList(new StubStorage());
            Task task1 = new StubTask("Test Task 1");
            Task task2 = new StubTask("Test Task 2");
            Task task3 = new StubTask("Test Task 3");

            manager.addTask(task1);
            manager.addTask(task2);
            manager.addTask(task3);

            manager.markDone(0);
            manager.markDone(2);

            assertEquals("[X] Test Task 1", task1.toString());
            assertEquals("[X] Test Task 3", task3.toString());
        } catch (AdamException e) {
            assertEquals(1, 0);
        }
    }

    /**
     * Tests the listAllOnDate method.
     */
    @Test
    public void testListAllOnDate() {
        try {
            TaskList manager = new TaskList(new StubStorage());

            manager.addTask(new StubTask("On Date"));
            manager.addTask(new StubTask("Not On Date"));
            manager.addTask(new StubTask("On Date"));
            manager.addTask(new StubTask("Not On Date"));
            ArrayList<String> outputs = manager.listAllOnDate(LocalDate.now());

            assertEquals(2, outputs.size());
            assertEquals("1. [ ] On Date", outputs.get(0));
            assertEquals("3. [ ] On Date", outputs.get(1));
        } catch (AdamException e) {
            assertEquals(1, 0);
        }
    }
}

/**
 * Stub class for Storage.
 */
class StubStorage extends Storage {
    StubStorage() {
        super();
    }

    @Override
    public ArrayList<Task> loadLog() {
        return new ArrayList<>();
    }

    @Override
    public void saveLog(ArrayList<Task> tasks) {
    }
}

/**
 * Stub class for Task.
 */
class StubTask extends Task {
    private final String description;

    StubTask(String description) throws AdamException {
        super(description);
        this.description = description;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean isOn(LocalDate date) {
        return "On Date".equals(this.description);
    }
}

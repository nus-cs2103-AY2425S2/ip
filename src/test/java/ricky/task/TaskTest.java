package ricky.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskTest {
    private Task task;

    @BeforeEach
    public void setUp() {
        task = new Task("read book");
    }

    @Test
    public void testGetStatusIcon() {
        assertEquals(" ", task.getStatusIcon());
        task.markDone();
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void testMarkDone() {
        task.markDone();
        assertEquals(true, task.isDone);
    }

    @Test
    public void testMarkUndone() {
        task.markDone();
        task.markUndone();
        assertEquals(false, task.isDone);
    }

    @Test
    public void testToString() {
        assertEquals("[ ] read book", task.toString());
        task.markDone();
        assertEquals("[X] read book", task.toString());
    }

    @Test
    public void testStore() {
        assertEquals(" | 0 | read book", task.storeInfo());
        task.markDone();
        assertEquals(" | 1 | read book", task.storeInfo());
    }
}

package Ninon;

import static org.junit.jupiter.api.Assertions.*;

import Ninon.Task.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskTest {

    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task("Sample Event");
    }

    @Test
    void testInitialState() {
        assertEquals("[ ] Sample Event", task.toString());
        assertEquals("0 / Sample Event", task.formatOut());
    }

    @Test
    void testMarkAsDone() {
        task.mark_As_Done();
        assertEquals("[X] Sample Event", task.toString());
        assertEquals("1 / Sample Event", task.formatOut());
    }

    @Test
    void testMarkAsNotDone() {
        task.mark_As_Done();
        task.mark_As_Not_Done();
        assertEquals("[ ] Sample Event", task.toString());
        assertEquals("0 / Sample Event", task.formatOut());
    }
}

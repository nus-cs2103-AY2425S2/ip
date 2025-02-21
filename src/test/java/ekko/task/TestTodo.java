package ekko.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TodoTest {

    @Test
    void testConstructor() {
        Todo task = new Todo("Finish assignment");
        assertEquals("Finish assignment", task.printDes());
        assertFalse(task.isDone(), "New task should not be marked as done by default.");
    }

    @Test
    void testMarkDone() {
        Todo task = new Todo("Submit report");
        task.markDone();
        assertTrue(task.isDone(), "Task should be marked as done after calling markDone().");
        assertEquals("[X]", task.printDone(), "Checkbox should display '[X]' when task is done.");
    }

    @Test
    void testUnDo() {
        Todo task = new Todo("Read book");
        task.markDone();
        task.unDo();
        assertFalse(task.isDone(), "Task should be marked as undone after calling unDo().");
        assertEquals("[ ]", task.printDone(), "Checkbox should display '[ ]' when task is undone.");
    }

    @Test
    void testPrintDes() {
        Todo task = new Todo("Buy groceries");
        assertEquals("Buy groceries", task.printDes(), "printDes() should return the task description.");
    }

    @Test
    void testPrintDone() {
        Todo task = new Todo("Exercise");
        assertEquals("[ ]", task.printDone(), "printDone() should initially return '[ ]'.");
        task.markDone();
        assertEquals("[X]", task.printDone(), "printDone() should return '[X]' after marking as done.");
    }

    @Test
    void testToString() {
        Todo task = new Todo("Walk the dog");
        assertEquals("[T] [ ] Walk the dog", task.toString(), "toString() should return correct format for an undone task.");
        task.markDone();
        assertEquals("[T] [X] Walk the dog", task.toString(), "toString() should return correct format for a done task.");
    }
}

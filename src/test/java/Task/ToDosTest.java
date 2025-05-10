package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ToDosTest {

    @Test
    public void testToString() {
        ToDos todo = new ToDos("Read a book");
        assertEquals("[T][ ] Read a book", todo.toString(), "toString() does not match expected format.");
    }

    @Test
    public void testMarkAsDone() {
        ToDos todo = new ToDos("Complete homework");
        todo.markAsDone();
        assertEquals("[T][X] Complete homework", todo.toString(), "Marking as done failed.");
    }

    @Test
    public void testClone() {
        ToDos original = new ToDos("Practice coding");
        ToDos clone = original.clone();
        assertNotSame(original, clone, "Clone should not be the same instance as original.");
        assertEquals(original.toString(), clone.toString(), "Cloned ToDos should have the same string representation.");
    }

    @Test
    public void testLeadingTrailingSpaces() {
        ToDos todo = new ToDos("  Buy groceries  ");
        assertEquals("[T][ ]   Buy groceries  ", todo.toString(), "Leading and trailing spaces should be preserved.");
    }

    @Test
    public void testLongDescription() {
        String longDesc = "A very long task description that should still be properly formatted in the output.";
        ToDos todo = new ToDos(longDesc);
        assertEquals("[T][ ] " + longDesc, todo.toString(), "Long descriptions should be formatted correctly.");
    }
}

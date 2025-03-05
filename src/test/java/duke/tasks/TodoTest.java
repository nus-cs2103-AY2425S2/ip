package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import g.tasks.Todo;

public class TodoTest {

    @Test
    public void testToFileString() {
        Todo todoNotDone = new Todo("Read book", false);
        Todo todoDone = new Todo("Write report", true);

        assertEquals("T | 0 | Read book", todoNotDone.toFileString());
        assertEquals("T | 1 | Write report", todoDone.toFileString());
    }

    @Test
    public void testToString() {
        Todo todo = new Todo("Exercise", false);
        assertEquals("[T][ ] Exercise", todo.toString());
    }
}

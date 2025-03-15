package lebum.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ToDoTest {
    @Test
    void constructor_validTitle_createsTask() {
        ToDo todo = new ToDo("read book");
        assertEquals("read book", todo.getTitle());
        assertFalse(todo.getIsDone());
    }

    @Test
    void print_undoneTask_correctFormat() {
        ToDo todo = new ToDo("read book");
        assertEquals("[T] [ ] read book", todo.print());
    }

    @Test
    void print_doneTask_correctFormat() {
        ToDo todo = new ToDo("read book");
        todo.markDoneSilently();
        assertEquals("[T] [X] read book", todo.print());
    }
}
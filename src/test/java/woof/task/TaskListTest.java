package woof.task;

import org.junit.jupiter.api.Test;
import woof.exception.MarkedErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    @Test
    public void testAddTodo_validInput_success() {
        TaskList.clear();
        TaskList.addTodo("Read a book");

        assertEquals(1, TaskList.size());
        assertEquals("[T][ ] Read a book", TaskList.get(1));
    }

    @Test
    public void testMark_validInput_success() throws MarkedErrorException {
        TaskList.clear(); // Clear the list before testing
        TaskList.addTodo("Read a book");
        TaskList.mark(1);
        assertEquals("[T][X] Read a book", TaskList.get(1));
    }

    @Test
    public void testDelete_validInput_success() {
        TaskList.clear(); // Clear the list before testing
        TaskList.addTodo("Read a book");
        TaskList.addTodo("Write a report");

        TaskList.delete(1);

        assertEquals(1, TaskList.size());
        assertEquals("[T][ ] Write a report", TaskList.get(1));
    }
}

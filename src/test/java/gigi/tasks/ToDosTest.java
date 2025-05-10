package gigi.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ToDosTest {
    private ToDos todo;

    @BeforeEach
    void setUp() {
        this.todo = new ToDos("Buy groceries");
    }

    @Test
    void constructor_validDescription_taskInitialized() {
        assertNotNull(todo);
        assertEquals("[T][ ] Buy groceries", todo.toString());
    }

    @Test
    void markDone_taskMarkedDone_statusUpdated() {
        todo.markDone(0);
        assertEquals("[T][X] Buy groceries", todo.toString());
    }

    @Test
    void markUndone_taskPreviouslyMarkedDone_statusReverted() {
        todo.markDone(0); // Mark as done first
        todo.markUndone(0);
        assertEquals("[T][ ] Buy groceries", todo.toString());
    }

    @Test
    void convertToText_taskNotDone_correctSaveFormat() {
        assertEquals("[T] | false | Buy groceries", todo.convertToText());
    }

    @Test
    void convertToText_taskDone_correctSaveFormat() {
        todo.markDone(0);
        assertEquals("[T] | true | Buy groceries", todo.convertToText());
    }
}

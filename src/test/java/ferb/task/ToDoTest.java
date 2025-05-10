package ferb.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ToDoTest {

    @Test
    public void todo_creationWithValidInputs_todoCreatedSuccessfully() {
        ToDo todo = new ToDo("run");
        assertEquals("run", todo.taskDescription());
        assertFalse(todo.isDone());
    }

    @Test
    public void todo_markAsDone_todoMarkedAsDone() {
        ToDo todo = new ToDo("run");
        todo.markDone();
        assertTrue(todo.isDone());
    }

    @Test
    public void todo_unmarkAsDone_todoUnmarkedAsDone() {
        ToDo todo = new ToDo("run");
        todo.markDone();
        todo.unmarkDone();
        assertFalse(todo.isDone());
    }

    @Test
    public void todo_toStringWithValidInputs_correctStringRepresentation() {
        ToDo todo = new ToDo("run");
        assertEquals("[T][ ] run", todo.toString());
        todo.markDone();
        assertEquals("[T][X] run", todo.toString());
    }

    @Test
    public void todo_toSaveWithValidInputs_correctSaveFormat() {
        ToDo todo = new ToDo("run");
        assertEquals("T|0|run", todo.toSave());
        todo.markDone();
        assertEquals("T|1|run", todo.toSave());
    }
}
package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import exceptions.TaskException;

public class TodoTest {

    @Test
    public void testCreateValidTodo() throws TaskException {
        Todo todo = Todo.create("todo read book /priority HIGH");
        assertEquals("[T][ ] read book (Priority: High)", todo.toString());
    }

    @Test
    public void testCreateDefaultPriorityTodo() throws TaskException {
        Todo todo = Todo.create("todo read book");
        // Default priority is LOW
        assertEquals("[T][ ] read book (Priority: Low)", todo.toString());
    }

    @Test
    public void testCreateTodoWithoutDescriptionThrowsException() {
        Exception exception = assertThrows(TaskException.class, () -> {
            Todo.create("todo ");
        });
        assertEquals("PLEASE BRUH! Use: todo <description> /priority <LOW|MEDIUM|HIGH|URGENT> ._.",
                exception.getMessage());
    }

    @Test
    public void testCreateTodoWithInvalidPriorityThrowsException() {
        Exception exception = assertThrows(TaskException.class, () -> {
            Todo.create("todo read book /priority SUPERHIGH");
        });
        assertEquals("Get your priorities in order! Use: LOW, MEDIUM, HIGH, or URGENT!",
                exception.getMessage());
    }
}

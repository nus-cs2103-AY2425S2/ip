package ekko.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestTodolist {

    private Todolist todolist;
    private Todo todoTask;
    private Event eventTask;
    private Deadline deadlineTask;

    @BeforeEach
    public void setUp() {
        todolist = new Todolist();
        todoTask = new Todo("Complete homework");
        eventTask = new Event("Meeting with boss",
                LocalDateTime.of(2025, 2, 20, 10, 0),
                LocalDateTime.of(2025, 2, 20, 12, 0));
        deadlineTask = new Deadline("Submit report",
                LocalDateTime.of(2025, 2, 19, 23, 59));
    }

    @Test
    public void testAddTodo() {
        String result = todolist.add(todoTask);
        assertEquals("Got it! I've added this task: \n[T] [ ] Complete homework\nNow you have 1 task(s) in the list.",
                result);
    }

    @Test
    public void testAddEvent() {
        String result = todolist.add(eventTask);
        assertEquals("Got it! I've added this task: \n[E] [ ] Meeting with boss (from: 2025-02-20T10:00 to: 2025-02-20T12:00)\nNow you have 1 task(s) in the list.",
                result);
    }

    @Test
    public void testAddDeadline() {
        String result = todolist.add(deadlineTask);
        assertEquals("Got it! I've added this task: \n[D] [ ] Submit report (by: 2025-02-19T23:59)\nNow you have 1 task(s) in the list.",
                result);
    }

    @Test
    public void testMarkTodo() {
        todolist.add(todoTask);
        String result = todolist.mark(1);
        assertEquals("Yippee! One task off the list: \n[T] [X] Complete homework", result);
    }

    @Test
    public void testUnmarkTodo() {
        todolist.add(todoTask);
        todolist.mark(1); // Mark it first
        String result = todolist.unmark(1);
        assertEquals("Meow, please remember to do it still...\n[T] [ ] Complete homework", result);
    }

    @Test
    public void testDeleteTodo() {
        todolist.add(todoTask);
        String result = todolist.delete(1);
        assertEquals("Ekko has eaten your task!\n[T] [ ] Complete homework\nOnly 0 tasks left! Jiayouu", result);
    }

    @Test
    public void testFilterTask() {
        todolist.add(todoTask);
        todolist.add(eventTask);
        String result = todolist.filter("boss");
        assertEquals("[E] [ ] Meeting with boss (from: 2025-02-20T10:00 to: 2025-02-20T12:00)", result);
    }

    @Test
    public void testToString() {
        todolist.add(todoTask);
        todolist.add(eventTask);
        String result = todolist.toString();
        assertTrue(result.contains("[T] [ ] Complete homework"));
        assertTrue(result.contains("[E] [ ] Meeting with boss"));
    }

    @Test
    public void testMarkTodoOutOfBounds() {
        todolist.add(todoTask);
        assertThrows(AssertionError.class, () -> todolist.mark(2));
    }

    @Test
    public void testDeleteTodoOutOfBounds() {
        todolist.add(todoTask);
        assertThrows(AssertionError.class, () -> todolist.delete(2));
    }
}

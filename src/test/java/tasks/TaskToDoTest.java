package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TaskToDoTest {
    private TaskToDo task;

    @BeforeEach
    public void initialize() {
        task = new TaskToDo("eat food");
    }

    @Test
    public void getFullName_functionCall_success() {
        String expectedName = "[T][ ] eat food";
        assertEquals(expectedName, task.getFullName());
    }

    @Test
    public void markDone_functionCall_success() {
        String expectedName = "[T][X] eat food";
        task.markDone();
        assertEquals(expectedName, task.getFullName());
    }

    @Test
    public void unmarkDone_functionCall_success() {
        String expectedName = "[T][ ] eat food";
        task.markDone();
        task.unmarkDone();
        assertEquals(expectedName, task.getFullName());
    }

    @Test
    public void getName_functionCall_success() {
        String expectedName = "eat food";
        assertEquals(expectedName, task.getName());
    }

    @Test
    public void getDone_functionCall_success() {
        boolean isDone = true;
        task.markDone();
        assertEquals(isDone, task.getDone());

        isDone = false;
        task.unmarkDone();
        assertEquals(isDone, task.getDone());
    }
}

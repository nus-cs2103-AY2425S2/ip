package miki.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void testToDo() {
        try {
            // Test Task
            Task task = new ToDo("Test");

            assertEquals("[T] " + "[ ]  Test" + "\n" + "\n" , task.toString());
        } catch (Error e) {
            fail();
        }
    }

    @Test
    public void testToDoToStorageFormat() {
        try {
            // Test Task
            Task task = new ToDo("Test");

            assertEquals("T | 0 | Test | ", task.toStorageFormat());
        } catch (Error e) {
            fail();
        }
    }
}

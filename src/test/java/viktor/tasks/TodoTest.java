package viktor.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class TodoTest {

    @Test
    void testTodoConstructor() {
        TodoTask todo = new TodoTask("Test task");
        assertNotNull(todo, "The Todo object should be created.");
    }

    @Test
    void testGetDescription() {
        TodoTask todo = new TodoTask("Test task");
        assertEquals("Test task", todo.getDescription(),
            "Description should match the one provided in the constructor.");
    }

    @Test
    void testGetType() {
        TodoTask todo = new TodoTask("Test task");
        assertEquals("T", todo.getType(), "The type of the Todo should be 'T'.");
    }

    @Test
    void testToString() {
        TodoTask todo = new TodoTask("Test task");
        assertEquals("[T][ ] Test task", todo.toString(), "toString should return the correct string format.");
    }

    @Test
    void testToSaveandBeDone() {
        TodoTask todo = new TodoTask("Test task");
        assertEquals("T |   | Test task", todo.toSave(), "toSave should return the correct save format.");
    }

    @Test
    void testBeDoneandBeUndone() {
        TodoTask todo = new TodoTask("Test task");
        todo.beDone();
        todo.beUndone();
        assertEquals("T |   | Test task", todo.toSave(), "toSave should return the correct save format.");
    }

    @Test
    void testTodoStatus() {
        TodoTask todo = new TodoTask("Test task");
        assertEquals(" ", todo.getStatusIcon(), "Initially, the status icon should be a space.");
    }
}

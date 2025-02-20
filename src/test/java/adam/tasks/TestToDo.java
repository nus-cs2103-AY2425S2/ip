package adam.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import adam.exceptions.AdamException;

/**
 * Test class for ToDo.
 */
class TestToDo {
    /**
     * Tests the toString method of ToDo.
     */
    @Test
    void testToString() {
        try {
            ToDo t = new ToDo("Test ToDo");
            assertEquals("[T][ ] Test ToDo", t.toString());
        } catch (AdamException e) {
            assertEquals(0, 1);
        }
    }

    /**
     * Tests the toLogString method of ToDo.
     */
    @Test
    void testToLogString() {
        try {
            ToDo t = new ToDo("Test ToDo");
            assertEquals("T | false | Test ToDo", t.toLogString());
        } catch (AdamException e) {
            assertEquals(0, 1);
        }
    }
}

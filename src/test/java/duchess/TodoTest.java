package duchess;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    void testMark() {
        Todo task = new Todo("Task 1");
        assertEquals(false, task.isMarked());
        task.mark();
        assertEquals(true, task.isMarked());
        task.mark();
        assertEquals(true, task.isMarked());
    }

    @Test
    void testToFileFormat() {
        Todo task = new Todo("Task 1");
        String fileFormatString = "T | 0 | Task 1";
        assertEquals(fileFormatString, task.toFileFormat());
    }
}

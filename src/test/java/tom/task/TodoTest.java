package tom.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tom.exception.TaskException;

public class TodoTest {
    @Test
    public void toFileFormatString_arbitrarydescription_success() {
        Todo task = new Todo("arbitrary description");

        // non-marked tasks should have empty indicator
        String fileString = task.toFileFormatString();
        assertEquals("T |   | arbitrary description", fileString);

        // marked tasks should have X indicator
        try {
            task.markDone();
        } catch (TaskException e) {
            e.printStackTrace();
        }
        fileString = task.toFileFormatString();
        assertEquals("T | X | arbitrary description", fileString);
    }

    @Test
    public void toString_arbitrarydescription_success() {
        Todo task = new Todo("arbitrary description");

        // non-marked tasks should have empty indicator
        String fileString = task.toString();
        assertEquals("[T][ ] arbitrary description", fileString);

        // marked tasks should have X indicator
        try {
            task.markDone();
        } catch (TaskException e) {
            e.printStackTrace();
        }
        fileString = task.toString();
        assertEquals("[T][X] arbitrary description", fileString);
    }
}

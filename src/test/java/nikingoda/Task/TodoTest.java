package nikingoda.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void testNotDone() {
        Todo todo = new Todo("Read book");
        String expectedFile = "T|0|Read book";
        String expectedString = "[T][ ] Read book";
        assertEquals(expectedFile, todo.toFile());
        assertEquals(expectedString, todo.toString());
    }

    @Test
    public void testDone() {
        Todo todo = new Todo("Read book", true);
        String expectedString = "[T][X] Read book";
        String expectedFile = "T|1|Read book";
        assertEquals(expectedString, todo.toString());
        assertEquals(expectedFile, todo.toFile());
    }
}

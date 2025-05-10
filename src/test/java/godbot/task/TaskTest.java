package godbot.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void testToDoToString() {
        ToDo task = new ToDo("Buy milk");

        assertNotNull(task, "Task should not be null");
        assertEquals("[T][ ]Buy milk", task.toString(), "ToDo toString() should match expected format");
    }
}


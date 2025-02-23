package main.java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void getStatusIcon_allTasks_success() {
        Task task = new Task("reading");
        assertEquals(" ", task.getStatusIcon());
        task.setDone();
        assertEquals("X", task.getStatusIcon());
    }

    
}

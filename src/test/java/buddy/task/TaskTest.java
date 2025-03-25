package buddy.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void initialiseReturnEmptyStringForStatus() {
        Task task = new Todo("Test");
        Assertions.assertEquals(" ", task.getStatusIcon());
    }


    @Test
    public void markTaskAsDone_returnXForStatus() {
        Task task = new Todo("Test");
        task.markTaskAsDone();
        Assertions.assertEquals("X", task.getStatusIcon());
    }


    @Test
    public void toString_returnCorrectFormat() {
        Task task = new Todo("Test");
        String expected = "[T][ ] Test";
        Assertions.assertEquals(expected, task.toString());
    }
}

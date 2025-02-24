package chitti;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void taskCreation(){
        Task task = new DeadlineTask("hi","2024-12-12");
        assertEquals("[D][ ] hi (by: Dec 12 2024)", task.toString());
    }

    @Test
    public void fileFormat(){
        Task task = new DeadlineTask("hi","2024-12-12");
        assertEquals("D|0|hi|2024-12-12",task.toFileFormat());
    }
}

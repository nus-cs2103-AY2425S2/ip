package chitti;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void eventCreation(){
        Task task = new ToDoTask("hi");
        assertEquals("[T][ ] hi", task.toString());
    }
    @Test
    public void fileFormat(){
        Task task = new ToDoTask("hi");
        assertEquals("T|0|hi", task.toFileFormat());
    }
}

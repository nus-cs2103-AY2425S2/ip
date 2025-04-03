package ziyang;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class todoTaskTest {
    @Test
    public void dummyTest() {
        todoTask task = new todoTask("stuff");
        assertEquals(task.toString(), "[T][ ]:stuff");
    }

    @Test
    public void anotherDummyTest() {
        deadlineTask task = new deadlineTask("stuff", "2020-01-01");
        assertEquals(task.toString(), "[D][ ]:stuff(by: 2020-01-01)");
    }
}

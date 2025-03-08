package wizt.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void createToDoTask() {
        String input1 = "todo code";

        String substr = input1.substring("todo".length());
        Task t = new Todo(substr);
        assertEquals("[T][ ] code" , t.toString());
    }



}

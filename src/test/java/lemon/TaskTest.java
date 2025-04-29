package lemon;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void toStringTest() {
        assertEquals("[ ] read a book", new Task("read a book").toString());
    }

    @Test
    public void martDoneTest() {
        Task test = new Task("finish coding assignment");
        test.markDone();
        assertEquals("[X] finish coding assignment", test.toString());
    }
}

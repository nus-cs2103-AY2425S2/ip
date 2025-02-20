package nana.logic;

import nana.logic.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testToString() {
        assertEquals("[T][ ]read book", new Todo("read book").toString());

    }
}

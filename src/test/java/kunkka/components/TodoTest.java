package kunkka.components;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    
    @Test
    public void testToString() {
        Todo todo = new Todo("read book", false);
        assertEquals("[T][ ] read book", todo.toString());
    }

    
}

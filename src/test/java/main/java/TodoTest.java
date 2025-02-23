package main.java;
//same package as the class being tested
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] Reading books", new Todo("Reading books").toString());
    }

    @Test
    public void toFileForm_toDoTask_success() {
        assertEquals("T | 0 | Reading books", new Todo("Reading books").toFileForm());
    }
}

package nana.logic;

import nana.logic.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDeadline {
    @Test
    public void testToStorage() {
        assertEquals("false D deadline return book /by 2005-03-12", new Deadline("return book", "2005-03-12").toStorage());
    }
}

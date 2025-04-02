package orim.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void generateLog_after_multiple_times_of_mark_and_unmark() {
        Todo test = new Todo("return book");
        test.mark();
        test.unmark();
        test.unmark();
        test.mark();
        assertEquals("T,1,return book", test.generateLog());
    }

}

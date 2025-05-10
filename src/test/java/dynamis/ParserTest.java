package dynamis;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void serialiseTest(){
        Parser p = new Parser();
        assertEquals("T | 0 | lmoa", p.serialiseTask(new Todo("lmoa")));
        assertEquals("D | 0 | test 2 | today"
                , p.serialiseTask(new Deadline("test 2", "today")));
        assertEquals("E | 0 | test 3 | 2025-01-01 | 2025-01-02"
                , p.serialiseTask(new Event("test 3", "2025-01-01", "2025-01-02")));
    }

    @Test
    public void deserialiseTest(){
        Parser p = new Parser();
        assertEquals(new Todo("test").toString()
                , p.deserializeTask("T | 0 | test").toString());
        assertEquals(new Deadline("test 2", "2025-01-01").toString()
                , p.deserializeTask("D | 0 | test 2 | 2025-01-01").toString());
        assertEquals(new Event("test 3", "today", "2025-01-01").toString()
                , p.deserializeTask("E | 0 | test 3 | today | 2025-01-01").toString());
    }
}

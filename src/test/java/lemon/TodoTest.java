package lemon;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void anotherDummyTest(){
        assertEquals(4, 4);
    }

    @Test
    public void toStringTest() throws Exception {
        assertEquals("[T][ ] Japanese homework", new Todo("Japanese homework").toString());
    }

    /*
    @Test
    public void wrongFormatTest() throws Exception {
        try {
            assertEquals("[T][ ] ", new Todo(""));
        } catch (Exception e) {
            assertEquals("Sorry! Todo description cannot be empty.", e.getMessage());
        }

    }  */


}


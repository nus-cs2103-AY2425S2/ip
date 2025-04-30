package nikingoda.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import nikingoda.NikingodaException.NikingodaException;
import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void testToString_NotDone() throws NikingodaException {
        Event event = new Event("Meeting", "1200 15/10/2023", "1400 15/10/2023");
        String expected = "[E][ ] Meeting (from: 12:00 PM, Oct 15 2023 to: 2:00 PM, Oct 15 2023)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testToFile_NotDone() throws  NikingodaException {
        Event event = new Event("Meeting", "1200 15/10/2023", "1400 15/10/2023");
        String expected = "E|0|Meeting|1200 15/10/2023|1400 15/10/2023";
        assertEquals(expected, event.toFile());
    }

    @Test
    public void testUpdateBegin() throws NikingodaException {
        Event event = new Event("Meeting", "1200 15/10/2023", "1400 15/10/2023");
        event.updateBegin("1300 15/10/2023");
        // "1300 15/10/2023" formats to "1:00 PM, Oct 15 2023"
        String expected = "[E][ ] Meeting (from: 1:00 PM, Oct 15 2023 to: 2:00 PM, Oct 15 2023)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testUpdateEnd() throws NikingodaException {
        Event event = new Event("Meeting", "1200 15/10/2023", "1400 15/10/2023");
        event.updateEnd("1500 15/10/2023");
        // "1500 15/10/2023" formats to "3:00 PM, Oct 15 2023"
        String expected = "[E][ ] Meeting (from: 12:00 PM, Oct 15 2023 to: 3:00 PM, Oct 15 2023)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testDoneStatus() {
        Event event = new Event("Conference", "0900 20/10/2023", "1700 20/10/2023", true);
        String expectedToString = "[E][X] Conference (from: 9:00 AM, Oct 20 2023 to: 5:00 PM, Oct 20 2023)";
        String expectedToFile = "E|1|Conference|0900 20/10/2023|1700 20/10/2023";
        assertEquals(expectedToString, event.toString());
        assertEquals(expectedToFile, event.toFile());
    }
}

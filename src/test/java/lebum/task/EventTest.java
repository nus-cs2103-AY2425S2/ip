package lebum.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EventTest {
    @Test
    void print_withDates_correctFormat() {
        Event event = new Event("meeting", "2024-01-26", "2024-01-27");
        assertEquals("[E] [ ] meeting (from: 2024-01-26 to: 2024-01-27)", event.print());
    }

    @Test
    void getPeriod_returnsCorrectFormat() {
        Event event = new Event("meeting", "2024-01-26", "2024-01-27");
        assertEquals(" (from: 2024-01-26 to: 2024-01-27)", event.getPeriod());
    }
}
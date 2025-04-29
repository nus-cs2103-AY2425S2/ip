package xuxin.task;

import org.junit.jupiter.api.Test;
import xuxin.exception.DukeException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {
    @Test
    void eventTestWithDate() throws DukeException {
        Event test = new Event("event", "12/01/2025", "12/01/2025");
        assertEquals("[E][ ] event (from: Jan 12 2025 to: Jan 12 2025)", test.toString(), "toString() method works");

        test.markTask();
        assertEquals("[E][x] event (from: Jan 12 2025 to: Jan 12 2025)", test.toString(), "markTask() method works");
    }
}
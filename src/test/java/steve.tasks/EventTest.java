package steve.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EventTest {
    private ArrayList<Task> tasks;
    private TaskManager taskManager;

    @BeforeEach
    public void setUp() {
        tasks = new ArrayList<>(); // Initialize the ArrayList before each test
    }

    @Test
    public void message() {
        String input = "meeting /from 2025-02-13 1600 /to 2025-02-13 1700";
        Event e = new Event(input);
        tasks.add(e);
        TaskManager taskManager = new TaskManager(tasks);

        String expected = "_________________________\n"
                + "     Got it. I've added this task:\n"
                + "       [E][ ] meeting " + "(From: Feb 13 2025, 04:00 pm to: Feb 13 2025, 05:00 pm) \n"
                + "     Now you have " + taskManager.getTaskSize() + " tasks in the list.\n"
                + "_________________________\n";

        assertEquals(expected, e.toString());
    }

    @Test
    public void getDescription() {
        String input = "driving lesson /from 2025-02-13 1200 /to 2025-02-13 1330";
        Event e = new Event(input);
        String expected = "driving lesson (From: Feb 13 2025, 12:00 pm to: Feb 13 2025, 01:30 pm) ";
        assertEquals(expected, e.getDescription());
    }

    @Test
    public void invalidFormatEvent() {
        String input = "meeting /from 2025-02-13 /to 2025-02-13 1700"; // Missing time for "from"
        Event e = new Event(input);
        String expected = Event.invalidFormatMessage();
        assertEquals(expected, e.messageString());
    }

    @Test
    public void invalidDateOrderEvent() {
        String input = "meeting /from 2025-02-13 1800 /to 2025-02-13 1700"; // End time before start time
        Event e = new Event(input);
        String expected = Event.invalidDateOrderMessage();
        assertEquals(expected, e.messageString());
    }

    @Test
    public void emptyDescriptionEvent() {
        String input = "/from 2025-02-13 1200 /to 2025-02-13 1330";
        Event e = new Event(input);
        String expected = Event.invalidFormatMessage();
        assertEquals(expected, e.messageString());
    }

    @Test
    public void invalidDateFormatEvent() {
        String input = "party /from 2025-13-02 1400 /to 2025-13-02 1600"; // Month out of range
        Event e = new Event(input);
        String expected = Event.invalidFormatMessage();
        assertEquals(expected, e.messageString());
    }

    @Test
    public void validEdgeCaseEvent() {
        String input = "conference /from 2024-02-29 1000 /to 2024-02-29 1200"; // Leap year
        Event e = new Event(input);
        String expected = "conference (From: Feb 29 2024, 10:00 am to: Feb 29 2024, 12:00 pm) ";
        assertEquals(expected, e.getDescription());
    }

    @Test
    public void validEvent() {
        String input = "hackathon /from 2025-12-10 2200 /to 2025-12-11 0600"; // Overnight event
        Event e = new Event(input);
        String expected = "hackathon (From: Dec 10 2025, 10:00 pm to: Dec 11 2025, 06:00 am) ";
        assertEquals(expected, e.getDescription());
    }

    @Test
    public void checkIsValid() {
        Event validEvent = new Event("meeting /from 2025-02-13 1600 /to 2025-02-13 1700");
        Event invalidEvent = new Event("meeting /from 2025-02-13 /to 2025-02-13 1700"); // Missing time

        assertTrue(validEvent.isValid());
        assertFalse(invalidEvent.isValid());
    }

    @Test
    public void toStringTest() {
        Event e = new Event("workshop /from 2025-06-15 0900 /to 2025-06-15 1200");
        tasks.add(e);
        TaskManager taskManager = new TaskManager(tasks);
        String expected = "_________________________\n"
                + "     Got it. I've added this task:\n"
                + "       [E][ ] workshop (From: Jun 15 2025, 09:00 am to: Jun 15 2025, 12:00 pm) \n"
                + "     Now you have " + taskManager.getTaskSize() + " tasks in the list.\n"
                + "_________________________\n";

        assertEquals(expected, e.toString());
    }
}

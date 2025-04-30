package nikingoda.Task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testToString_NotDone() {
        // Create a Deadline with isDone defaulting to false.
        Deadline deadline = new Deadline("Submit assignment", "2359 30/09/2023");
        // The outputDeadline() formatter converts "2359 30/09/2023" into "11:59 PM, Sep 30 2023"
        String expected = "[D][ ] Submit assignment (by: 11:59 PM, Sep 30 2023)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void testToString_Done() {
        // Create a Deadline with isDone defaulting to false.
        Deadline deadline = new Deadline("Submit assignment", "2359 30/09/2023", true);
        // The outputDeadline() formatter converts "2359 30/09/2023" into "11:59 PM, Sep 30 2023"
        String expected = "[D][X] Submit assignment (by: 11:59 PM, Sep 30 2023)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void testToFile_NotDone() {
        Deadline deadline = new Deadline("Submit assignment", "2359 30/09/2023");
        // The inputDeadline() formatter converts the deadline to "2359 30/9/2023" (note: month without leading zero)
        String expected = "D|0|Submit assignment|2359 30/9/2023";
        assertEquals(expected, deadline.toFile());
    }

    @Test
    public void testToFile_Done() {
        Deadline deadline = new Deadline("Submit assignment", "2359 30/09/2023", true);
        // The inputDeadline() formatter converts the deadline to "2359 30/9/2023" (note: month without leading zero)
        String expected = "D|1|Submit assignment|2359 30/9/2023";
        assertEquals(expected, deadline.toFile());
    }

    @Test
    public void testUpdateDeadline() {
        Deadline deadline = new Deadline("Submit assignment", "2359 30/09/2023");
        // The outputDeadline() formatter converts "2359 30/09/2023" into "11:59 PM, Sep 30 2023"
        String expected0 = "[D][ ] Submit assignment (by: 11:59 PM, Sep 30 2023)";
        assertEquals(expected0, deadline.toString());
        // Update deadline to a new value.
        deadline.updateDeadline("0000 01/10/2023");
        // "0000 01/10/2023" becomes "12:00 AM, Oct 1 2023" after formatting.
        String expected = "[D][ ] Submit assignment (by: 12:00 AM, Oct 1 2023)";
        assertEquals(expected, deadline.toString());
    }
}

package softess;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class DeadlineTest {
    @Test
    void testToString() {
        Deadline deadline = new Deadline("Submit assignment", "2025-02-10 2359", false);
        String expected = "[D][ ] Submit assignment (by: Feb 10 2025, 11:59 pm)";
        assertEquals(expected, deadline.toString(), "The toString method should format the date correctly.");
    }

}

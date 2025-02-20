package amara.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private Deadline deadline = new Deadline(
            "Do CM3221 disconnection approach homework",
            LocalDateTime.parse("2025-01-31 1600", this.formatter)
    );

    @Test
    public void testDeadlineFunctionality() {
        assertEquals("[D][ ] Do CM3221 disconnection approach homework "
                + "(by: Jan 31 2025, 4:00 PM)", deadline.toString());

        this.deadline.markTask();
        assertEquals("[D][X] Do CM3221 disconnection approach homework "
                + "(by: Jan 31 2025, 4:00 PM)", deadline.toString());

        this.deadline.unmarkTask();
        assertEquals("[D][ ] Do CM3221 disconnection approach homework "
                + "(by: Jan 31 2025, 4:00 PM)", deadline.toString());

        this.deadline.getSavedFormat();
        assertEquals("D,0,Do CM3221 disconnection approach homework,2025-01-31T16:00",
                deadline.getSavedFormat());
    }
}

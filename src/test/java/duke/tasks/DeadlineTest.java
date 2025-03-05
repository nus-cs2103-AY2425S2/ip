package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import g.tasks.Deadline;

public class DeadlineTest {

    @Test
    public void testToFileString() {
        Deadline deadline = new Deadline("Submit assignment", "2025-02-15", false);
        assertEquals("D | 0 | Submit assignment | 2025-02-15", deadline.toFileString());

        Deadline completedDeadline = new Deadline("Project demo", "2025-03-01", true);
        assertEquals("D | 1 | Project demo | 2025-03-01", completedDeadline.toFileString());
    }

    @Test
    public void testToString() {
        Deadline deadline = new Deadline("Submit report", "2025-02-20", false);
        assertEquals("[D][ ] Submit report (by: Feb 20 2025)", deadline.toString());

        Deadline completedDeadline = new Deadline("Presentation", "2025-03-10", true);
        assertEquals("[D][X] Presentation (by: Mar 10 2025)", completedDeadline.toString());
    }
}

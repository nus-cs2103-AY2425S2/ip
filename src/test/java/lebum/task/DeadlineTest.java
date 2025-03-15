package lebum.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {
    @Test
    void print_withDateAndTime_correctFormat() {
        Deadline deadline = new Deadline("homework", "26/01/2024 1430");
        assertEquals("[D] [ ] homework(by:26th of January 2024, 2:30 pm)", deadline.print());
    }

    @Test
    void print_dateOnly_correctFormat() {
        Deadline deadline = new Deadline("homework", "26/01/2024");
        assertEquals("[D] [ ] homework(by:26th of January 2024)", deadline.print());
    }

}
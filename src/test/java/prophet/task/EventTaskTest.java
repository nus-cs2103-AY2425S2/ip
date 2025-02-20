package prophet.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class EventTaskTest {
    @Test
    public void testToStringValid() {
        // Test case where the event task is from 2021-09-01 to 2021-09-02
        EventTask eventTask = new EventTask(
                "CS2103T Lecture",
                LocalDateTime.parse("2021-09-01T00:00"),
                LocalDateTime.parse("2021-09-02T23:59"));
        assertEquals("E | [ ] CS2103T Lecture from: Sep 01 2021 0000 to: Sep 02 2021 2359\n",
                eventTask.toString());
    }

    @Test
    public void testGetStatusIconValid() {
        // Test case where the event task is from 2021-09-01 to 2021-09-02
        EventTask eventTask = new EventTask(
                "CS2103T Lecture",
                LocalDateTime.parse("2021-09-01T00:00"),
                LocalDateTime.parse("2021-09-02T23:59"));
        eventTask.setDone();
        assertEquals("E | [X] ", eventTask.getStatusIcon());
    }

}

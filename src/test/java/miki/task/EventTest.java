package miki.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testEvent() {
        try {
            // Test Task
            LocalDateTime start = LocalDateTime.parse("2021-08-21T18:00");
            LocalDateTime end = LocalDateTime.parse("2021-08-21T20:00");
            Task task = new Event("Test", start, end);
            System.out.println(task.toString());
            System.out.println(task.toStorageFormat());

            assertEquals("[E] " + "[ ]  Test" + "\n" + "\n"
                    + "        From  21 AUGUST 2021, 18:00  to  21 AUGUST 2021, 20:00",
                    task.toString());
        } catch (Error e) {
            fail();
        }
    }

    @Test
    public void testEventToStorageFormat() {
        try {
            // Test Task
            LocalDateTime start = LocalDateTime.parse("2021-08-21T18:00");
            LocalDateTime end = LocalDateTime.parse("2021-08-21T20:00");
            Task task = new Event("Test", start, end);

            assertEquals("E | 0 | Test |  | 2021-08-21T18:00 | 2021-08-21T20:00", task.toStorageFormat());
        } catch (Error e) {
            fail();
        }
    }
}

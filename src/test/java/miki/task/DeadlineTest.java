package miki.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testDeadline() {
        try {
            // Test Task
            LocalDateTime deadline = LocalDateTime.parse("2021-08-21T20:00");
            Task task = new Deadline("Test", deadline);
            System.out.println(task.toString());
            System.out.println(task.toStorageFormat());

            assertEquals("[D] " + "[ ]  Test" + "\n" + "\n"
                    + "        Deadline: 21 AUGUST 2021, 20:00",
                    task.toString());
        } catch (Error e) {
            fail();
        }
    }

    @Test
    public void testDeadlineToStorageFormat() {
        try {
            // Test Task
            LocalDateTime deadline = LocalDateTime.parse("2021-08-21T20:00");
            Task task = new Deadline("Test", deadline);

            assertEquals("D | 0 | Test |  | 2021-08-21T20:00", task.toStorageFormat());
        } catch (Error e) {
            fail();
        }
    }
}

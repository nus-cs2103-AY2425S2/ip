package kunkka.command;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import kunkka.tasklist.Tasklist;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventCommandTest {

    @Test
    public void testValidEvent() {
        EventCommand event = new EventCommand("Project meeting", "2021-08-24", "2021-08-25");
        Tasklist tasks = new Tasklist();

        // Capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Execute the command
        event.execute(tasks);

        // Verify the output
        String expectedOutput = "Got it. I've added this task:";
        assertTrue(outContent.toString().contains(expectedOutput));
        assertEquals("[E][ ] Project meeting (from: Aug 24 2021 to: Aug 25 2021)", tasks.getTasks().get(0).toString());

        // Reset the System.out
        System.setOut(System.out);
    }

    @Test
    public void testEmptyDescription() {
        EventCommand event = new EventCommand("", "2021-08-24", "2021-08-25");
        Tasklist tasks = new Tasklist();

        // Capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Execute the command
        event.execute(tasks);

        // Verify the output
        String expectedOutput = "Error: Task description cannot be empty";
        assertTrue(outContent.toString().contains(expectedOutput));

        // Reset the System.out
        System.setOut(System.out);
    }

    @Test
    public void testEmptyStartDate() {
        EventCommand event = new EventCommand("Project meeting", "", "2021-08-25");
        Tasklist tasks = new Tasklist();

        // Capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Execute the command
        event.execute(tasks);

        // Verify the output
        String expectedOutput = "Error: Event start time cannot be empty";
        assertTrue(outContent.toString().contains(expectedOutput));

        // Reset the System.out
        System.setOut(System.out);
    }

    @Test
    public void testEmptyEndDate() {
        EventCommand event = new EventCommand("Project meeting", "2021-08-24", "");
        Tasklist tasks = new Tasklist();

        // Capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Execute the command
        event.execute(tasks);

        // Verify the output
        String expectedOutput = "Error: Event end time cannot be empty";
        assertTrue(outContent.toString().contains(expectedOutput));

        // Reset the System.out
        System.setOut(System.out);
    }

    @Test
    public void testEndDateBeforeStartDate() {
        EventCommand event = new EventCommand("Project meeting", "2021-08-25", "2021-08-24");
        Tasklist tasks = new Tasklist();

        // Capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Execute the command
        event.execute(tasks);

        // Verify the output
        String expectedOutput = "Error: Event end time must be after start time";
        assertTrue(outContent.toString().contains(expectedOutput));

        // Reset the System.out
        System.setOut(System.out);
    }
}
package clank.command;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import clank.exception.ClankException;

/**
 * Unit tests for the {@code ReminderCommand} class.
 */
public class ReminderCommandTest {

    /**
     * Tests the creation of a valid ReminderCommand.
     */
    @Test
    public void testValidReminder() throws ClankException {
        Command reminder = new ReminderCommand("reminder 3", false);
        assertNotNull(reminder);
    }

    /**
     * Tests handling of an incorrect format in ReminderCommand.
     */
    @Test
    public void testInvalidReminderFormat() {
        assertThrows(ClankException.class, () -> new ReminderCommand("reminder", false));
    }
}

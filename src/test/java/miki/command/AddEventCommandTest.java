package miki.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class AddEventCommandTest {
    @Test
    public void testAddEventCommand_isExit() {
        try {
            Command command = new AddEventCommand("test", LocalDateTime.now(), LocalDateTime.now());

            assertEquals(false, command.checkExit());
        } catch (Error e) {
            fail();
        }
    }
}

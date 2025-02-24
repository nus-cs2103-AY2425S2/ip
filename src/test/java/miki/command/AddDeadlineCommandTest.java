package miki.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class AddDeadlineCommandTest {
    @Test
    public void testAddDeadlineCommand_isExit() {
        try {
            Command command = new AddDeadlineCommand("test", LocalDateTime.now());

            assertEquals(false, command.checkExit());
        } catch (Error e) {
            fail();
        }
    }
}

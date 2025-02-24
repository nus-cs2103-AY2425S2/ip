package miki.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class UnmarkCommandTest {
    @Test
    public void testUnmarkCommand_isExit() {
        try {
            Command command = new UnmarkCommand(1);

            assertEquals(false, command.checkExit());
        } catch (Error e) {
            fail();
        }
    }
}

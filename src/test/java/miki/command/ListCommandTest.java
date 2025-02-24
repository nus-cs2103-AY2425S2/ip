package miki.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class ListCommandTest {
    @Test
    public void testListCommand_isExit() {
        try {
            Command command = new ListCommand();

            assertEquals(false, command.checkExit());
        } catch (Error e) {
            fail();
        }
    }
}

package miki.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class FindCommandTest {
    @Test
    public void testFindCommand_isExit() {
        try {
            Command command = new FindCommand("test");

            assertEquals(false, command.checkExit());
        } catch (Error e) {
            fail();
        }
    }
}

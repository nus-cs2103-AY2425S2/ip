package miki.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class AddToDoCommandTest {
    @Test
    public void testAddToDoCommand_isExit() {
        try {
            Command command = new AddToDoCommand("test");

            assertEquals(false, command.checkExit());
        } catch (Error e) {
            fail();
        }
    }
}

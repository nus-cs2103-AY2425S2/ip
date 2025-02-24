package miki.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import miki.exception.MikiException;
import miki.ui.Ui;

public class ExitCommandTest {
    @Test
    public void testExitCommand_isExit() {
        try {
            Command command = new ExitCommand();

            assertEquals(true, command.checkExit());
        } catch (Error e) {
            fail();
        }
    }

    @Test
    public void testExitCommand_checkExecute() {
        try {
            Command command = new ExitCommand();
            String output = command.execute(null, new Ui(), null);
            assertEquals("End of session Dawg. Goodbye Dawg.\n", output);
        } catch (MikiException e) {
            fail("Should not throw exception");
        } catch (Error e) {
            fail();
        }
    }
}

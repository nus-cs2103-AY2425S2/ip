package bobandsteve.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import bobandsteve.command.ByeCommand;
import bobandsteve.exception.BobAndSteveException;

public class ParserTest {
    @Test
    public void createParser_wrongInput_exceptionThrown() {
        try {
            assertEquals(new ByeCommand(), Parser.parse("test"));
            fail();
        } catch (BobAndSteveException error) {
            assertEquals("Invalid command, Enter help to view all commands", error.getMessage());
        }
    }
}

package qwerty.ui;

import org.junit.jupiter.api.Test;

import qwerty.exceptions.InvalidIndexException;
import qwerty.exceptions.UnknownCommandException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    
    @Test
    void testValidateIndex() {

        String invalidDesc = "invalid";

        assertThrows(InvalidIndexException.class, () -> {
            Parser.validateIndex(invalidDesc);
        });
    }

    @Test
    void testCreateCommand() {

        String invalidCommand = "unknown";
        String description = "Lorem ipsum";

        assertThrows(UnknownCommandException.class, () -> {
            Parser.createCommand(invalidCommand, description);
        });
    }

}

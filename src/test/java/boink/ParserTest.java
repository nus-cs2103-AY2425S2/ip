package boink;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import boink.exceptions.BoinkException;
import boink.exceptions.InvalidTaskInputException;

public class ParserTest {

    @Test
    public void testInvalidDateFormat() {
        BoinkException exception = assertThrows(InvalidTaskInputException.class, () -> {
            Parser.createTaskFromInput("deadline /by 01/01/01 1234");
        });

        String output = "Invalid Task Input. Error: Invalid date format!";
        assertEquals(output, exception.getMessage());
    }

    @Test
    public void testEmptyInput() {
        BoinkException exception = assertThrows(BoinkException.class, () -> {
            Parser.parseUserInput("");
        });

        String output = "Empty input. Please enter a valid command!";
        assertEquals(output, exception.getMessage());
    }
}

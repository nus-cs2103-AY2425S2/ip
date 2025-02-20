package pixel.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import pixel.command.AddCommand;
import pixel.command.UpdateCommand;

public class ParserTest {

    @Test
    public void testParseFullCommand() {
        // valid AddCommand
        assertInstanceOf(AddCommand.class, Parser.parseFullCommand("deadline /by 2024-12-30 12:00"));

        // valid UpdateCommand
        assertInstanceOf(UpdateCommand.class, Parser.parseFullCommand("mark 0"));

        // invalid index for UpdateCommand
        try {
            Parser.parseFullCommand("mark x");
            fail();
        } catch (PixelException e) {
            assertEquals("Please input a valid task number!", e.getMessage());
        }

        // invalid command input
        try {
            Parser.parseFullCommand("hello");
            fail();
        } catch (PixelException e) {
            assertEquals("Sorry, I'm not sure what that means...", e.getMessage());
        }
    }

    @Test
    public void testParseDateTime() {
        // valid dateTime with time
        assertEquals(LocalDateTime.parse("2024-12-30T12:35"), Parser.parseDateTime("2024-12-30 12:35"));

        // valid dateTime without time
        assertEquals(LocalDateTime.parse("2024-09-25T00:00"), Parser.parseDateTime("2024-09-25"));

        // invalid dateTime
        try {
            Parser.parseDateTime("2021-05-1k 12:00");
            fail();
        } catch (PixelException e) {
            assertEquals("Please use the format YYYY-MM-DD HH:MM for the date and time!", e.getMessage());
        }

        // wrong format
        try {
            Parser.parseDateTime("2021/07/15 12:00");
            Parser.parseDateTime("2021-04-17 05.29");
            fail();
        } catch (PixelException e) {
            assertEquals("Please use the format YYYY-MM-DD HH:MM for the date and time!", e.getMessage());
        }
    }
}

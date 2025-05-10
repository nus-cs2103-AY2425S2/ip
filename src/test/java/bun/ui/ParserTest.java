package bun.ui;  //same package as the class being tested

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testDescriptionWithMultipleWords() throws DateFormatException, InvalidCommandException, MissingFieldException {
        assertEquals((new AddCommand(new Event("read books", "2004-03-19", "2004-03-20"))), Parser.parse(new String[]{"Event", "read books /from 2024-03-19 /to 2024-03-20"}));
    }
}

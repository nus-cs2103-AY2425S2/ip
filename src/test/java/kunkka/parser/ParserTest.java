package kunkka.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import kunkka.command.Mark;


public class ParserTest {
    
    @Test
    public void testParseCommand() {
        assertEquals("list", Parser.parseCommand("list").getType());
        assertEquals("mark", Parser.parseCommand("mark 1").getType());
        assertEquals(1, ((Mark) Parser.parseCommand("mark 1")).getIndex());
        assertEquals(null, Parser.parseCommand("nonsense"));    
    }

    


}

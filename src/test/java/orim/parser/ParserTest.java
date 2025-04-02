package orim.parser;

import org.junit.jupiter.api.Test;
import orim.myexception.IncorrectInputException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parser_incorrectInput_exceptionThrown() {
        try {
            Parser.parse("wrongInput");
            fail();
        } catch (IncorrectInputException e) {
            assertEquals(" OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }

    }

}


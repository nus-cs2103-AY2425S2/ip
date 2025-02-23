package bob.parser;

import java.io.IOException;

import bob.command.Command;
import bob.command.WrongCommandException;

/**
 * This functional interface represents a function that takes in a string and returns a Command.
 * It is implemented by the numerous parse functions in Parser.
 */
@FunctionalInterface
public interface ParseFunction {
    Command apply(String userInput) throws
            WrongCommandException,
            IOException,
            NumberFormatException,
            IndexOutOfBoundsException;
}

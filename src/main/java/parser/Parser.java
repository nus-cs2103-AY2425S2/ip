package parser;

import java.io.IOException;

import commands.*;

/**
 * Parses user input and returns the corresponding Command object.
 */
public class Parser {
    /**
     * Parses the user input and returns a Command object.
     * @param input The user input.
     * @return The corresponding Command object.
     * @throws IOException If an unknown command is encountered.
     */
    public static Command parse(String input) throws IOException {
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("delete")) {
            return new DeleteCommand(input);
        } else if (input.startsWith("find")) {
            return new FindCommand(input);
        } else if (input.startsWith("todo")) {
            return new AddTodoCommand(input);
        } else if (input.startsWith("deadline")) {
            return new AddDeadlineCommand(input);
        } else if (input.startsWith("event")) {
            return new AddEventCommand(input);
        } else if (input.startsWith("edit")) {
            return new EditCommand(input);
        } else if (input.startsWith("mark")) {
            return new MarkCommand(input);
        } else if (input.startsWith("unmark")) {
            return new UnmarkCommand(input);
        } else {
            throw new IOException("Unknown command.");
        }
    }
}

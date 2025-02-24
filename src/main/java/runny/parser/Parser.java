package runny.parser;

import runny.RunnyException;
import runny.commands.ByeCommand;
import runny.commands.Command;
import runny.commands.DeadlineCommand;
import runny.commands.DeleteCommand;
import runny.commands.EventCommand;
import runny.commands.FindCommand;
import runny.commands.ListCommand;
import runny.commands.MarkCommand;
import runny.commands.TodoCommand;
import runny.commands.UndoCommand;
import runny.commands.UnmarkCommand;

/**
 * Parses user input to create corresponding Command objects for Runny Chatbot.
 * Determines the type of command based on the keyword in the input.
 */
public class Parser {

    /**
     * Parses the full user input and generates the representing Command object.
     *
     * @param fullCommand The full user input.
     * @return The Command object corresponding to the user input.
     * @throws RunnyException If the user input is not recognized.
     */
    public static Command parse(String fullCommand) throws RunnyException {
        assert fullCommand != null;
        String[] splitCommand = fullCommand.split(" ", 2);
        String command = splitCommand[0];
        String info = "";

        for (int i = 1; i < splitCommand.length; i++) {
            if (splitCommand.length == 2) {
                info = splitCommand[1];
            }
        }

        switch (command) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(info);
        case "unmark":
            return new UnmarkCommand(info);
        case "todo":
            return new TodoCommand(info);
        case "event":
            return new EventCommand(info);
        case "deadline":
            return new DeadlineCommand(info);
        case "delete":
            return new DeleteCommand(info);
        case "find":
            return new FindCommand(info);
        case "undo":
            return new UndoCommand();
        default:
            throw new RunnyException("OOPS!!! I'm sorry, but I do not understand that command :-(\n");
        }
    }
}

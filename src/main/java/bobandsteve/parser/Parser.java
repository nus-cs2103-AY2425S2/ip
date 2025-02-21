package bobandsteve.parser;

import bobandsteve.command.ByeCommand;
import bobandsteve.command.Command;
import bobandsteve.command.CommandEnum;
import bobandsteve.command.DeadlineCommand;
import bobandsteve.command.DeleteCommand;
import bobandsteve.command.EventCommand;
import bobandsteve.command.FindCommand;
import bobandsteve.command.HelpCommand;
import bobandsteve.command.ListCommand;
import bobandsteve.command.MarkCommand;
import bobandsteve.command.SortCommand;
import bobandsteve.command.TodoCommand;
import bobandsteve.command.UnmarkCommand;
import bobandsteve.exception.BobAndSteveException;
import bobandsteve.exception.InvalidCommandException;

/**
 * The Parser class is responsible for parsing the user input string into
 * the corresponding Command object. It interprets the command type and
 * creates an instance of the appropriate Command subclass.
 */
public class Parser {

    /**
     * Parses the given input string into a Command object based on the command type.
     * The input is split into a command keyword and its arguments. If the command
     * is invalid, an exception is thrown.
     *
     * @param input The user input string to be parsed.
     * @return The corresponding Command object based on the parsed command.
     * @throws BobAndSteveException If the command is invalid or an error occurs during parsing.
     */
    public static Command parse(String input) throws BobAndSteveException {
        String[] split = input.split(" ", 2);
        CommandEnum command;
        try {
            command = CommandEnum.valueOf(split[0].trim().toUpperCase());
        } catch (IllegalArgumentException err) {
            throw new InvalidCommandException("Invalid command, Enter help to view all commands");
        }

        if (command == CommandEnum.FIND) {
            return new FindCommand(input);
        } else if (command == CommandEnum.BYE) {
            return new ByeCommand();
        } else if (command == CommandEnum.HELP) {
            return new HelpCommand();
        } else if (command == CommandEnum.LIST) {
            return new ListCommand();
        } else if (command == CommandEnum.TODO) {
            return new TodoCommand(input);
        } else if (command == CommandEnum.DEADLINE) {
            return new DeadlineCommand(input);
        } else if (command == CommandEnum.EVENT) {
            return new EventCommand(input);
        } else if (command == CommandEnum.UNMARK) {
            return new UnmarkCommand(input);
        } else if (command == CommandEnum.MARK) {
            return new MarkCommand(input);
        } else if (command == CommandEnum.DELETE) {
            return new DeleteCommand(input);
        } else if (command == CommandEnum.SORT) {
            return new SortCommand();
        } else {
            throw new InvalidCommandException("Invalid command, Enter help to view all commands");
        }
    }
}

package controller;

import commands.AbstractCommand;
import commands.AddCommand;
import commands.DeleteCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.SortCommand;
import commands.UnmarkCommand;
import taskObjects.*;

import enums.CommandTypes;
import exception.InvalidInputException;
import exception.SyntaxException;

/**
 * {@code Parser} class responsible for parsing input string and converting them
 * to executable {@code AbstractCommand} class
 */
public class Parser {

    private final Storage storage;

    /**
     * Construct a {@code Parser} class instance with specified storage
     *
     * @param storage The storage instance that will handle persistent data saving
     *                and loading
     */
    public Parser(Storage storage) {
        this.storage = storage;
    }

    /**
     * Parses the input string into respective {@code AbstractCommand} class
     *
     * @param input raw String input from user
     * @return The {@code AbstractCommand} class to be executed
     * @throws InvalidInputException @code
     */
    public AbstractCommand parse(String input) throws InvalidInputException {
        String[] parsed = input.split(" ", 2);
        CommandTypes commandTypes = CommandTypes.fromString(parsed[0]);

        switch (commandTypes) {
        case BYE, Q:
            return new ExitCommand(this.storage);

        case LIST, LS:
            return new ListCommand();

        case SORT:
            return new SortCommand();

        case MARK:
            if (parsed.length < 2) {
                throw new SyntaxException("mark", "mark <index of item>");
            }
            return new MarkCommand(Integer.parseInt(parsed[1]));

        case UNMARK:
            if (parsed.length < 2) {
                throw new SyntaxException("unmark", "unmark <index of item>");
            }
            return new UnmarkCommand(Integer.parseInt(parsed[1]));

        case DELETE, DEL:
            if (parsed.length < 2) {
                throw new SyntaxException("delete", "delete <index of item>");
            }
            return new DeleteCommand(Integer.parseInt(parsed[1]));

        case FIND:
          if (parsed.length < 2) {
            throw new SyntaxException("Find", "Find <keyword>");
          }
          return new FindCommand(parsed[1]);

        case TODO:
            if (parsed.length < 2) {
                throw new SyntaxException("Todo", "todo <Task>");
            }
            return new AddCommand(new Todo(parsed[1], false));

        case DEADLINE:
            if (parsed.length < 2) {
                throw new SyntaxException("Deadline", "deadline <Task> /by <Time>");
            }
            String[] deadlinePartition = parsed[1].split("/by");
            if (deadlinePartition.length < 2) {
                throw new SyntaxException("Deadline", "deadline <Task> /by <Time>");
            }
            String deadlineDescription = deadlinePartition[0].trim();
            String deadlineBy = deadlinePartition[1].trim();
            return new AddCommand(new Deadline(deadlineDescription, false, deadlineBy));

        case EVENT:
            if (parsed.length < 2) {
                throw new SyntaxException("Event", "event <Task> /from <Time> /to <Time>");
            }
            String[] eventPartition1 = parsed[1].split("/from");
            if (eventPartition1.length < 2) {
                throw new SyntaxException("Event", "event <Task> /from <Time> /to <Time>");
            }
            String eventDescription = eventPartition1[0].trim();
            String[] eventPartition2 = eventPartition1[1].split("/to");
            if (eventPartition2.length < 2) {
                throw new SyntaxException("Event", "event <Task> /from <Time> /to <Time>");
            }
            String eventFrom = eventPartition2[0].trim();
            String eventTo = eventPartition2[1].trim();
            return new AddCommand(new Event(eventDescription, false, eventFrom, eventTo));
        default:
            throw new InvalidInputException("Sorry Commander, but I do not understand your orders.");
        }
    }
}

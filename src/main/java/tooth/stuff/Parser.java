package tooth.stuff;

import java.time.LocalDate;
import java.util.Arrays;

import tooth.command.ByeCommand;
import tooth.command.Command;
import tooth.command.DeadlineCommand;
import tooth.command.DeleteCommand;
import tooth.command.EventCommand;
import tooth.command.FindCommand;
import tooth.command.HelpCommand;
import tooth.command.ListCommand;
import tooth.command.LoadCommand;
import tooth.command.MarkCommand;
import tooth.command.SaveCommand;
import tooth.command.TodoCommand;
import tooth.command.UnmarkCommand;
import tooth.exception.InvalidCommandException;
import tooth.exception.InvalidParamException;

/**
 * Converts String to executable commands
 */
public class Parser {

    public Parser() {
    }

    /**
     * Parse String to convert it to a command
     *
     * @param input String input to be converted to Commands
     * @return Command that can then be executed
     * @throws InvalidParamException Error containing mistakes in input string
     */
    public Command parse(String input) throws InvalidParamException {
        if (input.indexOf('|') > -1) {
            throw new InvalidCommandException("Character [|] is not allowed");
        }
        String[] pieces = input.split(" ");
        String command = pieces[0];
        String s = String.join(" ", Arrays.copyOfRange(pieces, 1, pieces.length));

        switch (command) {
        case "bye":
            if (pieces.length != 1) {
                throw new InvalidParamException("List does not have any description");
            }
            return new ByeCommand();
        case "list":
            if (pieces.length != 1) {
                throw new InvalidParamException("List does not have any description");
            }
            return new ListCommand();

        case "mark":
            if (pieces.length != 2) {
                throw new InvalidParamException("Mark only requires 1 parameter (Index)");
            }
            try {
                return new MarkCommand(Integer.parseInt(s));
            } catch (RuntimeException e) {
                throw new InvalidParamException("Mark expects a Integer as an exception");
            }

        case "unmark":
            if (pieces.length != 2) {
                throw new InvalidParamException("Unmark only requires 1 parameter (Index)");
            }
            try {
                return new UnmarkCommand(Integer.parseInt(s));
            } catch (RuntimeException e) {
                throw new InvalidParamException("Unmark expects a Integer as an exception");
            }

        case "todo":
            if (s.isEmpty()) {
                throw new InvalidParamException("Todo requires a description");
            }
            return new TodoCommand(s);

        case "event":
            int fromIndex = s.indexOf("/from");
            int toIndex = s.indexOf("/to");
            if (s.isEmpty()) {
                throw new InvalidParamException("Event requires a description");
            } else if (fromIndex == -1 || toIndex == -1) {
                throw new InvalidParamException("Missing either /to or /from field");
            } else if (fromIndex == 0) {
                throw new InvalidParamException("Missing the title");
            }
            try {
                LocalDate from = LocalDate.parse(s.substring(fromIndex + 6, toIndex).trim());
                LocalDate to = LocalDate.parse(s.substring(toIndex + 4).trim());
                String eventString = s.substring(0, fromIndex).trim();
                return new EventCommand(eventString, from, to);
            } catch (RuntimeException e) {
                throw new InvalidParamException("Wrong date format. Ensure it is in YYYY-MM-DD");
            }

        case "deadline":
            int byIndex = s.indexOf("/by");
            if (s.isEmpty()) {
                throw new InvalidParamException("Deadline requires a description");
            } else if (byIndex == -1) {
                throw new InvalidParamException("Missing /by field");
            } else if (byIndex == 0) {
                throw new InvalidParamException("Missing the title");
            }
            try {
                LocalDate by = LocalDate.parse(s.substring(byIndex + 4).trim());
                String dealineString = s.substring(0, byIndex).trim();
                return new DeadlineCommand(dealineString, by);
            } catch (RuntimeException e) {
                throw new InvalidParamException("Wrong date format. Ensure it is in YYYY-MM-DD");
            }

        case "delete":
            if (pieces.length != 2) {
                throw new InvalidParamException("Delete only requires 1 parameter (Index)");
            }
            try {
                return new DeleteCommand(Integer.parseInt(s));
            } catch (RuntimeException e) {
                throw new InvalidParamException("Delete expects a Integer as an exception");
            }

        case "find":
            if (s.isEmpty()) {
                throw new InvalidParamException("Find needs a string to search for");
            }
            return new FindCommand(s);

        case "save":
            if (pieces.length != 1) {
                throw new InvalidParamException("List does not have any description");
            }
            return new SaveCommand();

        case "load":
            if (pieces.length != 1) {
                throw new InvalidParamException("List does not have any description");
            }
            return new LoadCommand();

        case "help":
            if (pieces.length != 1) {
                throw new InvalidParamException("Help does not have any description");
            }
            return new HelpCommand();

        default:
            throw new InvalidCommandException("tooth.command.Command " + command + " does not exist");
        }
    }
}

package wizt.parser;

import wizt.command.AddCommand;
import wizt.command.Command;
import wizt.command.DeleteCommand;
import wizt.command.ExitCommand;
import wizt.command.FindCommand;
import wizt.command.ListCommand;
import wizt.command.UpdateCommand;

/**
 * Represents the different commands the application recognizes.
 */
public class Parser {
    /**
     * Identifies which command the user has inputted.
     * @param fullCommand The full command string from user input.
     * @return The corresponding command type.
     */
    public static Command parse(String fullCommand) {
        if (fullCommand.equals("list")) {
            return new ListCommand();
        } else if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else if (fullCommand.contains("unmark") || fullCommand.contains("mark") || fullCommand.contains("update")) {
            return new UpdateCommand(fullCommand);
        } else if (fullCommand.contains("todo") || fullCommand.contains("deadline") || fullCommand.contains("event")) {
            return new AddCommand(fullCommand);
        } else if (fullCommand.contains("delete")) {
            return new DeleteCommand(fullCommand);
        } else if (fullCommand.contains("find")) {
            return new FindCommand(fullCommand);
        }
        return new Command();
    }
}

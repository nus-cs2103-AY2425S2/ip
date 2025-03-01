package bebop.command;

/**
 * Parses all the command to the Ui.
 */
public class Parser {

    /**
     * Parses command string into command type.
     *
     * @param command command being processed.
     * @return command of Command class.
     */
    public Command parse(String command) {
        String[] inputs = command.split(" ");
        switch (inputs[0]) {
        case "bye": return new ExitCommand();
        case "list": return new ListCommand();
        case "mark": return new MarkCommand(true, command);
        case "unmark": return new MarkCommand(false, command);
        case "todo": return new AddCommand("t", command);
        case "deadline": return new AddCommand("d", command);
        case "event": return new AddCommand("e", command);
        case "delete": return new DeleteCommand(command);
        case "find": return new FindCommand(command);
        default: return new InvalidCommand();
        }
    }


}

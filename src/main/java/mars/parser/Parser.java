package mars.parser;

import mars.command.Command;
import mars.command.addCommand;
import mars.command.deleteCommand;
import mars.command.listCommand;
import mars.command.markCommand;
import mars.command.unmarkCommand;
import mars.command.byeCommand;
import mars.command.findCommand;
import mars.command.echoCommand;

/**
 * Parser class deals with making sense of the user command
 * returns Command object
 */

public class Parser {

    /**
     * Parses the user input and returns a command to execute.
     *
     * @param userInput The raw input provided by the user.
     * @return The command object.
     */
    public static Command parse(String userInput){
        Integer index;

        assert userInput != null : "userInput cannot be null.";
        String[] parts = userInput.split(" ", 2);

        assert parts[0] != null : "Command must not be null.";
        String commandWord = parts[0];

        switch (commandWord) {
            case "todo":
                return new addCommand(commandWord,userInput);
            case "deadline":
                return new addCommand(commandWord,userInput);
            case "event":
                return new addCommand(commandWord,userInput);
            case "do":
                String[] secondPart = parts[1].split(" ", 2);
                commandWord = parts[0] + " " + secondPart[0];
                return new addCommand(commandWord,userInput);
            case "delete":
                index = Integer.valueOf(parts[1]);
                return new deleteCommand(index);
            case "list":
                return new listCommand();
            case "mark":
                index = Integer.valueOf(parts[1]);
                return new markCommand(index);
            case "unmark":
                index = Integer.valueOf(parts[1]);
                return new unmarkCommand(index);
            case "bye":
                return new byeCommand();
            case "find":
                String keyword = parts[1];
                return new findCommand(keyword);
            default:
                // revisit this functionality
                return new echoCommand(commandWord);
        }
    }

}

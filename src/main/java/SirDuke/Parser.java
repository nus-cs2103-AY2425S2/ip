package SirDuke;

import SirDuke.backend.ToDoList;
import SirDuke.backend.command.*;

import java.util.Scanner;

public class Parser {
    Scanner scanner = new Scanner(System.in);

    Parser(ToDoList toDoList) {
        this.toDoList = toDoList;
    }
    ToDoList toDoList;

    /**
     * Parses the user input and executes the corresponding command.
     */
    public Command parse(String input) {
        String regex = " ";
        String[] parsedInput = input.split(regex, 2);
        String commandType = parsedInput[0];
        try {
            switch (commandType) { //first word of the command
                case "bye":
                    return new ByeCommand();
                case "list":
                    return new ListCommand();
                case "mark":
                    return new MarkCommand(parsedInput[1]);
                case "unmark":
                    return new UnmarkCommand(parsedInput[1]);
                case "todo":
                    return new ToDoCommand(parsedInput[1]);
                case "deadline":
                    return new DeadlineCommand(parsedInput[1]);
                case "event":
                    return new EventCommand(parsedInput[1]);
                case "delete":
                    return new DeleteCommand(parsedInput[1]);
                case "find":
                    return new FindCommand(parsedInput[1]);
                default:
                    return new InvalidCommand();
            }
        } catch (IndexOutOfBoundsException e) {
            return new IncompleteCommand();
        }
    }
}

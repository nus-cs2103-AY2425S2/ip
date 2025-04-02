package orim.parser;

import orim.command.*;
import orim.myexception.IncorrectInputException;
import orim.myexception.MissingContentException;
import orim.storage.Storage;
import orim.task.Deadline;
import orim.task.Event;
import orim.task.Todo;
import orim.tasklist.TaskList;
import orim.ui.Ui;

import java.util.Arrays;
import java.util.List;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    public Parser() {

    }

    /**
     * Generates a specific command according to users input.
     *
     * @param command full input that receive from user.
     * @return subclass of Command according to users' input.
     * @throws MissingContentException when the information for adding Task is incomplete.
     */
    public static Command parse(String command) throws MissingContentException {
        Command temp = new Command() {
            @Override
            public String execute(TaskList tasks, Ui ui, Storage file) {
                return "";
            }
        };
        List<String> strings = Arrays.stream(command.split("/"))
                .map(x -> x.trim())
                .toList();
        switch (strings.get(0).split(" ")[0]) {
        case "todo":
            try {
                temp = new AddCommand(new Todo(strings.get(0).substring(5)));
            } catch (IndexOutOfBoundsException e) {
                throw new MissingContentException(" OOPS!!! The description of a todo cannot be empty.");
            }
            break;
        case "deadline":
            try {
                temp = new AddCommand(new Deadline(strings.get(0).substring(9)
                        , strings.get(1).substring(3)));
            } catch (IndexOutOfBoundsException e) {
                throw new MissingContentException(" OOPS!!! The description of a deadline cannot be empty.");
            }
            break;
        case "event":
            try {
                temp = new AddCommand(new Event(strings.get(0).substring(6),
                        strings.get(1).substring(5), strings.get(2).substring(3)));
            } catch (IndexOutOfBoundsException e) {
                throw new MissingContentException(" OOPS!!! The description of a event cannot be empty.");
            }
            break;
        case "delete":
            temp = new DeleteCommand(Integer.parseInt(strings.get(0).substring(7)) - 1);
            break;
        case "mark":
            temp = new UpdateCommand(Integer.parseInt(strings.get(0).substring(5)) - 1, true);
            break;
        case "unmark":
            temp = new UpdateCommand(Integer.parseInt(strings.get(0).substring(7)) - 1, false);
            break;
        case "bye":
            temp = new ExitCommand();
            break;
        case "list":
            temp = new DisplayCommand();
            break;
        case "find":
            String description = strings.get(0).substring(5);
            if (description.equals("unmarks")) {
                temp = new SearchCommand(description, true);
                break;
            }
            temp = new SearchCommand(description);
            break;
        default:
            throw new IncorrectInputException(" OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return temp;
    }

}

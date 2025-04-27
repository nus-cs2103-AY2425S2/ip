package Ninon;

import Ninon.Command.*;
import Ninon.Task.*;

import java.time.format.DateTimeParseException;
import java.util.Objects;

public class Parser {

    public static Command parse(String message) throws NinonException {
        Command command;
        assert !Objects.equals(message, "") : "message is empty";
        if (Objects.equals(message, "bye")) {
            command = new ExitCommand();
        } else if (Objects.equals(message, "list")) {
            command = new ListCommand();
        } else if (Objects.equals(message.split(" ")[0], "mark")) {
            assert message.split("").length != 1 : "index is missing";
            command = new MarkCommand(Integer.parseInt(message.split(" ")[1]),true);
        } else if (Objects.equals(message.split(" ")[0], "unmark")) {
            assert message.split("").length != 1 : "index is missing";
            command = new MarkCommand(Integer.parseInt(message.split(" ")[1]),false);
        } else if (Objects.equals(message.split(" ")[0], "find")) {
            assert message.split("").length != 1 : "description is missing";
            command = new FindCommand(message.split(" ")[1]);
        } else if (Objects.equals(message.split(" ")[0], "todo")) {
            if (message.split(" ").length == 1) {
                throw new NinonException("OOPS!!! The description of a todo cannot be empty.");
            }
            String description = message.split("/")[0].replace("todo ", "");
            command = new AddCommand(new Todo(description));
        } else if (Objects.equals(message.split(" ")[0], "deadline")) {
            if (message.split(" ").length == 1) {
                throw new NinonException("OOPS!!! The description of a deadline cannot be empty.");
            } else if (message.split("/").length == 1) {
                throw new NinonException("OOPS!!! The by of a deadline cannot be empty.");
            }
            String description = message.split("/")[0].replace("deadline ", "");
            String by = message.split("/")[1].replace("by ", "");
            try {
                command = new AddCommand(new Deadline(description, by));
            } catch (DateTimeParseException e) {
                throw new NinonException("date input format should be yyyy-mm-dd or date out of range");
            }
        } else if (Objects.equals(message.split(" ")[0], "event")) {
            if (message.split(" ").length == 1) {
                throw new NinonException("OOPS!!! The description of a event cannot be empty.");
            } else if (message.split("/").length == 1) {
                throw new NinonException("OOPS!!! The from of a event cannot be empty.");
            } else if (message.split("/").length == 2) {
                throw new NinonException("OOPS!!! The to of a event cannot be empty.");
            }
            String description = message.split("/")[0].replace("event ", "");
            String from = message.split("/")[1].replace("from ", "").replace(" ","");
            String to = message.split("/")[2].replace("to ", "");
            try {
                command = new AddCommand(new Event(description, from, to));
            } catch (DateTimeParseException e) {
                throw new NinonException("date input format should be yyyy-mm-dd or date out of range");
            }
        } else if (Objects.equals(message.split(" ")[0], "doafter")) {
            if (message.split(" ").length == 1) {
                throw new NinonException("OOPS!!! The description of a do cannot be empty.");
            } else if (message.split("/").length == 1) {
                throw new NinonException("OOPS!!! The description of an after cannot be empty.");
            }
            String description = message.split("/")[0].replace("doafter ", "");
            String by = message.split("/")[1].replace("by ", "");
            try {
                if (Objects.equals(by.split(" ")[0], "date")) {
                    command = new AddCommand(new DoAfter(description, by.split(" ")[1]));
                } else {
                    command = new AddCommand(new DoAfter(description, new Task(by)));
                }
            } catch (DateTimeParseException e) {
                throw new NinonException("date input format should be yyyy-mm-dd or date out of range");
            }
        } else if (Objects.equals(message.split(" ")[0], "delete")) {
            assert message.split("").length != 1 : "index is missing";
            command = new DeleteCommand(Integer.parseInt(message.split(" ")[1]));
        } else {
            throw new NinonException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return command;
    }
}
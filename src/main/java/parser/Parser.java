package parser;

import command.AddCommand;
import command.ArchiveCommand;
import command.ArchiveListCommand;
import command.Command;
import command.DeleteCommand;
import command.ExitCommand;
import command.FindCommand;
import command.HelpCommand;
import command.ListCommand;
import command.MarkCommand;
import command.MotivationCommand;
import command.StoreCommand;
import command.UnarchiveCommand;
import command.UnmarkCommand;
import exceptions.ElmachoException;
import task.Deadline;
import task.Event;
import task.ToDo;

/**
 * This class is responsible for interpreting user input and converting it into executable commands.
 *
 * <p>This class acts as an intermediary between the UI and the command execution logic,
 *    ensuring that inputs are correctly formatted and valid before being processed.</p>
 */
public class Parser {

    public Parser() {
    }

    /**
     * Returns a Command to be executed afer parsing user input.
     * @param instruction The raw user input as a String
     * @return A Command object that represents the action to be performed.
     * @throws ElmachoException if the user input is incomplete or missing required details.
     */
    public static Command parse(String instruction) throws ElmachoException {
        assert instruction != null : "Instruction must not be null.";
        String[] parts = instruction.split(" ", 2);

        assert parts[0] != null : "Command must not be null.";
        String command = parts[0].trim();


        switch (command) {
            case "delete" -> {
                int number = checkIndex(parts);
                return new DeleteCommand(number);
            }
            case "mark" -> {
                int number = checkIndex(parts);
                return new MarkCommand(number);
            }
            case "unmark" -> {
                int number = checkIndex(parts);
                return new UnmarkCommand(number);
            }
            case "archive" -> {
                return parseArchive(parts);
            }
            case "unarchive" -> {
                int number = checkIndex(parts);
                return new UnarchiveCommand(number);
            }
            case "find" -> {
                String keyword = checkArgs(parts);
                return new FindCommand(keyword);
            }
            case "bye" -> {
                return new ExitCommand();
            }
            case "list" -> {
                return new ListCommand();
            }
            case "motivate" -> {
                return new MotivationCommand();
            }
            case "help" -> {
                return new HelpCommand();
            }

            // Loading of old tasks from storage
            case "T", "D", "E" -> {
                return reloadTask(command, parts);
            }

            // Adding of new tasks
            case "todo", "deadline", "event" -> {
                String description = checkArgs(parts);
                return makeNewTask(command, description);
            }
        }

        return new Command();
    }

    public static Command makeNewTask(String command, String description) throws ElmachoException {
        switch (command) {
            case "todo" -> {
                String task = description.trim();
                return new AddCommand(new ToDo(task, false));
            }
            case "deadline" -> {
                String[] deadlineDetails = description.split("/by ", 2);  // Split on "/by " with a limit of 2

                if (deadlineDetails.length < 2) {
                    throw new ElmachoException("HELLOOO! When is the deadline??");
                }

                String task = deadlineDetails[0].trim();
                String dueDate = deadlineDetails[1].trim();  // This will be "2019-01-01 1800"

                return new AddCommand(new Deadline(task, dueDate, false));
            }
            case "event" -> {
                String[] eventDetails = description.split("/from ", 2);
                if (eventDetails.length < 2) {
                    throw new ElmachoException("HELLOOO! When does your event start??");
                }

                String task = eventDetails[0].trim();

                String[] timeDetails = eventDetails[1].split("/to ", 2);
                if (timeDetails.length < 2) {
                    throw new ElmachoException("HELLO. When does your event end?");
                }

                String from = timeDetails[0].trim();
                String to = timeDetails[1].trim();

                return new AddCommand(new Event(task, from, to, false));
            }
        }
        return new Command();
    }

    public static Command reloadTask(String command, String[] description) throws ElmachoException {
        String[] details = description[1].split("/");
        boolean isDone = details[0].equals("1");
        String storeDescription = details[1];

        switch (command) {
            case "T" -> {
                return new StoreCommand(new ToDo(storeDescription, isDone));
            }
            case "D" -> {
                String[] storeDetails = details[2].split("By");
                String dueDate = storeDetails[1].trim();
                return new StoreCommand(new Deadline(storeDescription, dueDate, isDone));
            }
            case "E" -> {
                String[] storeDetails = details[2].split("From");
                String[] storeDetails2 = storeDetails[1].split("To");
                String fromDate = storeDetails2[0].trim();
                String toDate = storeDetails2[1].trim();
                return new StoreCommand(new Event(storeDescription, fromDate, toDate, isDone));
            }
        }
        return new Command();
    }

    public static String checkArgs(String[] parts) throws ElmachoException {
        if (parts.length <= 1 || parts[1].trim().isEmpty()) {
            throw new ElmachoException("Description not specified. Complete your statement.");
        }
        return parts[1].trim();
    }

    public static int checkIndex(String[] parts) throws ElmachoException {
        try {
            if (parts.length <= 1 || parts[1].trim().isEmpty()) {
                throw new ElmachoException("Task number not specified. Which task do you mean?");
            }
            int number = Integer.parseInt(parts[1].trim());
            if (number <= 0) {
                throw new ElmachoException("Invalid task number given.");
            }
            return number;
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid task number.");
        }
        return -1;
    }

    public static Command parseArchive(String[] parts) throws ElmachoException {
        try {
            if (parts.length <= 1 || parts[1].trim().isEmpty()) {
                throw new ElmachoException("Task to archive not specified. Complete your statement.");
            }
            if (parts[1].toLowerCase().trim().equals("list")) {
                return new ArchiveListCommand();
            }
            int number = Integer.parseInt(parts[1].trim());
            if (number <= 0) {
                throw new ElmachoException("Invalid task number given.");
            }
            return new ArchiveCommand(number);
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid task number.");
        }
        return new Command();
    }
}

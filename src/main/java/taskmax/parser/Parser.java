package taskmax.parser;

import taskmax.command.AddCommand;
import taskmax.command.Command;
import taskmax.command.DeleteCommand;
import taskmax.command.ExitCommand;
import taskmax.command.ListCommand;
import taskmax.command.MarkCommand;
import taskmax.command.UnmarkCommand;

import taskmax.exception.TaskmaxException;

import taskmax.task.Deadline;
import taskmax.task.Event;
import taskmax.task.ToDo;

import taskmax.ui.Ui;

/**
 * Handles parsing of user commands into executable command objects.
 */
public class Parser {

    /**
     * Parses the user input into a corresponding Command object.
     *
     * @param input The raw user input string.
     * @return The corresponding Command object.
     * @throws TaskmaxException If the input is invalid or incomplete.
     */
    public static Command parse(String input) throws TaskmaxException {
        String[] words = input.split(" ", 2);
        String commandWord = words[0];

        switch (commandWord) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                validateCommand(words, "Please enter a number of an existing task so I can find it in the list!");
                return new MarkCommand(Integer.parseInt(words[1]));
            case "unmark":
                validateCommand(words, "Please enter a number of an existing task so I can find it in the list!");
                return new UnmarkCommand(Integer.parseInt(words[1]));
            case "delete":
                validateCommand(words, "Please enter a number of an existing task so I can find it in the list!");
                return new DeleteCommand(Integer.parseInt(words[1]));
            case "todo":
                validateCommand(words, "You have to include a task to add!\n"
                        + "e.g. todo Assignment1");
                return new AddCommand(new ToDo(words[1]));
            case "deadline":
                validateCommand(words, "Oops! You have to include a \"/by deadline\" after your task\n"
                        + "e.g. deadline Assignment2 /by 2021-10-15 1800\n"
                        + "Please try again!");
                String[] deadlineParts = words[1].split(" /by ", 2);
                validateParts(deadlineParts, 2, "Oops! You have to include a \"/by deadline\" after your task\n"
                        + "e.g. deadline Assignment2 /by 2021-10-15 1800\n"
                        + "Please try again!");
                return new AddCommand(new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim()));
            case "event":
                validateCommand(words, "Oops! You have to include a \"/from start /to end\" after your task\n"
                        + "e.g. event Concert /from 2021-10-15 1800 /to 2021-10-15 2200\n"
                        + "Please try again!");
                String[] eventParts = words[1].split(" /from | /to ", 3);
                validateParts(eventParts, 3, "Oops! You have to include a \"/from start /to end\" after your task\n"
                        + "e.g. event Concert /from 2021-10-15 1800 /to 2021-10-15 2200\n"
                        + "Please try again!");
                return new AddCommand(new Event(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim()));
            default:
                throw new TaskmaxException(getHelpMessage());
        }
    }

    /**
     * Validates that the user input contains the expected number of parts.
     *
     * @param words The split input command.
     * @param errorMessage The error message to display if validation fails.
     * @throws TaskmaxException If the command is incomplete.
     */
    private static void validateCommand(String[] words, String errorMessage) throws TaskmaxException {
        if (words.length < 2) {
            throw new TaskmaxException(errorMessage);
        }
    }

    /**
     * Validates that the provided parts array has the expected number of elements.
     *
     * @param parts The split components of a command argument.
     * @param expectedLength The expected number of parts.
     * @param errorMessage The error message to display if validation fails.
     * @throws TaskmaxException If the provided parts array is incomplete.
     */
    private static void validateParts(String[] parts, int expectedLength, String errorMessage) throws TaskmaxException {
        if (parts.length < expectedLength) {
            throw new TaskmaxException(errorMessage);
        }
    }

    /**
     * Returns the help message when an invalid command is entered.
     *
     * @return The help message string.
     */
    private static String getHelpMessage() {
        return Ui.LINE + "Hey there! There are 7 things I can help you with! \n"
                + "\n1. List: Enter \"list\" and I will list out all the tasks you have given me!\n"
                + "2. Todo: Enter \"todo theTaskName\" to add a task you plan to do!\n"
                + "3. Deadlines: Enter \"deadline theTaskName /by yyyy-mm-dd 24hrTime\" to add a task with a specific deadline!\n"
                + "4. Events: Enter \"event theTaskName /from yyyy-mm-dd 24hrTime /to yyyy-mm-dd 24hrTime\" to add an event!\n"
                + "5. Delete: Enter \"delete theTaskListNumber\" to delete a task from the list!\n"
                + "6. Mark as done: Enter \"mark TaskListNumber\" to mark the task as complete in the list!\n"
                + "7. Mark as undone: Enter \"unmark TaskListNumber\" to mark the task as incomplete in the list!\n"
                + "\nIf you need a refresher, just enter any word!"
                + "\nIf you are satisfied with your service, enter \"bye\" to save your task list and exit!\n"
                + "\nDo remember that my input receptors are sensitive so please be careful with your spelling"
                + "\nand capital letters for commands!\n"
                + "\nThat is all and happy scheduling! ~Taskmax :D\n" + Ui.LINE;
    }
}

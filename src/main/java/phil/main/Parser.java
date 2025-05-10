package phil.main;

import java.util.Arrays;
import java.util.List;

import phil.command.ByeCommand;
import phil.command.DeadlineCommand;
import phil.command.DeleteCommand;
import phil.command.DeleteNoteCommand;
import phil.command.EventCommand;
import phil.command.FindCommand;
import phil.command.ListCommand;
import phil.command.ListNoteCommand;
import phil.command.MarkCommand;
import phil.command.NewNoteCommand;
import phil.command.TodoCommand;
import phil.command.UnmarkCommand;
import phil.exception.InvalidCommandException;
import phil.exception.PhilException;
import phil.model.NoteList;
import phil.model.TaskList;
import phil.storage.Storage;


/**
 * Represents the Parser which contains logic to process input strings passed in.
 */
public class Parser {
    private TaskList taskList;
    private NoteList noteList;
    private Storage storage;

    /**
     * Sets up the Parser object.
     *
     * @param taskList list of tasks.
     * @param noteList list of notes.
     * @param storage storage.
     */
    public Parser(TaskList taskList, NoteList noteList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
        this.noteList = noteList;
    }

    /**
     * Checks whether the given string input is valid
     * for Mark, Unmark, Delete commands, requiring only one argument.
     *
     * @param inputArgs String list from input String, delimited by space.
     * @param numTasks number of tasks in task list.
     * @return boolean representing if input is invalid.
     */
    private boolean isInputInvalidForMarkUnmarkDelete(List<String> inputArgs, int numTasks) {
        if (inputArgs.size() != 2) {
            return true;
        } else if (!inputArgs.get(1).matches("\\d+")) {
            return true;
        } else {
            return Integer.parseInt(inputArgs.get(1)) > numTasks;
        }
    }

    /**
     * Checks whether the given string input is valid
     * for Find, Todo, Event, Deadline commands.
     *
     * @param inputArgs String list from input String, delimited by space.
     * @param minimumNumberOfArgs minimum number of arguments required.
     * @param requiredArgs varArgs for required args to check.
     * @return boolean representing if input is invalid.
     */
    private boolean isInputInvalidForFindAndTaskCreation(List<String> inputArgs,
            int minimumNumberOfArgs, String... requiredArgs) {
        boolean hasMinNumberOfArgs = inputArgs.size() >= minimumNumberOfArgs;
        boolean hasRequiredArgs = true;
        for (String arg : requiredArgs) {
            hasRequiredArgs = hasRequiredArgs && inputArgs.contains(arg);
        }
        return !hasMinNumberOfArgs || !hasRequiredArgs;
    }

    private boolean matchesFirstArg(List<String> inputArgs, String argToMatch) {
        return inputArgs.get(0).equals(argToMatch);
    }

    /**
     * Takes in a String input, and returns the corresponding output based on the command.
     *
     * @param input input by user representing the command.
     * @return output to print if command is valid.
     * @throws PhilException to be thrown if command or arguments are not valid.
     */
    public String generateStringOutputFromInput(String input) throws PhilException {
        // Assert taskList and storage are initialised, before potential accesses.
        assert this.taskList != null && this.storage != null;

        List<String> inputArgs = Arrays.asList(input.split(" "));
        int numTasks = this.taskList.getNumberOfTasks();
        int numNotes = this.noteList.getNumberOfNotes();

        if (input.equals("bye")) {
            return new ByeCommand(this.taskList, this.noteList, this.storage).execute();
        } else if (matchesFirstArg(inputArgs, "list")) {
            return new ListCommand(this.taskList).execute();
        } else if (matchesFirstArg(inputArgs, "delete")) {
            return new DeleteCommand(inputArgs, this.taskList,
                    isInputInvalidForMarkUnmarkDelete(inputArgs, numTasks), numTasks)
                    .execute();
        } else if (matchesFirstArg(inputArgs, "mark")) {
            return new MarkCommand(inputArgs, this.taskList,
                    isInputInvalidForMarkUnmarkDelete(inputArgs, numTasks), numTasks)
                    .execute();
        } else if (matchesFirstArg(inputArgs, "unmark")) {
            return new UnmarkCommand(inputArgs, this.taskList,
                    isInputInvalidForMarkUnmarkDelete(inputArgs, numTasks), numTasks)
                    .execute();
        } else if (matchesFirstArg(inputArgs, "todo")) {
            return new TodoCommand(inputArgs, this.taskList,
                    isInputInvalidForFindAndTaskCreation(inputArgs, 2))
                    .execute();
        } else if (matchesFirstArg(inputArgs, "deadline")) {
            return new DeadlineCommand(inputArgs, this.taskList,
                    isInputInvalidForFindAndTaskCreation(inputArgs, 4, "/by"))
                    .execute();
        } else if (matchesFirstArg(inputArgs, "event")) {
            return new EventCommand(inputArgs, this.taskList,
                    isInputInvalidForFindAndTaskCreation(inputArgs, 6, "/from", "/to"))
                    .execute();
        } else if (matchesFirstArg(inputArgs, "find")) {
            return new FindCommand(inputArgs, this.taskList,
                    isInputInvalidForFindAndTaskCreation(inputArgs, 2))
                    .execute();
        } else if (matchesFirstArg(inputArgs, "new-note")) {
            return new NewNoteCommand(inputArgs, this.noteList,
                    isInputInvalidForFindAndTaskCreation(inputArgs, 2))
                    .execute();
        } else if (matchesFirstArg(inputArgs, "delete-note")) {
            return new DeleteNoteCommand(inputArgs, this.noteList,
                    isInputInvalidForMarkUnmarkDelete(inputArgs, numNotes), numNotes)
                    .execute();
        } else if (input.equals("list-note")) {
            return new ListNoteCommand(this.noteList).execute();
        } else {
            throw new InvalidCommandException(input);
        }
    }
}

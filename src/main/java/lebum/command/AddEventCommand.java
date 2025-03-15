package lebum.command;

import lebum.exception.DukeException;
import lebum.main.Storage;
import lebum.main.TaskList;
import lebum.main.Ui;
import lebum.task.Event;

/**
 * The command to add event
 */
public class AddEventCommand extends Command {
    private static final int MAX_DESCRIPTION_LENGTH = 100;
    private String title;
    private String[] parts;
    private String response = "";

    /**
     * Constructor for adding event.
     * @param title of event command
     */

    public AddEventCommand(String title) {
        this.title = title.trim();
        parts = this.title.split(" ", 2);
    }

    @Override
    public String getResponse() {
        return this.response;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        try {
            validateInput();
            String[] evParts = parts[1].split(" /from ", 2);
            String description = evParts[0].trim();

            String[] timeParts = evParts[1].split(" /to ", 2);
            String startTime = timeParts[0].trim();
            String endTime = timeParts[1].trim();

            validateFields(description, startTime, endTime);

            Event event = new Event(description, startTime, endTime);
            this.response = tasks.addTask(event);
            storage.saveToFile(tasks);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! Invalid event format. Use: event <description>"
                    + " /from <start-time> /to <end-time>");
        }
    }

    private void validateInput() throws DukeException {
        // Check for empty description
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty,"
                    + " please fill in the description!");
        }

        // Check for /from tag
        if (!parts[1].contains(" /from ")) {
            throw new DukeException("OOPS!!! Please provide event time using /from and /to.");
        }

        String[] evParts = parts[1].split(" /from ", 2);

        // Check if there's content before /from
        if (evParts[0].trim().isEmpty()) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty!");
        }

        // Check for /to tag
        if (!evParts[1].contains(" /to ")) {
            throw new DukeException("OOPS!!! Please provide event end time using /to.");
        }

        // Check if there's content after /to
        String[] timeParts = evParts[1].split(" /to ", 2);
        if (timeParts[1].trim().isEmpty()) {
            throw new DukeException("OOPS!!! The end time cannot be empty!");
        }
    }

    private void validateFields(String description, String startTime, String endTime) throws DukeException {
        // Validate description length
        if (description.length() > MAX_DESCRIPTION_LENGTH) {
            throw new DukeException("OOPS!!! The description is too long (max 100 characters).");
        }

        // Validate for special characters
        if (containsInvalidSpecialCharacters(description)
                || containsInvalidSpecialCharacters(startTime)
                || containsInvalidSpecialCharacters(endTime)) {
            throw new DukeException("OOPS!!! Invalid characters detected. Please avoid <>|\\^`");
        }

        // Make sure times aren't empty
        if (startTime.isEmpty()) {
            throw new DukeException("OOPS!!! Start time cannot be empty.");
        }

        if (endTime.isEmpty()) {
            throw new DukeException("OOPS!!! End time cannot be empty.");
        }
    }

    private boolean containsInvalidSpecialCharacters(String str) {
        String invalidChars = "<>|\\^`";
        return str.chars().anyMatch(ch -> invalidChars.indexOf(ch) >= 0);
    }
}

package lebum.command;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import lebum.exception.DukeException;
import lebum.main.Storage;
import lebum.main.TaskList;
import lebum.main.Ui;
import lebum.task.Deadline;

/**
 * Class that handles adding deadlines.
 */
public class AddDeadlineCommand extends Command {

    private static final int MAX_DESCRIPTION_LENGTH = 100;
    private String title;
    private String[] parts;
    private String response = "";

    /**
     * Constructor for adding deadline command.
     * @param title of the task
     */
    public AddDeadlineCommand(String title) {
        this.title = title.trim();
        parts = this.title.split(" ", 2);
    }

    @Override
    public String getResponse() {
        return this.response;
    }

    /**
     * Execute adding deadline tasks.
     * @param tasks to add
     * @param storage to load the file
     * @param ui to handle ui
     * @throws DukeException
     */
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        try {
            validateInput();
            String[] dlParts = parts[1].split("/by", 2);
            String description = dlParts[0].trim();
            String dateTime = dlParts[1].trim();

            validateDescription(description);
            validateDateTime(dateTime);

            Deadline deadline = new Deadline(description, dateTime);
            this.response = tasks.addTask(deadline);
            storage.saveToFile(tasks);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(INVALID_FORMAT_ERROR);
        } catch (IllegalArgumentException e) {
            throw new DukeException(INVALID_DATE_ERROR);
        }
    }

    private void validateInput() throws DukeException {
        // Check for empty description
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new DukeException(EMPTY_DESC_ERROR);
        }

        // Check for multiple /by tags
        if (countOccurrences(parts[1], "/by") > 1) {
            throw new DukeException(MULTIPLE_BY_ERROR);
        }

        // Check for /by tag
        String[] dlParts = parts[1].split("/by", 2);
        if (dlParts.length < 2) {
            throw new DukeException(MISSING_DEADLINE_ERROR);
        }

        // Check for empty time after /by
        if (dlParts[1].trim().isEmpty()) {
            throw new DukeException(EMPTY_TIME_ERROR);
        }
    }

    private void validateDescription(String description) throws DukeException {
        if (description.length() > MAX_DESCRIPTION_LENGTH) {
            throw new DukeException(MAX_LENGTH_ERROR);
        }

        if (containsInvalidSpecialCharacters(description)) {
            throw new DukeException(SPECIAL_CHAR_ERROR);
        }
    }

    private void validateDateTime(String dateTime) throws DukeException {
        // Accept any non-empty string as a valid deadline
        if (dateTime.trim().isEmpty()) {
            throw new DukeException(EMPTY_TIME_ERROR);
        }

        // Optional: try to parse as datetime if it looks like one
        if (dateTime.matches("\\d{4}-\\d{2}-\\d{2}.*")) {
            try {
                LocalDateTime parsedDateTime;
                try {
                    // Try parsing with time
                    parsedDateTime = LocalDateTime.parse(dateTime,
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                } catch (DateTimeParseException e) {
                    // Try parsing date only
                    parsedDateTime = LocalDateTime.parse(dateTime + " 23:59",
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                }

                // Check if date is in the past
                if (parsedDateTime.isBefore(LocalDateTime.now())) {
                    throw new DukeException(PAST_DATE_ERROR);
                }
            } catch (DateTimeParseException e) {
                throw new DukeException(e.getMessage());
            }
        }

        // Additional validation
        if (dateTime.length() > MAX_DESCRIPTION_LENGTH) {
            throw new DukeException(MAX_LENGTH_ERROR);
        }

        if (containsInvalidSpecialCharacters(dateTime)) {
            throw new DukeException(SPECIAL_CHAR_ERROR);
        }
    }

    private int countOccurrences(String str, String substr) {
        return str.split(substr, -1).length - 1;
    }

    private boolean containsInvalidSpecialCharacters(String str) {
        String invalidChars = "<>|\\^`";
        return str.chars().anyMatch(ch -> invalidChars.indexOf(ch) >= 0);
    }
}

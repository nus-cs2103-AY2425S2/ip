package dudu.controller;

import dudu.model.Deadline;
import dudu.model.Task;
import dudu.model.TaskList;

import dudu.utils.DateTimeParser;
import dudu.utils.DuduException;
import dudu.utils.Ui;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class DeadlineCommand implements Command {
    private final String description;

    /**
     * Creates DeadlineCommand object.
     *
     * @param description Arguments of command.
     */
    public DeadlineCommand(String description) {
        this.description = description;
    }

    private static void validateDescription(String input) throws DuduException {
        if (input.trim().isEmpty()) {
            throw new DuduException("The description of a task cannot be empty.");
        }
    }

    private void validateDeadlinePartsLength(int length) throws DuduException {
        if (length < 2) {
            throw new DuduException("Please enter a deadline using the /by keyword.");
        }
    }

    private void validateDeadlineBy(String by) throws DuduException {
        if (by.isEmpty()) {
            throw new DuduException("Please enter a deadline using the /by keyword.");
        }
    }

    /**
     * Adds new Deadline Task to current list and overwrites save file.
     *
     * @param tasks Current Tasks in the list.
     * @param cachedTasks Save file.
     * @return String containing Deadline Task that was added.
     * @throws DuduException If no deadline is given with the /by keyword.
     */
    @Override
    public String execute(TaskList tasks, File cachedTasks) throws DuduException {
        try {
            String[] deadlineParts = description.split("/by ");

            int deadlinePartsLength = deadlineParts.length;
            validateDeadlinePartsLength(deadlinePartsLength);
            String deadlineTitle = deadlineParts[0].trim();
            String deadlineBy = deadlineParts[1].trim();
            String outputString;

            validateDescription(deadlineTitle);
            validateDeadlineBy(deadlineBy);

            LocalDateTime parsedDateTime = DateTimeParser.parseDateTime(deadlineBy);

            Task deadline = new Deadline(deadlineTitle, parsedDateTime);
            outputString = tasks.addEntry(deadline);
            FileOperation.overwriteFile(cachedTasks, tasks);
            return outputString;
        } catch (DuduException e) {
            return e.getMessage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns false as it is not an exit command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

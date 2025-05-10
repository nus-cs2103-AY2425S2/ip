package dudu.controller;

import dudu.model.Event;
import dudu.model.Task;
import dudu.model.TaskList;

import dudu.utils.DateTimeParser;
import dudu.utils.DuduException;
import dudu.utils.Ui;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class EventCommand implements Command {
    private final String description;

    /**
     * Creates EventCommand object.
     *
     * @param description Arguments of command.
     */
    public EventCommand(String description) {
        this.description = description;
    }

    private static void validateTitle(String input) throws DuduException {
        if (input.trim().isEmpty()) {
            throw new DuduException("The title of a task cannot be empty.");
        }
    }

    private void validateEventPartsLength(int length) throws DuduException {
        if (length < 3) {
            throw new DuduException("Please enter a duration for this event " +
                    "using the /from and /to keywords.");
        }
    }

    private void validateEventFromTo(String from, String to) throws DuduException {
        if (from.isEmpty() || to.isEmpty()) {
            throw new DuduException("Please enter a duration for this event using the /from and /to keywords.");
        }
    }

    /**
     * Adds new Event Task to current list and overwrites save file.
     *
     * @param tasks Current Tasks in the list.
     * @param cachedTasks Save file.
     * @return String containing Event task that was added.
     * @throws DuduException If no duration is given with the /from and /to keywords.
     */
    @Override
    public String execute(TaskList tasks, File cachedTasks) throws DuduException {
        try {
            String[] eventParts = description.split("/from | /to", 3);
            int eventPartsLength = eventParts.length;
            validateEventPartsLength(eventPartsLength);

            String eventTitle = eventParts[0];
            validateTitle(eventTitle);

            String eventFrom = eventParts[1].trim();
            String eventTo = eventParts[2].trim();
            validateEventFromTo(eventFrom, eventTo);

            String outputString;

            LocalDateTime parsedFrom = DateTimeParser.parseDateTime(eventFrom);
            LocalDateTime parsedTo = DateTimeParser.parseDateTime(eventTo);

            Task event = new Event(eventTitle, parsedFrom, parsedTo);
            outputString = tasks.addEntry(event);
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

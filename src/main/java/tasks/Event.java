package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exceptions.TaskException;
import tasks.priority.TaskPriority;

/**
 * This class represents a task with a more specific deadline.
 * It extends the {@link Task} class and includes a specific start and end date/time.
 *
 * @author Yashvan
 */
public class Event extends Task {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("d MMMM yyyy, h:mma");
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Private constructor for the Deadline class.
     *
     * @param description This is a description of what the deadline task should be.
     * @param startTime This is when the task starts.
     * @param endTime This is when the task must be completed by.
     * @param taskPriority This is the priority of the task.
     */
    private Event(LocalDateTime startTime, LocalDateTime endTime, String description, TaskPriority taskPriority) {
        super(description, taskPriority);

        assert description != null && !description.isBlank() : "Description should not be null or blank";
        assert startTime != null : "Start time should not be null";
        assert endTime != null : "End time should not be null";
        assert taskPriority != null : "TaskPriority should not be null";

        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Creates an instance of Event.
     * A factory method to parse input and create an Event object.
     *
     * @param input The input string for the event task.
     * @return A new Event object.
     * @throws TaskException If the input format is invalid.
     */
    public static Event create(String input) throws TaskException {
        assert input != null && !input.isBlank() : "Input should not be null or empty";

        String[] parts = input.split(" /from ");

        if (parts.length < 2 || !parts[1].contains(" /to ")) {
            throw new TaskException("PLEASE BRUH! Use: event <description> /from <start> /to "
                    + "<end> /priority <LOW|MEDIUM|HIGH|URGENT> ._.");
        }

        String[] timeParts = parts[1].split(" /to ");
        if (timeParts.length < 2) {
            throw new TaskException("One would think that a start and end time come as a pair wouldn't you?");
        }

        String eventTask = parts[0].substring(5).trim();
        String startTimeString = timeParts[0].trim();
        String[] endTimePriority = getTimePriority(timeParts, eventTask, startTimeString);
        String endTimeString = endTimePriority[0].trim();

        TaskPriority taskPriority;
        try {
            taskPriority = (endTimePriority.length > 1)
                    ? TaskPriority.valueOf(endTimePriority[1].toUpperCase())
                    : TaskPriority.LOW;
        } catch (IllegalArgumentException e) {
            throw new TaskException("Get your priorities in order! Use: LOW, MEDIUM, HIGH, or URGENT!");
        }

        // Parse date-time strings
        LocalDateTime startTime;
        LocalDateTime endTime;

        try {
            startTime = LocalDateTime.parse(startTimeString, INPUT_FORMATTER);
            endTime = LocalDateTime.parse(endTimeString, INPUT_FORMATTER);
            validateEventTimes(startTime, endTime);
        } catch (DateTimeParseException e) {
            throw new TaskException("Invalid date-time format bro! Use: d/M/yyyy HHmm.");
        }

        return new Event(startTime, endTime, eventTask, taskPriority);
    }

    private static String[] getTimePriority(String[] timeParts, String eventTask, String startTimeString)
            throws TaskException {
        assert timeParts != null && timeParts.length > 1 : "Time parts should contain start and end times";

        String endTimeAndPriority = timeParts[1];

        if (eventTask.isEmpty()) {
            throw new TaskException("Watchu trying to describe bro? An abstract concept? Write a description!");
        }
        if (startTimeString.isEmpty() || endTimeAndPriority.isEmpty()) {
            throw new TaskException("Sick event man! Just kidding, start and end times can't be empty.");
        }

        // Parse end time and priority
        return endTimeAndPriority.split(" /priority ");
    }

    /**
     * Checks if the end time is before the start time of an event.
     *
     * @param startTime The time the event starts.
     * @param endTime The time the event ends.
     * @throws TaskException If the time the event ends is before the time it starts.
     */
    private static void validateEventTimes(LocalDateTime startTime, LocalDateTime endTime) throws TaskException {
        assert startTime != null && endTime != null : "Start and end times should not be null";

        if (endTime.isBefore(startTime)) {
            throw new TaskException("Are you a time traveler cos an end time cannot be before a start time!");
        }
    }

    /**
     * Returns string representation of the object.
     *
     * @return Shows whether the event task has or has not been completed.
     */
    @Override
    public String toString() {
        assert startTime != null : "Start time should not be null before formatting";
        assert endTime != null : "End time should not be null before formatting";

        return "[E]" + super.toString()
                + " (from: "
                + startTime.format(OUTPUT_FORMATTER)
                + " to: "
                + endTime.format(OUTPUT_FORMATTER)
                + ")";
    }
}


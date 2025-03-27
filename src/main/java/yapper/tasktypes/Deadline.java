package yapper.tasktypes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import yapper.exceptions.InvalidTaskType;
import yapper.exceptions.MissingTaskArgs;

/**
 * Represents a deadline task with a specific date and time.
 */
public class Deadline extends Task {

    private final LocalDate date;
    private final LocalTime time;

    /**
     * Constructs a {@code Deadline} task from the given user request. The request must be formatted as: {@code
     * deadline task_name /by yyyy/MM/dd HHmm}.
     *
     * @param request The user input containing the task name and deadline details.
     * @throws MissingTaskArgs If the required arguments are missing or incorrectly formatted.
     */
    public Deadline(String request) {
        this.request = request;
        String[] splitString = request.split(" /by ", 2);

        if (splitString.length < 2) {
            throw new MissingTaskArgs("\tHey! I don't quite understand you. "
                    + "Remember for Deadline "
                    + "Give it in this format: command name /by date");
        }

        // Extract task name
        this.taskName = splitString[0].replace("deadline", "").trim();

        // Split date and time separately
        String[] dateTimeParts = splitString[1].trim().split(" ", 2);

        if (dateTimeParts.length < 2) {
            throw new MissingTaskArgs("\tHey! I don't quite understand you. "
                    + "Remember for Deadline Give it in this format: "
                    + "command name /by YYYY/MM/DD HHmm");
        }

        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");

            this.date = LocalDate.parse(dateTimeParts[0].trim(), dateFormatter);
            this.time = LocalTime.parse(dateTimeParts[1].trim(), timeFormatter);
        } catch (DateTimeParseException e) {
            throw new InvalidTaskType("\tHey! I don't quite understand you. "
                    + "Remember for Deadline Give it in this format: "
                    + "command name /by YYYY/MM/DD HHmm");
        }
    }

    /**
     * Returns a string representation of the deadline task, including the date and time.
     *
     * @return A formatted string representing the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date + " " + this.time + ")";
    }
}

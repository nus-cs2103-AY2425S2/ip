package steve.tasks;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with a start time and an end time.
 * This task includes a description and two dates: a start time ("from") and an end time ("to").
 * The task ensures that the description is not empty and the date/time format is valid.
 */
public class Event extends Task {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private String description;
    private LocalDateTime from;
    private LocalDateTime to;
    private boolean isValid = false;

    /**
     * Constructs an Event task with the specified description.
     *
     * @param description The description of the event task, including the dates.
     */
    public Event(String description) {
        super(validateDescription(description));
        initializeEvent(validateDescription(description));
    }

    /**
     * Initializes event objects
     * */
    private void initializeEvent(String description) {
        try {
            messageFormatter(description);
            this.isValid = true;
        } catch (IllegalArgumentException e) {
            this.description = e.getMessage();
            super.decreaseTaskCount();
        }
    }

    /**
     * Formats user description, from and to keywords
     * */
    private void messageFormatter(String description) {
        String[] parts = extractDescriptionParts(description);
        this.description = parts[0].trim();
        this.from = parseDateTime(parts[1].replace("from", "").trim());
        this.to = parseDateTime(parts[2].replace("to", "").trim());
        if (from.isAfter(to)) {
            throw new IllegalArgumentException(invalidDateOrderMessage());
        }
    }

    /**
     * Parses a date-time string into a LocalDateTime object
     * */
    private static LocalDateTime parseDateTime(String dateTimeString) {
        try {
            if (!isValidDate(dateTimeString)) {
                throw new IllegalArgumentException(invalidDateFormatMessage());
            }
            return LocalDateTime.parse(dateTimeString, FORMATTER);

        } catch (DateTimeParseException | IllegalArgumentException e) {
            throw new IllegalArgumentException(invalidFormatMessage());
        }
    }

    /**
     * Extracts and validates the event description parts
     * */
    private static String[] extractDescriptionParts(String description) {
        String[] parts = description.split("/");
        if (parts.length != 3 || parts[0].trim().isEmpty()) {
            throw new IllegalArgumentException(invalidFormatMessage());
        }
        return parts;
    }

    /**
     * Returns a string message indicating empty description
     * */
    public static String emptyInputMessage() {
        return "Description cannot be empty. Usage: event <description /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm>";
    }

    /**
     * Returns a string message indicating incorrect input format
     * */
    public static String invalidFormatMessage() {
        return "Invalid format. Usage: "
                + "event <description /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm>";
    }

    /**
     * Returns a string message indicating incorrect date format
     * */
    public static String invalidDateFormatMessage() {
        return "Invalid Date format. Please ensure: \n"
                + "1. Date is within month range\n"
                + "2. Month is within year range\n";
    }

    /**
     * Returns a string message indicating incorrect date order
     * */
    public static String invalidDateOrderMessage() {
        return "Invalid Date Order. Please ensure: \n"
                + "Start date time is before End Date time";
    }

    /**
     * Returns string based on validity of description (empty or not)
     * */
    private static String validateDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException(emptyInputMessage());
        }
        return description;
    }

    private static boolean checkEmptyOrValid(String description) {
        return description.startsWith("Invalid")
                || description.startsWith("Description cannot be empty");
    }

    /**
     * Displays a message about the task, including its description and the date range.
     * If the task description is valid, it will print the event task details,
     * including the start and end times. If the description is invalid,
     * an error message is displayed instead.
     */
    public void message() {
        String result = checkEmptyOrValid(this.description)
                ? this.description
                : addTaskMessage();
        System.out.println(result);
    }

    /**
     * Returns a string message indicating task is added
     */
    public String addTaskMessage() {
        return "_________________________\n"
                + "     Got it. I've added this task:\n"
                + "       [E][ ] " + description + " (From: " + formatDateTime(from)
                + " to: " + formatDateTime(to) + ") \n"
                + "     Now you have " + TaskManager.getTaskSize() + " tasks in the list.\n"
                + "_________________________\n";
    }

    /**
     * Returns a string message about the task, including its description and the date range.
     * If the task description is valid, it will print the event task details,
     * including the start and end times. If the description is invalid,
     * an error message is displayed instead.
     */
    public String messageString() {
        return checkEmptyOrValid(this.description)
                ? this.description
                : addTaskMessage();
    }

    /**
     * Returns a string representation of the event task, including its description
     * and date range.
     *
     * @return A string describing the event task, including the start and end times.
     */
    @Override
    public String toString() {
        String result = checkEmptyOrValid(this.description)
                ? this.description
                : addTaskMessage();
        return result;
    }

    /**
     * Returns the description of the event task, including the start and end times.
     *
     * @return A string describing the event with its date range.
     */
    @Override
    public String getDescription() {
        return this.description + " (From: " + formatDateTime(from)
                + " to: " + formatDateTime(to) + ") ";
    }

    /**
     * Returns the code representing the event task type.
     *
     * @return A string representing the event task type ("E").
     */
    @Override
    public String code() {
        return "E";
    }

    /**
     * Returns whether the event task is valid based on its description and dates.
     *
     * @return True if the task is valid, false otherwise.
     */
    @Override
    public boolean isValid() {
        return isValid;
    }

    /**
     * Returns the type of the task.
     *
     * @return A string representing the task type ("Event: ").
     */
    @Override
    public String type() {
        return "Event: ";
    }

    /**
     * Returns the original description, including the dates in the format used to create the task.
     *
     * @return A string containing the original description, including the "/from" and "/to" date/times.
     */
    @Override
    public String getOriginalDescription() {
        return this.description + " /from " + (from != null
                ? from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                : "") + " /to " + (to != null
                ? to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                : "");
    }

    /**
     * Formats the given {@link LocalDateTime} into a more readable string format.
     *
     * @param dateTime The {@link LocalDateTime} to format.
     * @return A string representing the formatted date/time.
     */
    private String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
        return dateTime.format(formatter);
    }

    private static boolean isValidDate(String dateStr) {
        try {
            String[] parts = dateStr.split(" ")[0].split("-");
            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int day = Integer.parseInt(parts[2]);

            // Check if the day is valid for the given month
            return day <= YearMonth.of(year, month).lengthOfMonth();
        } catch (Exception e) {
            return false;
        }
    }
}

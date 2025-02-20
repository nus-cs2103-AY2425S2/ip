package tasks.parser;

import tasks.Deadline;
import tasks.Event;

/**
 * This class provides methods to parse and format task-related date and time strings.
 * It is primarily used to convert user inputs or saved task data into standardised
 * formats suitable for creating instances of the {@link Deadline Deadline}
 * and {@link Event Event} classes.
 *
 * @author Yashvan
 */
public class TaskDateTimeParser {

    /**
     * Returns the parsed and formatted string to create an instance of the Deadline class.
     *
     * @param line The line used to convert to the command string.
     * @return Parsed input for Deadline class
     */
    public static String deadlineParser(String line) {
        String description = extractDescription(line);
        String priority = extractPriority(line);

        int startOfDeadline = line.indexOf("(by:") + 5;
        int endOfDeadline = line.lastIndexOf(")");
        String deadlineRaw = line.substring(startOfDeadline, endOfDeadline).trim();

        String[] deadlineParts = deadlineRaw.split(",");
        String datePart = deadlineParts[0].trim();
        String timePart = deadlineParts[1].trim();

        String[] dateSplit = datePart.split(" ");
        String formattedDate = dateSplit[0] + "/" + convertMonthToNumber(dateSplit[1]) + "/" + dateSplit[2];

        String formattedTime = convertTo24HourFormat(timePart);

        String deadlineInput = "deadline " + description + " /by " + formattedDate + " " + formattedTime
                + " /priority " + priority;

        return deadlineInput;
    }

    /**
     * Returns the parsed and formatted string to create an instance of the Event class.
     *
     * @param line The line used to convert to the command string.
     * @return Parsed input for Event class
     */
    public static String eventParser(String line) {
        String description = extractDescription(line);
        String priority = extractPriority(line);

        int startOfFrom = line.indexOf("(from:") + 7;
        int endOfFrom = line.indexOf("to:", startOfFrom) - 1;
        String from = line.substring(startOfFrom, endOfFrom).trim();

        String[] fromParts = from.split(", ");
        String startDate = convertToDate(fromParts[0]);
        String startTime = convertTo24HourFormat(fromParts[1]);

        int startOfTo = line.indexOf("to:") + 4;
        int endOfTo = line.lastIndexOf(")");
        String to = line.substring(startOfTo, endOfTo).trim();

        String[] toParts = to.split(", ");
        String endDate = convertToDate(toParts[0]);
        String endTime = convertTo24HourFormat(toParts[1]);

        String eventInput = "event " + description + " /from " + startDate + " " + startTime
                + " /to " + endDate + " " + endTime + " /priority " + priority;

        return eventInput;
    }

    /**
     * Returns the string integer representation of each month.
     *
     * @param month The month to convert to its string integer representation.
     * @return The month as a string.
     */
    private static String convertMonthToNumber(String month) {
        switch (month.toLowerCase()) {
        case "january": return "1";
        case "february": return "2";
        case "march": return "3";
        case "april": return "4";
        case "may": return "5";
        case "june": return "6";
        case "july": return "7";
        case "august": return "8";
        case "september": return "9";
        case "october": return "10";
        case "november": return "11";
        case "december": return "12";
        default: return "";
        }
    }

    /**
     * Returns the 12-hour format string to its 24-hour format.
     *
     * @param time The 12-hour format of the time.
     * @return The 24-hour format of the time.
     */
    private static String convertTo24HourFormat(String time) {
        boolean isPM = time.toLowerCase().endsWith("pm");
        String[] timeParts = time.substring(0, time.length() - 2).trim().split(":");

        int hours = Integer.parseInt(timeParts[0]);
        int minutes = Integer.parseInt(timeParts[1]);

        if (isPM && hours != 12) {
            hours += 12;
        } else if (!isPM && hours == 12) {
            hours = 0;
        }

        return String.format("%02d%02d", hours, minutes);
    }

    /**
     * Returns the date to this format d/M/yyyy.
     *
     * @param date The string representation of the date.
     * @return The date in the specified format.
     */
    private static String convertToDate(String date) {
        String[] dateParts = date.split(" ");
        String day = dateParts[0];
        String month = convertMonthToNumber(dateParts[1]);
        String year = dateParts[2];

        return day + "/" + month + "/" + year;
    }

    /**
     * Extracts description from line input.
     *
     * @param line The line it extracts the description from.
     * @return The extracted description.
     */
    private static String extractDescription(String line) {
        int startOfDescription = line.indexOf("] ") + 2;
        int endOfDescription = line.indexOf(" (Priority:");
        String description = line.substring(startOfDescription, endOfDescription).trim();

        return description;
    }

    /**
     * Extracts priority from line input.
     *
     * @param line The line it extracts the priority from.
     * @return The extracted priority.
     */
    private static String extractPriority(String line) {
        int startOfPriority = line.indexOf("(Priority:") + 10;
        int endOfPriority = line.indexOf(")", startOfPriority);
        String priority = line.substring(startOfPriority, endOfPriority).trim().toUpperCase();

        return priority;
    }
}

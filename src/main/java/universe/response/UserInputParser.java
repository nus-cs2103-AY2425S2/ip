package universe.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Responsible for parsing user input into meaningful components for chore management.
 * <p>
 * This class extracts key details from user commands, including:
 * </p>
 * <ul>
 *     <li>Description of the chore</li>
 *     <li>Deadline for Deadline chore type</li>
 *     <li>Start and end date/times for Event chore type</li>
 * </ul>
 *
 * @author yuqing-tham
 */
public class UserInputParser {
    private final String response;

    /**
     * Constructs a new UserInputParser with the user response.
     *
     * @param response Input by user.
     */
    public UserInputParser(String response) {
        this.response = response;
    }

    /**
     * Splits the response to obtain the part containing the Chore description.
     *
     * @return The choreDescription.
     */
    public String getDescription() {
        String[] parts = response.split(" ", 2);
        return parts[1].trim();
    }

    /**
     * Splits the response by the word "by".
     *
     * @return A String array containing the chore description and the deadline date/time.
     */
    public String[] getDeadlineDetails() {
        String temp = this.getDescription();
        String[] parts = temp.split("by", 2);
        return parts;
    }

    /**
     * Returns the deadline chore description by extracting the necessary parts.
     */
    public String getDeadlineDescription() {
        String description = this.getDeadlineDetails()[0].trim();
        return description;
    }

    /**
     * Returns the deadline chore deadline date/time by extracting the necessary parts.
     */
    public LocalDateTime getDeadlineDate() throws DateTimeParseException {
        String date = this.getDeadlineDetails()[1].trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy HHmm");
        return LocalDateTime.parse(date, formatter);
    }

    /**
     * Splits the response by the word "from" and "to".
     *
     * @return A String array containing the chore description and the event details.
     */
    public String[] getEventDetails() {
        String temp = this.getDescription();
        String[] mid = temp.split("from", 2);
        String[] furtherSplit = mid[1].split("to");
        String[] parts = new String[]{mid[0], furtherSplit[0], furtherSplit[1]};
        return parts;
    }

    /**
     * Returns the event chore description by extracting the necessary parts.
     */
    public String getEventDescription() {
        String[] temp = this.getEventDetails();
        return temp[0].trim();
    }

    /**
     * Returns the event chore start date/time by extracting the necessary parts.
     */
    public LocalDateTime getStartTime() throws DateTimeParseException {
        String start = this.getEventDetails()[1].trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy HHmm");
        return LocalDateTime.parse(start, formatter);
    }

    /**
     * Returns the event chore end date/time by extracting the necessary parts.
     */
    public LocalDateTime getEndTime() throws DateTimeParseException {
        String end = this.getEventDetails()[2].trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy HHmm");
        return LocalDateTime.parse(end, formatter);
    }

    /**
     * Splits the response by space for remove, check and uncheck commands.
     *
     * @return An integer representing the choreNumber.
     */
    public int getChoreNumber() {
        String[] parts = response.split(" ");
        int choreNumber = Integer.parseInt(parts[1]);
        return choreNumber;
    }

    /**
     * Splits the response by the space for filter commands.
     *
     * @return A String array containing the command and the date.
     */
    public String[] splitFilterCommand() {
        String[] parts = response.split(" ", 2);
        return parts;
    }

    /**
     * Returns the date to filter by.
     *
     * @return A String containing the date to be filtered by.
     */
    public String getFilterDateString() {
        String filterDateString = this.splitFilterCommand()[1].trim();
        return filterDateString;
    }

    /**
     * Changes the String to a LocalDateTime instance.
     */
    public LocalDateTime getFilterDate() {
        String date = this.getFilterDateString();
        LocalDateTime dateToFilter = LocalDateTime.parse(date + " 00:00",
                DateTimeFormatter.ofPattern("d MMM yyy HH:mm"));
        return dateToFilter;
    }

    /**
     * Splits the response by the space for find commands and extract the keyword part.
     *
     * @return A String containing the keyword.
     */
    public String getFindKeyword() {
        String[] parts = response.split(" ", 2);
        return parts[1];
    }

    /**
     * Splits the response by the space for the user inputs.
     *
     * @return A String containing the split parts of the user input.
     */
    public String[] splitCommand() {
        String[] splitResponse = response.split("\\s+");
        return splitResponse;
    }

    /**
     * Returns the first word of the user input.
     */
    public String getCommand() {
        String command = this.splitCommand()[0].trim(); // take first word
        return command;
    }
}

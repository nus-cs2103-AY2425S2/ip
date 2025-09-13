package universe.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Responsible for parsing the information in the file into meaningful components for chore management.
 * <p>
 * This class extracts key details from the file, including:
 * </p>
 * <ul>
 *     <li>Description of the chore</li>
 *     <li>Deadline for Deadline chore type</li>
 *     <li>Start and end date/times for Event chore type</li>
 * </ul>
 *
 * @author yuqing-tham
 */
public class FileParser {
    private final String choreInFile;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy HH:mm");

    /**
     * Constructs a new FileParser with the line containing the chore information.
     *
     * @param choreInFile The line in the file read by the scanner.
     */
    public FileParser(String choreInFile) {
        this.choreInFile = choreInFile;
    }

    /**
     * Splits the response by the " | " character when reading file.
     *
     * @return A String array with the relevant details of the chore in the checklist.
     */
    public String[] splitFileInformation() {
        String[] parts = choreInFile.split(" \\| ", 4);
        return parts;
    }

    /**
     * Returns the String representing the type of the chore.
     */
    public String getChoreType() {
        String choreType = this.splitFileInformation()[0].trim();
        return choreType;
    }

    /**
     * Returns the String representing the status of the chore.
     */
    public String getStatus() {
        String isDone = this.splitFileInformation()[1].trim();
        return isDone;
    }

    /**
     * Returns the String representing the description of the chore.
     */
    public String getChoreDescription() {
        String description = this.splitFileInformation()[2].trim();
        return description;
    }

    /**
     * Returns the String representing the deadline date/time of the chore.
     */
    public LocalDateTime getDeadlineDateTime() {
        String deadlineDate = this.splitFileInformation()[3].trim();
        LocalDateTime formattedDate = LocalDateTime.parse(deadlineDate, formatter);
        return formattedDate;
    }

    /**
     * Splits the response by "to" to split start and end date/times for an Event.
     *
     * @return A String array with the relevant details of the Event.
     */
    public String[] splitEventTime() {
        String[] temp = this.splitFileInformation()[3].split("to");
        return temp;
    }

    /**
     * Returns the String representing the start date/time of the Event chore.
     */
    public LocalDateTime getEventStartDateTime() {
        String startDateTime = this.splitEventTime()[0].trim();
        LocalDateTime formattedStart = LocalDateTime.parse(startDateTime, formatter);
        return formattedStart;
    }

    /**
     * Returns the String representing the end date/time of the Event chore.
     */
    public LocalDateTime getEventEndDateTime() {
        String endDateTime = this.splitEventTime()[1].trim();
        LocalDateTime formattedEnd = LocalDateTime.parse(endDateTime, formatter);
        return formattedEnd;
    }
}

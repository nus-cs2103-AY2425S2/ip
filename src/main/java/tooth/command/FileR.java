package tooth.command;

import java.time.LocalDate;

import tooth.exception.InvalidFileFormatException;
import tooth.task.Deadline;
import tooth.task.Event;
import tooth.task.Task;
import tooth.task.ToDo;

/**
 * Reads files to repopulate TaskList
 */
public class FileR {
    /**
     * Creates task based on serialisedString
     * @param serializedString the string form of task in save.txt
     * @return the task
     */
    public static Task create(String serializedString) throws InvalidFileFormatException {
        String[] parts = serializedString.split("\\|");
        if (parts[0].equals("T")) {
            if (parts.length != 3) {
                throw new InvalidFileFormatException("Wrong Number of Parameters: " + serializedString);
            }
            String name = parts[1];
            boolean done = Boolean.parseBoolean(parts[2]);
            return ToDo.of(name, done);
        } else if (parts[0].equals("D")) {
            if (parts.length != 4) {
                throw new InvalidFileFormatException("Wrong Number of Parameters: " + serializedString);
            }
            String name = parts[1];
            LocalDate deadline = LocalDate.parse(parts[2]);
            boolean done = Boolean.parseBoolean(parts[3]);
            return Deadline.of(name, done, deadline);
        } else if (parts[0].equals("E")) {
            if (parts.length != 5) {
                throw new InvalidFileFormatException("Wrong Number of Parameters: " + serializedString);
            }
            String name = parts[1];
            LocalDate from = LocalDate.parse(parts[2]);
            LocalDate to = LocalDate.parse(parts[3]);
            boolean done = Boolean.parseBoolean(parts[4]);
            return Event.of(name, done, from, to);
        }
        throw new InvalidFileFormatException("Header is wrong");
    }
}

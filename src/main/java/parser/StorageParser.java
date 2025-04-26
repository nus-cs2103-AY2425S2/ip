package parser;

import exception.SunpterException;
import task.Task;
import task.ToDo;
import task.Deadline;
import task.Event;

/**
 * Handles parsing from the storage file
 */

public class StorageParser {

    public static Task parseStoredTask(String line) {
        String[] parts = line.split(" ", 4);

        assert parts.length >= 4;

        String type = parts[0];
        boolean isDone = Boolean.parseBoolean(parts[1]);
        int priority = Integer.parseInt(parts[2]);
        String details = parts[3];

        Task task = switch(type) {
            case "[T]" -> parseTodo(details, priority);
            case "[E]" -> parseEvent(details, priority);
            case "[D]" -> parseDeadline(details, priority);
            default -> throw new SunpterException("You seeing things? Unknown task type: " + type);
        };

        if(isDone) {
            task.setCompleted();
        }
        return task;
    }

    private static ToDo parseTodo(String details, int priority) {
        return new ToDo(details, priority);
    }

    private static Event parseEvent(String details, int priority) {
        String[] parts = details.split(" /from | /to ");
        return new Event(parts[0], parts[1], parts[2], priority);
    }

    private static Deadline parseDeadline(String details, int priority) {
        String[] parts = details.split(" /by ");
        return new Deadline(parts[0], parts[1], priority);
    }
}
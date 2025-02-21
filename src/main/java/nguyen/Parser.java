package nguyen;

/**
 * Parses user input into commands and tasks.
 */
public class Parser {
    /**
     * Converts a full command string into a Command object.
     *
     * @param fullCommand the full user input string
     * @return a Command object representing the parsed command
     * @throws NguyenException if the command is invalid
     */
    public static Command parse(String fullCommand) throws NguyenException {
        assert fullCommand != null : "Full command cannot be null";
        assert !fullCommand.trim().isEmpty() : "Full command cannot be empty";

        return new Command(fullCommand);
    }

    /**
     * Converts a stored task string into a Task object.
     *
     * @param line the task string from storage
     * @return a Task object representing the parsed task
     */
    public static Task parseTask(String line) throws NguyenException {
        assert line != null : "Task cannot be null";
        assert !line.trim().isEmpty() : "Task line cannot be empty";
        assert line.length() >= 6 : "Task line should have sufficient length for valid parsing";

        Task task = null;
        if (line.startsWith("[T]")) {
            assert line.length() > 6 : "Todo task should contain a description";
            String description = line.substring(6);
            task = new Todo(description);
        } else if (line.startsWith("[D]")) {
            assert line.contains("(by:") : "Deadline task should have a 'by' date";
            assert line.indexOf(" (by:") > 6 : "Deadline task should have a valid description";

            String description = line.substring(6, line.indexOf(" (by:"));
            String by = line.substring(line.indexOf("by:") + 4, line.length() - 1);
            task = new Deadline(description, by);
        } else if (line.startsWith("[E]")) {
            assert line.contains("(from:") && line.contains(" to:")
                    : "Event task should have 'from' and 'to' timestamps";
            assert line.indexOf(" (from:") > 6 : "Event task should have a valid description";

            String description = line.substring(6, line.indexOf(" (from:"));
            String from = line.substring(line.indexOf("from:") + 6, line.indexOf(" to:"));
            String to = line.substring(line.indexOf("to:") + 4, line.length() - 1);
            task = new Event(description, from, to);
        }

        assert task != null : "Parsed task should not be null";
        assert line.length() >= 5 : "Task line should have sufficient length to check completion status";

        if (line.substring(4, 5).equals("X")) {
            assert task != null : "Task should not be null before marking";
            task.mark();
        }

        return task;
    }
}

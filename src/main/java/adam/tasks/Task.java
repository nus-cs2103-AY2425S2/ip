package adam.tasks;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import adam.exceptions.AdamException;
import adam.exceptions.EmptyDescription;
import adam.exceptions.InvalidCommand;
import adam.exceptions.InvalidLogFile;
import adam.exceptions.MissingArgument;
import adam.parser.Parser;

/**
 * Represents a task that has to be done.
 */
public abstract class Task {
    /** Represents if the task is done */
    private boolean isDone;
    /** String representing a description of the task */
    private final String description;

    /**
     * Constructs a Task object with a specified description.
     *
     * @param description The description of the task.
     * @throws AdamException If the description is empty.
     */
    public Task(String description) throws AdamException {
        if (description.equals("")) {
            throw new EmptyDescription();
        }
        assert !description.contains("|") : "Description should not contain |";
        this.isDone = false;
        this.description = description;
    }

    /**
     * Splits the input into a description and a list of delimiters.
     *
     * @param input A list of input parts to be split.
     * @param delimiters A list of delimiters to split the input by.
     * @return A list of strings representing the split input.
     * @throws AdamException If a delimiter is missing.
     */
    private static List<String> splitDescription(List<String> input, List<String> delimiters)
            throws AdamException {
        String cur = "";
        int delimiterIndex = 0;
        List<String> results = new ArrayList<String>();

        for (int i = 1; i < input.size(); i++) {
            if (delimiterIndex < delimiters.size()
                    && delimiters.get(delimiterIndex).equals(input.get(i))) {
                results.add(cur);
                cur = "";

                delimiterIndex++;
            } else {
                if (cur.length() > 0) {
                    cur += " ";
                }
                cur += input.get(i);
            }
        }

        results.add(cur);

        if (delimiterIndex < delimiters.size()) {
            throw new MissingArgument(delimiters.get(delimiterIndex));
        }

        return results;
    }

    /**
     * Creates a task from user input.
     *
     * @param userInput String representing the user input.
     * @return A task object representing the user input.
     * @throws AdamException If the user input is invalid.
     */
    public static Task of(String userInput) throws AdamException {
        List<String> parts = List.of(userInput.split(" "));
        if (parts.get(0).equals("todo")) {
            List<String> split = splitDescription(parts, List.of());
            return new ToDo(split.get(0));
        } else if (parts.get(0).equals("deadline")) {
            List<String> split = splitDescription(parts, List.of("/by"));
            return new Deadline(split.get(0), Parser.parseInputDate(split.get(1)));
        } else if (parts.get(0).equals("event")) {
            List<String> split = splitDescription(parts, List.of("/from", "/to"));
            return new Event(split.get(0), Parser.parseInputDate(split.get(1)),
                    Parser.parseInputDate(split.get(2)));
        } else if (parts.get(0).equals("fixed")) {
            List<String> split = splitDescription(parts, List.of("/takes"));
            return new FixedDuration(split.get(0), Parser.parseDuration(split.get(1)));
        }
        throw new InvalidCommand();
    }

    /**
     * Creates a task from a log file.
     *
     * @param logText The log text to create the task from.
     * @return A task object representing the log text.
     * @throws AdamException If the log text is invalid.
     */
    public static Task fromLog(String logText) throws AdamException {
        // | is a special character in regex, so we need to escape it
        List<String> parts = List.of(logText.split(" \\| "));
        Task task = null;
        try {
            switch (parts.get(0)) {
            case "T" -> task = new ToDo(parts.get(2));
            case "D" -> task = new Deadline(parts.get(2), Parser.parseInputDate(parts.get(3)));
            case "E" -> task = new Event(parts.get(2), Parser.parseInputDate(parts.get(3)),
                        Parser.parseInputDate(parts.get(4)));
            case "F" -> task = new FixedDuration(parts.get(2), Parser.parseDuration(parts.get(3)));
            default -> throw new InvalidLogFile();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidLogFile();
        }
        assert (parts.get(1).equals("true") || parts.get(1).equals("false"))
                : "isDone should be true or false";
        if (parts.get(1).equals("true")) {
            task.markDone();
        }
        return task;
    }

    /**
     * Marks the task as done
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Checks if the task contains a query string
     *
     * @param query The query string to check for.
     * @return True if the task contains the query string, false otherwise.
     */
    public boolean containsQuery(String query) {
        return this.description.contains(query);
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }

    /**
     * Gets the description of the task for logging.
     *
     * @return the description to be logged.
     */
    public String toLogString() {
        return this.isDone + " | " + this.description;
    }

    public abstract boolean isOn(LocalDate date);
}

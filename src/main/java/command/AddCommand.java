package command;

import exception.SunpterException;
import storage.Storage;
import task.Task;
import task.Deadline;
import task.ToDo;
import task.Event;
import task.PriorityRoster;
import ui.Ui;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Command that takes in input and adds tasks to the PriorityRoster
 * Supports three task types: Todo, Deadline, and Event
 */
public class AddCommand extends Command {
    private final String input;

    private static final Pattern DEADLINE_FORMAT = Pattern.compile(
            "deadline\\s+(?<description>\\S.*?)\\s+/by\\s+(?<deadline>\\S.*?)\\s+/priority\\s+(?<priority>\\d+)\\s*$");

    private static final Pattern EVENT_FORMAT = Pattern.compile(
            "event\\s+(?<description>\\S.*?)\\s+/from\\s+(?<start>\\S.*?)\\s+/to\\s+(?<end>\\S.*?)\\s+/priority\\s+(?<priority>\\d+)\\s*$");

    private static final Pattern TODO_FORMAT = Pattern.compile(
            "todo\\s+(?<description>\\S.*?)\\s+/priority\\s+(?<priority>\\d+)\\s*$");

    /**
     * Creates a new AddCommand
     * @param input The raw command input
     * @throws SunpterException if input is null
     */
    public AddCommand(String input) {
        this.input = input.trim();
    }

    @Override
    public String execute(PriorityRoster roster, Ui ui, Storage storage) {
        try {
            validateInput();
            Task task = createTask(input);
            roster.addTask(task);
            return ui.showTaskAddedMessage(task, roster.numberOfTasks());
        } catch (SunpterException e) {
            return e.getMessage();
        }
    }

    /**
     * Validates the basic input structure
     * @throws SunpterException if input is empty or doesn't start with a valid command
     */
    private void validateInput() throws SunpterException {
        String[] parts = input.split("\\s+", 2);
        if (parts.length < 2) {
            throw new SunpterException("Nuh-uh. I need more than that.");
        }

        String command = parts[0].toLowerCase();
        if (!command.matches("todo|deadline|event")) {
            throw new SunpterException("SLAP👋 Task command should be: todo, deadline, or event");
        }
    }

    /**
     * Creates a task based on the input command
     * @param input The command input string
     * @return The created Task object
     * @throws SunpterException if the input format is invalid
     */
    private Task createTask(String input) throws SunpterException {
        String command = input.split("\\s+", 2)[0].toLowerCase();
        return switch (command) {
            case "deadline" -> createDeadlineTask(input);
            case "event" -> createEventTask(input);
            case "todo" -> createTodoTask(input);
            default -> throw new SunpterException("SLAP👋unknown task type");
        };
    }

    /**
     * Creates a Deadline task from the input
     */
    private Deadline createDeadlineTask(String input) throws SunpterException {
        Matcher matcher = DEADLINE_FORMAT.matcher(input);
        if (!matcher.matches()) {
            throw new SunpterException("SLAP👋 Invalid deadline format. Expected: deadline {description} /by {date} /priority {number}");
        }

        String description = matcher.group("description");
        String deadline = matcher.group("deadline");
        int priority = parsePriority(matcher.group("priority"));

        return new Deadline(description, deadline, priority);
    }

    /**
     * Creates an Event task from the input
     */
    private Event createEventTask(String input) throws SunpterException {
        Matcher matcher = EVENT_FORMAT.matcher(input);
        if (!matcher.matches()) {
            throw new SunpterException("SLAP👋 Invalid event format. Expected: event {description} /from {date} /to {date} /priority {number}");
        }

        String description = matcher.group("description");
        String startDate = matcher.group("start");
        String endDate = matcher.group("end");
        int priority = parsePriority(matcher.group("priority"));

        return new Event(description, startDate, endDate, priority);
    }

    /**
     * Creates a Todo task from the input
     */
    private ToDo createTodoTask(String input) throws SunpterException {
        Matcher matcher = TODO_FORMAT.matcher(input);
        if (!matcher.matches()) {
            throw new SunpterException("SLAP👋 Invalid todo format. Expected: todo {description} /priority {number}");
        }

        String description = matcher.group("description");
        int priority = parsePriority(matcher.group("priority"));

        return new ToDo(description, priority);
    }

    /**
     * Parses and validates the priority value
     * @throws SunpterException if priority is invalid
     */
    private int parsePriority(String priorityStr) throws SunpterException {
        int priority = Integer.parseInt(priorityStr);
        if (priority < 0) {
            throw new SunpterException("SLAP👋Priority cannot be negative");
        }
        return priority;
    }
}
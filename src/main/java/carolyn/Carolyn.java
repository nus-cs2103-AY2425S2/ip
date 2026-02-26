package carolyn;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class Carolyn {
    protected Parser parser;
    protected Storage storage;
    protected Ui ui;
    protected TaskList tasks;

    public Carolyn() {
        this.parser = new Parser();
        this.storage = new Storage();
        this.ui = new Ui();
        this.tasks = storage.load();
    }

    /**
     * Runs the main program loop, processing user input and executing commands.
     * <p>
     * This method:
     * <ul>
     *     <li>Initializes a {@link Parser}, {@link Storage}, and {@link TaskList}.</li>
     *     <li>Loads previously saved tasks from storage.</li>
     *     <li>Continuously reads user input, processes commands, and updates the task list.</li>
     *     <li>Handles different command types: listing tasks, marking/unmarking tasks, deleting tasks, and adding new tasks.</li>
     *     <li>Saves changes to storage after each modification.</li>
     *     <li>Exits when the "bye" command is received.</li>
     * </ul>
     * </p>
     */
    public String getResponse(String s) {
        Parser parser = new Parser();
        Storage storage = new Storage();
        TaskList tasks = storage.load();
        assert tasks != null : "A task list should have been loaded, empty or not";

        try {
            Command c = parser.parse(s);
            return executeCommand(c, tasks, storage);
        } catch (CarolynException e) {
            return ui.printException(e);
        }
    }

    private String executeCommand(Command c, TaskList tasks, Storage storage) throws CarolynException {
        String type = c.getType();
        Object[] args = c.getArgs();

        return switch (type) {
            case "bye" -> ui.sayGoodBye();
            case "list" -> ui.printTaskList(tasks);
            case "mark", "unmark" -> {
                try {
                    Task t = tasks.get((int) args[0]);
                    t.mark(type.equals("mark"));
                    storage.save(tasks);
                    yield type.equals("mark") ? ui.printForMark(t) : ui.printForUnmark(t);
                } catch (CarolynException e) {
                    throw e;
                }
            }
            case "delete" -> {
                try {
                    int index = (int) args[0];
                    Task t = tasks.get((int) args[0]);
                    tasks.delete((int) args[0]);
                    storage.save(tasks);
                    yield ui.printForDelete(t, tasks);
                } catch (CarolynException e) {
                    throw e;
                }
            }
            case "find" -> ui.printTaskList(tasks.find((String) args[0]));
            case "tag" -> {
                try {
                    Task t = tasks.get((int) args[0]);
                    String tag = (String) args[1];
                    t.tag(tag);
                    storage.save(tasks);
                    yield ui.printForAddTag(t, tag);
                } catch (CarolynException e) {
                    throw e;
                }
            }
            case "todo" -> addTask(new ToDo((String) args[0]), tasks, storage);
            case "deadline" -> addTask(new Deadline((String) args[0], (LocalDate) args[1]), tasks, storage);
            case "event" ->
                    addTask(new Event((String) args[0], (LocalDateTime) args[1], (LocalDateTime) args[2]), tasks, storage);
            default -> throw new CarolynException("Unknown command: " + type);
        };
    }

    private String addTask(Task t, TaskList tasks, Storage storage) {
        tasks.add(t);
        storage.save(tasks);
        return ui.printForAddTask(t, tasks);
    }
}
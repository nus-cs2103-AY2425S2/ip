package kyrie;

/**
 * Represents a command to add an event task.
 */
public class AddEventCommand extends Command {
    private String name;
    private DateTime from;
    private DateTime to;
    public static final String COMMAND_WORD = "event";

    /**
     * Constructor for AddEventCommand.
     * 
     * @param name The name of the event task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public AddEventCommand(String name, DateTime from, DateTime to) {
        super();
        assert name != null : "Name of event cannot be null";
        this.name = name;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the command to add an event task.
     * 
     * @param storage The storage object to save the task list to.
     * @param ui The ui object to show the user the added task.
     * @param tasks The task list to add the task to.
     */
    @Override
    public void execute(Storage storage, Ui ui, TaskList tasks) {
        Event event = new Event(name, from, to);
        tasks.addTask(event);
        ui.showAddedTask(event);
        storage.saveData(tasks);
    }
}
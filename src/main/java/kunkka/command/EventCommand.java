package kunkka.command;
import kunkka.components.Event;
import kunkka.components.KunkkaException;
import kunkka.tasklist.Tasklist;

/**
 * Represents a command to add an event task to the task list.
 */
public class EventCommand extends Command {
    
    protected String description;
    protected String from;
    protected String to;
    protected int priority;

    /**
     * Constructs an EventCommand object.
     * 
     * @param description Description of the event task.
     * @param from Start time of the event task.
     * @param to End time of the event task.
     */
    public EventCommand(String description, String from, String to, int priority) {
        super("event");
        this.description = description;
        this.from = from;
        this.to = to;
        this.priority = priority;
    }

    /**
     * Executes the event command to add an event task to the task list.
     * 
     * @param tasks Tasklist object that stores the list of tasks.
     */
    public String execute(Tasklist tasks) {
        try {
            if (description.trim().equals("")) {
                throw new KunkkaException("Error: Task description cannot be empty");
            }
            else if (from.trim().equals("")) {
                throw new KunkkaException("Error: Event start time cannot be empty");
            }
            else if (to.trim().equals("")) {
                throw new KunkkaException("Error: Event end time cannot be empty");
            }
            else {
                Event task = new Event(description, from, to, false, priority);
                if (task.getDuration() < 0) {
                    throw new KunkkaException("Error: Event end time must be after start time");
                }
                tasks.addTask(task);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + task);
                System.out.println("Now you have " + tasks.getTasks().size() + " tasks in the list.");
                return "Got it. I've added this task:\n  " + task + "\nNow you have " + tasks.getTasks().size() + " tasks in the list.";
            }
        }
        catch (KunkkaException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
}

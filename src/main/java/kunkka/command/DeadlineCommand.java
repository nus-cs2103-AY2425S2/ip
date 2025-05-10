package kunkka.command;
import kunkka.components.KunkkaException;
import kunkka.components.Task;
import kunkka.components.Deadline;
import kunkka.tasklist.Tasklist;

public class DeadlineCommand extends Command {
    
    protected String description;
    protected String by;
    protected int priority;

    /**
     * Constructor for DeadlineCommand
     * @param description Description of the deadline task
     * @param by Deadline of the task
     * @param priority Priority of the task
     */
    public DeadlineCommand(String description, String by, int priority) {
        super("deadline");
        this.description = description;
        this.by = by;
        this.priority = priority;
    }

    /**
     * Executes the deadline command
     * @param tasks Tasklist containing all tasks
     */
    public String execute(Tasklist tasks) {
        try {
            if (description.equals("")) {
                throw new KunkkaException("Error: Task name cannot be empty");
            }
            else if (by.equals("")) {
                throw new KunkkaException("Error: Deadline cannot be empty");
            }
            else {
                Task task = new Deadline(description, by, false, priority);
                tasks.addTask(task);
                String response = "Got it. I've added this task:\n" + task + "\nNow you have " + tasks.getTasks().size() + " tasks in the list.";
                System.out.println(response);
                return response;
            }
        }
        catch (KunkkaException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
}

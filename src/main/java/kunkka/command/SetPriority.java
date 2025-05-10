package kunkka.command;
import kunkka.tasklist.Tasklist;
import kunkka.components.Task;
import kunkka.components.KunkkaException;

public class SetPriority extends Command {
    private int priority;
    private int index;

    /**
     * Creates a new SetPriority command with the given priority.
     *
     * @param priority The priority to set.
     * @param index The index of the task to set the priority of.
     */
    public SetPriority(int priority, int index) {
        super("setpriority");
        this.priority = priority;
        this.index = index;
    }

    /**
     * Executes the SetPriority command.
     *
     * @param tasks The tasklist to set the priority of a task in.
     */
    public String execute(Tasklist tasks) {
        try {
            if (index < 0 || index >= tasks.getSize()) {
                throw new KunkkaException("Invalid index");
            }
            Task task = tasks.getTask(index);
            task.setPriority(priority);
            index += 1;
            return "Priority of task " + task.getName() + " set to " + priority;

        } catch (KunkkaException e) {
            return e.getMessage();
        }
    }
}

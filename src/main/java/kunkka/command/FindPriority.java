package kunkka.command;
import kunkka.tasklist.Tasklist;
import kunkka.components.Task;

public class FindPriority extends Command {
    private int priority;

    /**
     * Creates a new FindPriority command with the given priority.
     *
     * @param priority The priority to search for.
     */
    public FindPriority(int priority) {
        super("findpriority");
        this.priority = priority;
    }

    /**
     * Executes the FindPriority command.
     *
     * @param tasks The tasklist to search for tasks in.
     */
    @Override
    public String execute(Tasklist tasks) {
        System.out.println("Here are the tasks with priority " + priority + ":");
        StringBuilder output = new StringBuilder();
        output.append("Here are the tasks with priority " + priority + ":\n");
        for (Task task : tasks.getTasks()) {
            if (task.getPriority() == priority) {
                System.out.println(task);
                output.append(task).append("\n");
            }
        }
        return output.toString();
    }
}

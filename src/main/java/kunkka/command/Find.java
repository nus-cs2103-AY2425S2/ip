package kunkka.command;
import kunkka.tasklist.Tasklist;
import kunkka.components.Task;

/**
 * Represents a command to find tasks with a keyword.
 */
public class Find extends Command {
    private String keyword;

    /**
     * Creates a new Find command with the given keyword.
     *
     * @param keyword The keyword to search for.
     */
    public Find(String keyword) {
        super("find");
        this.keyword = keyword;
    }

    /**
     * Executes the Find command.
     *
     * @param tasks The tasklist to search for tasks in.
     */
    @Override
    public String execute(Tasklist tasks) {
        StringBuilder output = new StringBuilder();
        for (Task task : tasks.getTasks()) {
            if (task.getName().contains(keyword)) {
                System.out.println(task);
                output.append(task).append("\n");
            }
        }
        if (output.length() == 0) {
            return "No tasks found.";
        }
        return output.toString();
    }
    
}

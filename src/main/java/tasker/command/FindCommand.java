package tasker.command;

import tasker.Storage;
import tasker.task.TaskList;

/**
 * Finds tasks from a list that contains a string.
 */
public class FindCommand extends Command {
    /** The search term */
    private String term;

    /**
     * Class constructor.
     *
     * @param term The string to search for.
     */
    public FindCommand(String term) {
        this.term = term;
    }

    /**
     * Finds tasks which description contains a string.
     *
     * @param tasks   The task list to execute this command on.
     * @param storage The storage for saving changes to.
     * @return A string of tasks which description contains the term.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.findTasks(this.term);
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }

        FindCommand that = (FindCommand) obj;
        return term != null ? term.equals(that.term) : that.term == null;
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + (term == null ? 0 : term.hashCode());
    }
}

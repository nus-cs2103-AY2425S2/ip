package buddy.task;

import buddy.exception.BuddyException;
import buddy.exception.BuddyInvalidCommandException;

/**
 * Represents Todo event
 */
public class Todo extends Task {
    protected String by;

    /**
     * Constructor for Todo class.
     *
     * @param description Description of todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns String representation of todo task in storage.
     *
     * @return String representation of todo task in storage.
     */
    @Override
    public String toStorageStringFormat() {
        String result = "T | ";
        result += this.isDone ? "1" : "0";
        result += " | " + this.description + "\n";
        return result;
    }

    @Override
    public void updateTask(String field, String newValue) throws BuddyException {
        if (field.equals("/description")) {
            this.description = newValue;
        } else {
            throw new BuddyInvalidCommandException("To edit todo task, it should be in this format:\n"
                    + "update taskId /description newDescription");
        }
    }

    /**
     * Returns String representation of todo task.
     *
     * @return String representation of todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

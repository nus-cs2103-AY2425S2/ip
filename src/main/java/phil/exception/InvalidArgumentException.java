package phil.exception;

/**
 * Represents the exception to be returned for invalid arguments passed when creating tasks.
 */
public class InvalidArgumentException extends PhilException {
    private String explainedUsage;

    /**
     * Sets up the InvalidArgumentException.
     *
     * @param commandType enum Command type representing the type of command passed by the user.
     */
    public InvalidArgumentException(CommandType commandType) {
        super("Invalid arguments passed for creating tasks.");
        if (commandType == CommandType.CREATE_TODO) {
            this.explainedUsage = "A Todo task requires a description of minimally one word."
                    + "\n For example, 'todo read' creates the task 'read'.";
        } else if (commandType == CommandType.CREATE_DEADLINE) {
            this.explainedUsage = "A Deadline task requires a description AND a deadline, "
                                  + "specified as a string after '/by'."
                                  + "\n For example, 'deadline read /by Tuesday' creates the task"
                                  + " 'read' with a deadline of 'Tuesday'.";
        } else if (commandType == CommandType.CREATE_EVENT) {
            this.explainedUsage = "An Event task requires a description AND a from date, "
                                  + "specified as a string after '/from'."
                                  + "\n And a to date, specified as a string after '/to'."
                                  + "\n For example, 'event reading /from Monday /to Tuesday' "
                                  + " creates the event 'reading'\n from 'Monday' to 'Tuesday'.";
        } else if (commandType == CommandType.FIND_TASK) {
            this.explainedUsage = "Finding a task requires a search term of minimally one word."
                    + "\n For example, 'find hello' finds all tasks with the keyword 'hello'.";
        } else if (commandType == CommandType.CREATE_NOTE) {
            this.explainedUsage = "A Note requires a description of minimally one word."
                    + "\n For example, 'new-note the sky is blue' creates the note 'the sky is blue'.";
        } else {
            this.explainedUsage = "";
        }
    }

    /**
     * Sets up the InvalidArgumentException, for commands MARK_DONE, MARK_UNDONE and DELETE_TASK.
     *
     * @param commandType enum Command type representing the type of command passed by the user.
     * @param numTasks number of tasks, for commands involving marking, unmarking or deleting tasks.
     */
    public InvalidArgumentException(CommandType commandType, int numTasks) {
        super("Invalid arguments passed for marking or deleting tasks.");
        if (numTasks == 0) {
            this.explainedUsage = "Add at least one task to your list, before calling mark or delete.";
        } else if (commandType == CommandType.MARK_DONE) {
            this.explainedUsage = "To mark a task as done, say 'mark X' where X is the task to mark as done."
                    + "\nMake sure X is a valid positive integer from 1 to " + numTasks + " (number of tasks). "
                    + "\nFor example, calling 'mark 2' marks the second task as done."
                    + "\nSay 'list' to see the tasks you have stored.";
        } else if (commandType == CommandType.MARK_UNDONE) {
            this.explainedUsage = "To mark a task as not done, say 'unmark X' where X is the task to mark as not done."
                                  + "\nMake sure X is a valid positive integer from 1 to " + numTasks + "."
                                  + "\nFor example, calling 'unmark 2' marks the second task as not done."
                                  + "\nSay 'list' to see the tasks you have stored.";
        } else if (commandType == CommandType.DELETE_TASK) {
            this.explainedUsage = "To delete a task, say 'delete X' where X is the task to remove."
                                  + "\nMake sure X is a valid positive integer from 1 to " + numTasks + " . "
                                  + "\nFor example, calling 'delete 2' deletes the second task."
                                  + "\nSay 'list' to see the tasks you have stored.";
        } else if (commandType == CommandType.DELETE_NOTE) {
            this.explainedUsage = "To delete a note, say 'delete X' where X is the note to remove."
                    + "\nMake sure X is a valid positive integer from 1 to " + numTasks + " . "
                    + "\nFor example, calling 'delete-note 2' deletes the second note."
                    + "\nSay 'list-note' to see the note you have stored.";
        } else {
            this.explainedUsage = "";
        }
    }

    /**
     * Returns message and explanation.
     *
     * @return message with explained usage.
     */
    @Override
    public String getMessage() {
        return super.getMessage() + "\nDetails: \n" + this.explainedUsage;
    }
}

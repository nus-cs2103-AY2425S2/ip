package Acheron.CommandInfo;

/**
 * Used to create an object that represents the help command
 * info when deadline is used
 */
public class DeadlineCommandInfo extends GenericCommandInfo {
    /**
     * Overrides the toString() method so a custom message is printed out
     * @return Custom string message
     */
    @Override
    public String toString() {
        String topHalf = super.toString();
        return topHalf
                + "deadline (task name) /by (date) \n"
                + "Add a deadline task\n\n"
                + "Inputs:\n"
                + "task name: The name of the task\n"
                + "date: The deadline date in the format YYYY-MM-DD\n\n"
                + "E.g usage: deadline kill IX /by 2099-01-12\n";
    }
}


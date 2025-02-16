package Acheron.CommandInfo;

/**
 * Used to create an object that represents the help command
 * info when unmark is used
 */
public class UnmarkCommandInfo extends GenericCommandInfo {
    /**
     * Overrides the toString() method so a custom message is printed out
     * @return Custom string message
     */
    @Override
    public String toString() {
        String topHalf = super.toString();
        return topHalf
                + "unmark (num)\n"
                + "Unmark a task as incomplete\n\n"
                + "Inputs:\n"
                + "num: The task number\n\n"
                + "E.g usage: unmark 1\n";
    }
}

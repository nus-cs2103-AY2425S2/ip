package Acheron.CommandInfo;

/**
 * Used to create an object that represents the help command
 * info when mark is used
 */
public class MarkCommandInfo extends GenericCommandInfo {
    /**
     * Overrides the toString() method so a custom message is printed out
     * @return Custom string message
     */
    @Override
    public String toString() {
        String topHalf = super.toString();
        return topHalf
                + "mark (num)\n"
                + "Mark a task as complete\n\n"
                + "Inputs:\n"
                + "num: The task number\n\n"
                + "E.g usage: mark 1\n";
    }
}

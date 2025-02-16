package Acheron.CommandInfo;

/**
 * Used to create an object that represents the help command
 * info when list is used
 */
public class ListCommandInfo extends GenericCommandInfo {
    /**
     * Overrides the toString() method so a custom message is printed out
     * @return Custom string message
     */
    @Override
    public String toString() {
        String topHalf = super.toString();
        return topHalf
                + "list\n"
                + "Display all existing tasks\n\n"
                + "E.g usage: list\n";
    }
}

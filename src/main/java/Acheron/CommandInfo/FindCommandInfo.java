package Acheron.CommandInfo;

/**
 * Used to create an object that represents the help command
 * info when find is used
 */
public class FindCommandInfo extends GenericCommandInfo {
    /**
     * Overrides the toString() method so a custom message is printed out
     * @return Custom string message
     */
    @Override
    public String toString() {
        String topHalf = super.toString();
        return topHalf
                + "find (text)\n"
                + "Find all tasks with names containing the text\n\n"
                + "Inputs:\n"
                + "text: The text keyword\n\n"
                + "E.g usage: find nihility\n";
    }
}

package Acheron.CommandInfo;

/**
 * Used to create an object that represents the help command
 * info when delete is used
 */
public class DeleteCommandInfo extends GenericCommandInfo {
    /**
     * Overrides the toString() method so a custom message is printed out
     * @return Custom string message
     */
    @Override
    public String toString() {
        String topHalf = super.toString();
        return topHalf
                + "delete (num) \n"
                + "Delete a task\n\n"
                + "Inputs:\n"
                + "num: The task number\n\n"
                + "E.g usage: delete 1\n";
    }
}

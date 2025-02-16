package Acheron.CommandInfo;

/**
 * Used to create an object that represents the help command
 * info when todo is used
 */
public class ToDoCommandInfo extends GenericCommandInfo {
    /**
     * Overrides the toString() method so a custom message is printed out
     * @return Custom string message
     */
    @Override
    public String toString() {
        String topHalf = super.toString();
        return topHalf
                + "todo (task name)\n"
                + "Add a new todo task\n\n"
                + "Inputs:\n"
                + "task name: The name of the task\n\n"
                + "E.g usage: todo explore Penacony\n";
    }
}

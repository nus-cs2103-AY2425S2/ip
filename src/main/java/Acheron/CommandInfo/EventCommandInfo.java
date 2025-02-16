package Acheron.CommandInfo;


/**
 * Used to create an object that represents the help command
 * info when event is used
 */
public class EventCommandInfo extends GenericCommandInfo {
    /**
     * Overrides the toString() method so a custom message is printed out
     * @return Custom string message
     */
    @Override
    public String toString() {
        String topHalf = super.toString();
        return topHalf
                + "event (task name) /from (from) /to (to) \n"
                + "Add a event task\n\n"
                + "Inputs:\n"
                + "task name: The name of the task\n"
                + "from: The from date in the format YYYY-MM-DD\n"
                + "to: The to date in the format YYYY-MM-DD\n\n"
                + "E.g usage: event escape the dream /from 2024-01-12 /to 2024-05-12\n";
    }
}

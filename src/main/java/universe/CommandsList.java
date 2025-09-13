package universe;

/**
 * Responsible for printing the help sheet.
 *
 * @author yuqing-tham
 */
public class CommandsList {
    private static final String COMMANDS = "Approved Commands:\n"
            + "> To add a new Chore into the Checklist:\n"
            + "1. todo (chore description)\n"
            + "2. deadline (chore description) by (date and time in D MMM YYYY HHmm format)\n"
            + "3. event (chore description) from (date and time in D MMM YYYY HHmm format)"
            + " to (date and time in D MMM YYYY HHmm format)\n"
            + "> To list all the current Chores in the checklist: list\n"
            + "> To mark a Chore as completed: check (chore number)\n"
            + "> To mark a Chore as not completed: uncheck (chore number)\n"
            + "> To remove a Chore from the Checklist: remove (chore number)\n"
            + "> To find all the Chores due or happening on the same date: filter (date in D MMM YYYY format)\n"
            + "> To find all the Chores with the same keyword in the description: find (keyword)\n"
            + "> To clear all Chores in the Checklist: clear\n"
            + "> For fun: hi";

    /**
     * Prints the list of approved commands.
     */
    public void printCommands() {
        System.out.println(COMMANDS);
    }
}

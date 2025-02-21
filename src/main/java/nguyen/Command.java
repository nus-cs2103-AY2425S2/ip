package nguyen;
/**
 * Class to execute given command line
 *
 */
public class Command {
    private boolean isExit;
    private String item;
    /**
     * Constructor for the Command class.
     * Initializes the command with a string item.
     * The isExit flag is set to false by default.
     *
     * @param item The command input by the user.
     */
    public Command(String item) {
        this.item = item;
        isExit = false;
    }
    /**
     * Executes the command based on the user's input.
     * Different actions are performed based on the command string.
     *
     * @param taskList The list of tasks that will be modified.
     * @param ui The user interface for interacting with the user.
     * @param storage The storage for saving tasks.
     * @throws NguyenException If the command is invalid or there is an error in task handling.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws NguyenException {
        // Exit if the user inputs "bye"
        if (item.equals("bye")) {
            isExit = true;
            return;
        }

        // Displays all tasks in the list
        try {
            if (item.equals("list")) {
                taskList.printList();
            } else if (item.startsWith("delete")) {
                int number = Integer.parseInt(item.substring(7));
                taskList.delete(number);
            } else if (item.startsWith("mark")) {
                int number = Integer.parseInt(item.substring(5));
                taskList.mark(number);
            } else if (item.startsWith("unmark")) {
                int number = Integer.parseInt(item.substring(7));
                taskList.unMark(number);
            } else if (item.startsWith("find")) {
                taskList.find(item.substring(5));
            } else if (item.startsWith("sort")) {
                taskList.sort(item.substring(5));
            } else {
                taskList.handleTask(item);
            }
            storage.saveTask(taskList);
        } catch (NguyenException e) {
            throw e;
        } catch (Exception e) {
            throw new NguyenException("Invalid command");
        }

    }
    /**
     * Returns whether the exit command was given.
     *
     * @return true if the "bye" command was given, otherwise false.
     */
    public boolean isExit() {
        return isExit;
    }
}

package baymax;

import java.io.IOException;

import baymax.command.Command;

/**
 * Represents the main chatbot application, Baymax.
 * Baymax is a task manager that processes user commands
 * and executes corresponding actions such as adding, listing,
 * marking, unmarking, and deleting tasks.
 */
public class Baymax {
    private static final String ERROR_INVALID_COMMAND = "Invalid command. Please try again.\n"
            + "Here are the commands you can use:\n"
            + "1. list                - View all tasks\n"
            + "2. todo <task>        - Add a to-do task\n"
            + "3. deadline <task> /by <yyyy-MM-dd HHmm>  - Add a deadline\n"
            + "4. event <task> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>  - Add an event\n"
            + "5. mark <task number> - Mark a task as completed\n"
            + "6. unmark <task number> - Unmark a completed task\n"
            + "7. delete <task number> - Delete a task\n"
            + "8. find <keyword>    - Search for tasks containing a keyword\n";
    private static final String GREETING_MESSAGE = "Hello! I'm Baymax, your personal emotional companion :)\n"
                    + "How may I help you today?";
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs a Baymax chatbot with a user interface, storage handler,
     * and task list. Loads existing tasks from storage if available.
     * If an error occurs during loading, it initializes with an empty task list.
     */
    public Baymax() {
        storage = new Storage();

        try {
            tasks = storage.loadTasks();
            if (tasks == null) {
                tasks = new TaskList();
            }
        } catch (Exception e) {
            tasks = new TaskList();
        }
    }

    /**
     * Processes user input and returns Baymax's response as a string.
     *
     * @param input The user's input command.
     * @return The chatbot's response as a formatted string.
     */
    public String getResponse(String input) throws IOException {
        try {
            Command command = Parser.parse(input);
            if (command == null) {
                return ERROR_INVALID_COMMAND;
            }
            return command.execute(tasks, storage);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
    /**
     * Returns Baymax's greeting message.
     *
     * @return A greeting message.
     */
    public String getGreeting() {
        return GREETING_MESSAGE;
    }
}

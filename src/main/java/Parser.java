class Parser {
    private final TaskList taskList;
    private final Ui ui;

    /**
     * Constructs a Parser with the given TaskManager and Ui.
     *
     * @param taskList the TaskManager to manage tasks.
     * @param ui the Ui to handle user interactions.
     */
    public Parser(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Parses a user command and executes the corresponding action.
     *
     * @param command the user command to parse.
     * @return true if the chatbot should continue running, false to exit.
     */
    public boolean parseCommand(String command) {
        try {
            // Handle task-related commands: todo, deadline, event
            if (command.startsWith("todo") || command.startsWith("deadline") || command.startsWith("event")) {
                // Split the command into the type and the description
                String[] parts = command.split(" ", 2);  // This will split into [command, description]

                // Check if description is missing or empty
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new EmptyException("Description of a task cannot be empty. Try again");
                }

                // Add task to the list if description is valid
                taskList.addTask(command);
            } else if (command.equalsIgnoreCase("bye")) {
                ui.exitDialogue();
                return false;
            } else if (command.equalsIgnoreCase("list")) {
                taskList.listTasks();
            } else if (command.startsWith("mark ")) {
                handleMarkCommand(command, true);
            } else if (command.startsWith("unmark ")) {
                handleMarkCommand(command, false);
            } else {
                // If the command doesn't match any known commands, throw UnrecognisableException
                throw new UnrecognisableException("I'm sorry, but I don't know what that means.");
            }
        } catch (EmptyException e) {
            ui.echo(e.getMessage()); // Display the error message for empty description
        } catch (UnrecognisableException e) {
            ui.echo(e.getMessage()); // Display the error message for unrecognizable command
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
        return true;
    }


    private void handleMarkCommand(String command, boolean isMark) {
        try {
            int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
            if (isMark) {
                taskList.checkTask(taskIndex);
                ui.printTaskMarked(taskList.getTask(taskIndex));
            } else {
                taskList.uncheckTask(taskIndex);
                ui.printTaskUnmarked(taskList.getTask(taskIndex));
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Invalid task index. Please try again.");
        }
    }
}
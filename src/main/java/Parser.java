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
        if (command.equalsIgnoreCase("bye")) {
            ui.exitDialogue();
            return false;
        } else if (command.equalsIgnoreCase("list")) {
            taskList.listTasks();
        } else if (command.startsWith("mark ")) {
            handleMarkCommand(command, true);
        } else if (command.startsWith("unmark ")) {
            handleMarkCommand(command, false);
        } else {
            taskList.addTask(command);
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
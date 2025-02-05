package scooby.ui;

import scooby.tasks.TaskList;
import scooby.exception.UnrecognisableException;
import scooby.exception.EmptyException;

public class Parser {
    private final TaskList taskList;
    private final Ui ui;

    /**
     * Constructs a Parser with the given TaskManager and Ui.
     *
     * @param taskList the TaskManager to manage tasks.
     * @param ui       the Ui to handle user interactions.
     */
    public Parser(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;

        // Load tasks from the file at startup
        taskList.loadFromFile();
    }

    /**
     * Parses a user command and executes the corresponding action.
     *
     * @param command the user command to parse.
     * @return true if the chatbot should continue running, false to exit.
     */
    public boolean parseCommand(String command) {
        try {
            if (command.startsWith("todo") || command.startsWith("deadline") || command.startsWith("event")) {
                // Handle adding tasks
                String[] parts = command.split(" ", 2);  // This will split into [command, description]

                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new EmptyException("Description of a task cannot be empty. Try again");
                }

                taskList.addTask(command);
                taskList.saveToFile(); // Save after adding a task
            } else if (command.equalsIgnoreCase("bye")) {
                ui.exitDialogue();
                return false;
            } else if (command.equalsIgnoreCase("list")) {
                taskList.listTasks();
            } else if (command.startsWith("mark ")) {
                handleMarkCommand(command, true);
            } else if (command.startsWith("unmark ")) {
                handleMarkCommand(command, false);
            } else if (command.startsWith("delete ")) {
                handleDeleteCommand(command); // Handle delete command
            } else if (command.startsWith("find ")) {
                // Handle find command
                String[] parts = command.split(" ", 2);
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new EmptyException("Keyword for find cannot be empty. Try again.");
                }
                taskList.find(parts[1].trim());
            } else {
                throw new UnrecognisableException("I'm sorry, but I don't know what that means.");
            }
        } catch (EmptyException e) {
            ui.echo(e.getMessage());
        } catch (UnrecognisableException e) {
            ui.echo(e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
        return true;
    }

    /**
     * Parses a user command and executes the corresponding action.
     *
     * @param command the user command to parse.
     * @return the response string to be displayed to the user.
     */
    public String parseCommandReturnString(String command) {
        String response = ""; // Initialize the response variable
        try {
            if (command.startsWith("todo") || command.startsWith("deadline") || command.startsWith("event")) {
                // Handle adding tasks
                String[] parts = command.split(" ", 2);  // This will split into [command, description]

                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new EmptyException("Description of a task cannot be empty. Try again");
                }

                taskList.addTask(command);
                taskList.saveToFile(); // Save after adding a task
            } else if (command.equalsIgnoreCase("bye")) {
                ui.exitDialogue();
                response = "Goodbye!"; // Respond when user says goodbye
            } else if (command.equalsIgnoreCase("list")) {
                return taskList.listTasks();
            } else if (command.startsWith("mark ")) {
                handleMarkCommand(command, true);
            } else if (command.startsWith("unmark ")) {
                handleMarkCommand(command, false);
            } else if (command.startsWith("delete ")) {
                handleDeleteCommand(command); // Handle delete command
            } else if (command.startsWith("find ")) {
                // Handle find command
                String[] parts = command.split(" ", 2);
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new EmptyException("Keyword for find cannot be empty. Try again.");
                }
                taskList.find(parts[1].trim());
            } else {
                throw new UnrecognisableException("I'm sorry, but I don't know what that means.");
            }

        } catch (EmptyException e) {
            response = e.getMessage();  // Return the error message if the description is empty
        } catch (UnrecognisableException e) {
            response = e.getMessage();  // Return the error message if command is not recognized
        } catch (Exception e) {
            response = "An unexpected error occurred: " + e.getMessage(); // Return error message for unexpected errors
        }

        return response; // Return the response to be used elsewhere
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
            taskList.saveToFile(); // Save after marking/unmarking a task
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Invalid task index. Please try again.");
        }
    }

    private void handleDeleteCommand(String command) {
        try {
            int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
            taskList.deleteTask(taskIndex); // Call deleteTask method
            taskList.saveToFile(); // Save after deleting a task
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Invalid task index. Please try again.");
        }
    }
}

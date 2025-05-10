package scooby.ui;

import scooby.tasks.TaskList;
import scooby.exception.UnrecognisableException;
import scooby.exception.EmptyException;

public class Parser {
    private final TaskList taskList;
    private final Ui ui;
    private static final String HELPRESPONSE = new HelpPrompt().getHelp();

    /**
     * Constructs a Parser with the given TaskManager and Ui.
     *
     * @param taskList the TaskManager to manage tasks.
     * @param ui       the Ui to handle user interactions.
     */
    public Parser(TaskList taskList, Ui ui) {
        assert taskList != null : "TaskList instance cannot be null.";
        assert ui != null : "Ui instance cannot be null.";

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
        assert command != null && !command.isEmpty() : "Error: Command cannot be null or empty!";

        try {
            // Extract the first word of the command (the action)
            String[] parts = command.split(" ", 2);
            String action = parts[0].toLowerCase(); // Normalize to lowercase
            String details = (parts.length > 1) ? parts[1].trim() : "";

            return switch (action) {
                case "todo", "deadline", "event" -> handleTaskCreation(action, details);
                case "mark", "unmark", "delete", "update" -> handleTaskModification(action, details);
                case "list", "bye", "find" -> handleGeneralCommand(action, details);
                case "help" -> handleHelpCommand();
                default -> throw new UnrecognisableException("I'm sorry, but I don't know what that means.");
            };
        } catch (EmptyException | UnrecognisableException e) {
            return e.getMessage(); // Return known exception messages
        } catch (Exception e) {
            return "An unexpected error occurred: " + e.getMessage(); // Handle other errors
        }
    }

    private String handleHelpCommand() {
        return HELPRESPONSE;
    }

    /**
     * Handles task creation commands (`todo`, `deadline`, `event`).
     */
    private String handleTaskCreation(String action, String details) throws EmptyException {
        if (details.isEmpty()) {
            throw new EmptyException("Description of a task cannot be empty. Try again.");
        }
        String response = taskList.addTask(action + " " + details);
        taskList.saveToFile(); // Save after adding a task
        return response;
    }

    /**
     * Handles task modification commands (`mark`, `unmark`, `delete`).
     */
    private String handleTaskModification(String action, String details) {
        return switch (action) {
            case "mark" -> handleMarkCommand("mark " + details, true);
            case "unmark" -> handleMarkCommand("unmark " + details, false);
            case "delete" -> handleDeleteCommand("delete " + details);
            case "update" -> handleUpdateCommand("update" + details);
            default -> throw new IllegalArgumentException("Unknown modification command: " + action);
        };
    }

    /**
     * Handles general commands (`list`, `bye`, `find`).
     */
    private String handleGeneralCommand(String action, String details) throws EmptyException {
        return switch (action) {
            case "bye" -> ui.exitDialogue();
            case "list" -> taskList.listTasks();
            case "find" -> {
                if (details.isEmpty()) {
                    throw new EmptyException("Keyword for find cannot be empty. Try again.");
                }
                yield taskList.find(details);
            }
            default -> throw new IllegalArgumentException("Unknown general command: " + action);
        };
    }

    /**
     * Handles the update command by extracting the task index and new details.
     *
     * @param command The update command in the format "update <taskIndex> <newDetails>"
     * @return The response string after updating the task.
     */
    private String handleUpdateCommand(String command) {
        try {
            // Extract task index and new details
            String[] parts = command.split(" ", 2);
            if (parts.length < 2) {
                return "Error: Please specify the task number and new details.";
            }

            int taskIndex = Integer.parseInt(parts[0].replace("update", "").trim()) - 1;
            String newDetails = parts[1].trim();

            return taskList.updateTask(taskIndex, newDetails);
        } catch (NumberFormatException e) {
            return "Error: Invalid task number. Please enter a valid number.";
        }
    }

    private String handleMarkCommand(String command, boolean isMark) {
        assert command != null : "Description of Event task cannot be null.";
        try {
            int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
            if (isMark) {
                taskList.checkTask(taskIndex);
                taskList.saveToFile();
                return ui.returnMarkedString(taskList.getTask(taskIndex));
            } else {
                taskList.uncheckTask(taskIndex);
                taskList.saveToFile();
                return ui.returnUnmarkedString(taskList.getTask(taskIndex));
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "Invalid task index. Please try again.";
        }
    }

    private String handleDeleteCommand(String command) {
        assert command != null : "Description of Event task cannot be null.";
        String response = "";
        try {
            int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
            response += taskList.deleteTask(taskIndex); // Call deleteTask method
            taskList.saveToFile(); // Save after deleting a task
            return response; // returns the response
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "Invalid task index. Please try again.";
        }
    }
}

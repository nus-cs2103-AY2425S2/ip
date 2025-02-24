package chitti;

import java.util.ArrayList;
/**
 * Handles the parsing and execution of user commands.
 * This class processes the user's input, manages task creation, task marking, task deletion,
 * and interacts with the TaskList and UI components to provide the appropriate responses.
 */
public class Parser {
    /**
     * Handles different commands input by the user and returns a response.
     * The method interprets the command, manipulates the TaskList as needed,
     * and uses the Ui to generate a response.
     *
     * @param input The user's input as a string.
     * @param taskList The list of tasks to interact with.
     * @param ui The UI instance for generating responses.
     * @return A string containing the response based on the user's input.
     */
    String handleCommand(String input, TaskList taskList, Ui ui) {
        assert input != null : "Input should not be null";

        // Extract the command (first word of input)
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String args = parts.length > 1 ? parts[1] : "";

        switch (command) {
            case "list":
                return ui.getTaskListString(taskList.getTasks());

            case "mark":
                return handleMark(args, taskList, ui, true);

            case "unmark":
                return handleMark(args, taskList, ui, false);

            case "delete":
                return handleDelete(args, taskList, ui);

            case "find":
                return ui.getFoundListString(taskList.findTasks(args));

            case "deadline":
            case "event":
            case "todo":
                return handleTaskCreation(input, taskList, ui);

            default:
                return "I'm sorry, but I don't understand that command!";
        }
    }

    /**
     * Marks or unmarks a task based on the command.
     * This method parses the task index and updates its completion status.
     *
     * @param args The task index to mark or unmark.
     * @param taskList The list of tasks.
     * @param ui The UI instance to generate responses.
     * @param isMark A boolean indicating whether to mark or unmark the task.
     * @return A response message after attempting to mark/unmark the task.
     */
    private String handleMark(String args, TaskList taskList, Ui ui, boolean isMark) {
        try {
            int index = Integer.parseInt(args);
            Task task = taskList.getTask(index);
            if (isMark) {
                task.doTask();
                return ui.getTaskMarkedString(task);
            } else {
                task.undoTask();
                return ui.getTaskUnmarkedString(task);
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "Invalid task number. Please enter a valid index.";
        }
    }

    /**
     * Handles task deletion.
     * This method parses the task index and removes the corresponding task from the list.
     *
     * @param args The task index to delete.
     * @param taskList The list of tasks.
     * @param ui The UI instance to generate responses.
     * @return A response message after attempting to delete the task.
     */
    private String handleDelete(String args, TaskList taskList, Ui ui) {
        try {
            int index = Integer.parseInt(args);
            Task toRemove = taskList.getTask(index);
            taskList.deleteTask(index);
            return ui.getTaskDeletedString(toRemove, taskList.size());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "Invalid task number. Please enter a valid index.";
        }
    }

    /**
     * Handles the creation of tasks based on the command input.
     * This method determines the type of task (deadline, event, or todo)
     * and creates a corresponding task instance.
     *
     * @param input The full user input including the task type and arguments.
     * @param taskList The list of tasks to add the new task to.
     * @param ui The UI instance to generate responses.
     * @return A response message after attempting to add the new task.
     */
    private String handleTaskCreation(String input, TaskList taskList, Ui ui) {
        try {
            Task newTask = parseTask(input);
            taskList.addTask(newTask);
            return ui.getTaskAddedString(newTask, taskList.size());
        } catch (UnknownMessageException | InvalidTaskException e) {
            return e.toString();
        }
    }

    /**
     * Parses the input string and creates a corresponding task.
     * This method supports creating deadline tasks, event tasks, and todo tasks.
     *
     * @param input The user's input string specifying the task type and its arguments.
     * @return A Task object representing the task to be added.
     * @throws InvalidTaskException if the input is invalid for task creation.
     * @throws UnknownMessageException if the input does not match any known task types.
     */
    private Task parseTask(String input) throws InvalidTaskException, UnknownMessageException {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String args = parts.length > 1 ? parts[1] : "";

        switch (command) {
            case "deadline":
                int ind = args.indexOf("/");
                if (ind == -1 || ind == args.length() - 1) throw new InvalidTaskException();
                String name = args.substring(0, ind - 1);
                String deadline = args.substring(ind + 4);
                return new DeadlineTask(name, deadline);

            case "event":
                int firstInd = args.indexOf("/");
                int secondInd = args.lastIndexOf("/");
                if (firstInd == -1 || firstInd == secondInd || secondInd == args.length() - 1)
                    throw new InvalidTaskException();
                String eventName = args.substring(0, firstInd - 1);
                String start = args.substring(firstInd + 6, secondInd - 1);
                String end = args.substring(secondInd + 4);
                return new EventTask(eventName, start, end);

            case "todo":
                if (args.isEmpty()) throw new InvalidTaskException();
                return new ToDoTask(args);

            default:
                throw new UnknownMessageException();
        }
    }
}

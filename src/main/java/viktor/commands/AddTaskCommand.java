package viktor.commands;

import java.io.IOException;
import java.util.Random;

import viktor.exceptions.ViktorException;
import viktor.storage.Storage;
import viktor.tasks.DeadlineTask;
import viktor.tasks.EventTask;
import viktor.tasks.TaskList;
import viktor.tasks.TaskType;
import viktor.tasks.TodoTask;

/**
 * Command to add a task to the task list based on user input.
 */
public class AddTaskCommand implements Commandable {
    private static final String[] responses = {
        "The pursuit of knowledge is a noble endeavor. I've added",
        "Progress requires sacrifice, but it is always worth the cost for",
        "Even the greatest minds require assistance sometimes. You seek to",
        "Ah, progress—the double-edged sword of innovation, alongside:",
        "Every leap forward brings challenges, but such is the price of this great task,"
    };
    private String userInput;
    private TaskList taskList;
    private boolean isBeingTested;
    private final Random random = new Random();
    private String testingResponse = ("Even the greatest minds require assistance sometimes. You seek to");

    /**
     * Constructs the AddTaskCommand with user input, task list, and testing mode.
     *
     * @param userInput The user's command input.
     * @param taskList The list of tasks.
     * @param testing Whether the command is in testing mode.
     */
    public AddTaskCommand(String userInput, TaskList taskList, boolean isBeingTested) {
        this.userInput = userInput;
        this.taskList = taskList;
        this.isBeingTested = isBeingTested;
    }

    /**
     * Executes the command to add a task to the task list based on user input.
     * Creates a Todo, DeadlineTask, or Event task and adds it to the task list, depending on user input.
     * Prints a randomised response to the user, echoing the task description.
     * During testing, responses are fixed.
     * Saves saves the task list after the task is added.
     * @throws ViktorException If the input is invalid or there is an error with task creation.
     */
    @Override
    public String execute() throws ViktorException {
        String[] words = userInput.split(" ");
        TaskType taskType = TaskType.valueOf(words[0].toUpperCase());
        String description;
        String dueDate;
        String fromDate;
        String toDate;

        // Throws an exception if user inputs "add" without a event type, description (and more)
        if (words.length < 2) {
            throw new ViktorException("Tell me more about your task!");
        }

        // Extracts the description from the user input
        String taskDescription = userInput.substring(words[0].length()).trim();
        String output = "";

        switch (taskType) {
        case TODO:
            TodoTask todo = new TodoTask(taskDescription);
            taskList.addTask(todo);
            output = todo.getDescription();
            break;

        case DEADLINE:
            String[] parts = taskDescription.split("/by", 2);
            if (parts.length < 2) {
                throw new ViktorException("Invalid deadline format! Use: deadline <description> /by <due date>");
            }
            description = parts[0].trim();
            dueDate = parts[1].trim();
            if (description.isEmpty()) {
                throw new ViktorException("Please provide a description of this deadline task!");
            } else if (dueDate.isEmpty()) {
                throw new ViktorException("When's the deadline? Please focus, when is it due!");
            }
            DeadlineTask deadline = new DeadlineTask(description, dueDate);
            taskList.addTask(deadline);
            output = deadline.getDescription();
            break;

        case EVENT:
            String[] eventParts = taskDescription.split("/from", 2);
            if (eventParts.length < 2) {
                throw new ViktorException("Invalid event format! Use: event <description> /from <start> /to <end>");
            }
            description = eventParts[0].trim();
            if (description.isEmpty()) {
                throw new ViktorException("You have to tell me what the event is!");
            }
            String[] timeParts = eventParts[1].trim().split("/to", 2);
            if (timeParts.length < 2) {
                throw new ViktorException("Invalid event format! Use: event <description> /from <start> /to <end>");
            }
            fromDate = timeParts[0].trim();
            toDate = timeParts[1].trim();
            if (fromDate.isEmpty()) {
                throw new ViktorException("You have to tell me when this event starts.");
            } else if (toDate.isEmpty()) {
                throw new ViktorException("You have to tell me when this event ends.");
            }

            EventTask event = new EventTask(description, fromDate, toDate);
            taskList.addTask(event);
            output = event.getDescription();
            break;

        default:
            throw new ViktorException("Unknown command type.");
        }

        String response = isBeingTested ? testingResponse : responses[random.nextInt(responses.length)];
        try {
            Storage.save(taskList);
        } catch (IOException e) {
            return "An error occurred while saving tasks: " + e.getMessage();
        }

        return response + " " + output + ".";
    }

}

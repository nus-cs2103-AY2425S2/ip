package hamiltonianguy;

import hamiltonianguy.tasks.DeadlineTask;
import hamiltonianguy.tasks.ToDoTask;
import hamiltonianguy.tasks.Task;
import hamiltonianguy.tasks.EventTask;
import hamiltonianguy.storage.Storage;


public class HamiltonianGuy {

    private TaskList taskList;

    public HamiltonianGuy() {
        Storage storage = new Storage("./data/HamiltonianGuy.txt");
        taskList = new TaskList(storage);
    }

    /**
     * Processes user input and returns a chatbot response.
     *
     * @param input The user's command.
     * @return The chatbot's response.
     */
    public String getResponse(String input) {
        assert input != null && !input.trim().isEmpty() : "Input command cannot be null or empty";
        try {
            if (input.equals("bye")) {

                return "Bye. Hope to see you again!";
            } else if (input.equals("list")) {
                return taskList.isEmpty() ? "OOPS!! The list is empty." : taskList.getTaskListAsString();
            } else if (input.startsWith("mark")) {
                return handleMarkCommand(input);
            } else if (input.startsWith("unmark")) {
                return handleUnmarkCommand(input);
            } else if (input.startsWith("todo")) {
                return handleTodoCommand(input);
            } else if (input.startsWith("deadline")) {
                return handleDeadlineCommand(input);
            } else if (input.startsWith("event")) {
                return handleEventCommand(input);
            } else if (input.startsWith("delete")) {
                return handleDeleteCommand(input);
            } else if (input.startsWith("find ")) {
                String keyword = input.substring(5).trim();
                return taskList.findTasks(keyword);
            } if (input.equals("sort")) {
                taskList.sortTasksByDeadline();
                return "Tasks sorted by deadline.";
            } else {
                return "OOPS!!! I'm sorry, but I don't know what that means :-(";
            }
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "OOPS!!! Something went wrong: " + e.getMessage();
        }
    }

    // Modify existing command handlers to return responses as Strings
    private String handleMarkCommand(String input) {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        taskList.markTask(index);
        return "Nice! I've marked this task as done.";
    }

    private String handleUnmarkCommand(String input) {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        taskList.unmarkTask(index);
        return "OK, I've marked this task as not done yet.";
    }

    private String handleTodoCommand(String input) {
        String description = input.substring(4).trim();
        if (description.isEmpty()) {
            throw new IllegalArgumentException("OOPS!!! The description of a todo cannot be empty.");
        }
        taskList.addTask(new ToDoTask(description));
        return "Added: " + description;
    }

    private String handleDeadlineCommand(String input) {
        String[] parts = input.substring(8).split(" /by ");
        if (parts.length < 2) {
            throw new IllegalArgumentException("OOPS!!! Please provide a description and a deadline.");
        }
        taskList.addTask(new DeadlineTask(parts[0].trim(), parts[1].trim()));
        return "Added deadline task: " + parts[0].trim();
    }

    private String handleEventCommand(String input) {
        String[] parts = input.substring(5).split(" /from ");
        if (parts.length < 2) {
            throw new IllegalArgumentException("OOPS!!! Please provide a description and event times.");
        }
        String[] timeParts = parts[1].split(" /to ");
        if (timeParts.length < 2) {
            throw new IllegalArgumentException("OOPS!!! Please specify both start and end times.");
        }
        taskList.addTask(new EventTask(parts[0].trim(), timeParts[0].trim(), timeParts[1].trim()));
        return "Added event task: " + parts[0].trim();
    }

    private String handleDeleteCommand(String input) {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        taskList.deleteTask(index);
        return "Task deleted.";
    }
}

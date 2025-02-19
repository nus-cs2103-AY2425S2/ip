package teddy;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;
    private final Storage storage;

    public TaskList(Storage storage) {
        this.tasks = new ArrayList<>();
        this.storage = storage;
    }

    // Add TodoTask to TaskList
    public String addTodo(String[] parts) throws TeddyException {
        if (parts.length <= 1 || parts[1].isBlank()) {
            throw new TeddyException("The description of a todo cannot be empty.");
        }
        Todo todo = new Todo(parts[1]);

        // Check for duplicates
        if (tasks.contains(todo)) {
            return "This task already exists in your list!";
        }

        tasks.add(todo);

        try {
            this.storage.writeToFile(todo.toString());
        } catch (IOException e) {
            return "Something went wrong: " + e.getMessage();
        }
        return "Got it, I've added this task:\n  " + todo + "\nNow you have " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task") + " in the list.";
    }

    // Add Deadline to TaskList
    public String addDeadline(String[] parts) throws TeddyException {
        Deadline deadline = getDeadline(parts);

        // Check for duplicates
        if (tasks.contains(deadline)) {
            return "This task already exists in your list!";
        }

        tasks.add(deadline);

        try {
            storage.writeToFile(deadline.toString());
        } catch (IOException e) {
            return "Something went wrong: " + e.getMessage();
        }
        return "Got it, I've added this task:\n  " + deadline + "\nNow you have " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task") + " in the list.";
    }

    private Deadline getDeadline(String[] parts) throws TeddyException {
        if (parts.length <= 1 || parts[1].isBlank()) {
            throw new TeddyException("The description of a deadline cannot be empty.");
        }
        if (!parts[1].contains("/by")) {
            throw new TeddyException("A deadline must have a '/by' followed by the time.");
        }
        String[] split = parts[1].split("/by", 2);
        if (split.length < 2 || split[1].isBlank()) {
            throw new TeddyException("The time for a deadline cannot be empty.");
        }
        return new Deadline(split[0].trim(), split[1].trim());
    }

    public String addEvent(String input) throws TeddyException {
        if (input == null || input.isBlank()) {
            throw new TeddyException("The description of an event cannot be empty.");
        }
        Event event = getEvent(input);

        // Check for duplicates
        if (tasks.contains(event)) {
            return "This task already exists in your list!";
        }

        tasks.add(event);

        try {
            storage.writeToFile(event.toString());
        } catch (IOException e) {
            return "Something went wrong: " + e.getMessage();
        }

        return "Got it, I've added this task:\n  " + event
                + "\nNow you have " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task") + " in the list.";
    }

    private Event getEvent(String input) throws TeddyException {
        assert input.contains("/from") && input.contains("/to") : "Event input must contain '/from' and '/to'";

        String[] split = input.split("/");
        assert split.length >= 3 : "Incorrect event format";

        String task = split[0].trim();
        String start = split[1].split(" ", 2)[1].trim();
        String end = split[2].split(" ", 2)[1].trim();
        return new Event(task, start, end);
    }


    // Delete Task from TaskList
    public String deleteTask(String[] parts) throws TeddyException {
        if (parts.length <= 1 || parts[1].isBlank()) {
            throw new TeddyException("Please specify the task number to delete.");
        }
        try {
            int index = Integer.parseInt(parts[1]);
            if (index < 1 || index > tasks.size()) {
                throw new TeddyException("Task number " + index + " does not exist in the list.");
            }
            Task removedTask = tasks.remove(index - 1);
            return "Noted. I've removed this task:\n  " + removedTask + "\nNow you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list.";
        } catch (NumberFormatException e) {
            throw new TeddyException("Task number must be a valid integer.");
        }
    }

    // Mark Task as done in TaskList
    public String markTask(String[] parts) throws TeddyException {
        assert parts.length > 1 : "Parts array should have at least 2 elements (command + index)";

        try {
            int index = Integer.parseInt(parts[1]);
            tasks.get(index - 1).mark();
            return "Nice! I've marked this task as done:\n   " + tasks.get(index - 1).toString();
        } catch (IndexOutOfBoundsException e) {
            throw new TeddyException("Task number " + parts[1] + " does not exist.");
        }
    }

    // Mark task as not done in TaskList
    public String unmarkTask(String[] parts) throws TeddyException {
        if (parts.length <= 1 || parts[1].isBlank()) {
            throw new TeddyException("Please specify the task number to be marked as not done.");
        }
        try {
            int index = Integer.parseInt(parts[1]);
            tasks.get(index - 1).unmark();
            return "OK, I've marked this task as not done yet:\n   " + tasks.get(index - 1).toString();
        } catch (IndexOutOfBoundsException e) {
            throw new TeddyException("Task number " + parts[1] + " does not exist.");
        } catch (NumberFormatException e) {
            throw new TeddyException("Task number must be a valid integer.");
        }
    }

    // Find a task in TaskList
    public String find(String input) throws TeddyException {
        if (input == null || input.isBlank()) {
            throw new TeddyException("The search query cannot be empty.");
        }
        StringBuilder result = new StringBuilder("Here are the matching tasks in your list:\n");
        int count = 1;
        boolean found = false;

        for (Task task : this.tasks) {
            if (task.toString().toLowerCase().contains(input.toLowerCase())) {
                result.append(count).append(". ").append(task).append("\n");
                count++;
                found = true;
            }
        }
        return found ? result.toString() : "No matching tasks found for: " + input;
    }

    // Print tasks in TaskList
    public String printTasks() {
        StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            result.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return result.toString();
    }
}

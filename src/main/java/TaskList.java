import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>(); // Dynamic list to hold tasks

    /**
     * Adds a task to the list
     *
     * @param command the name of the chatbot.
     */
    public void addTask(String command) {
        Task newTask;
        try {
            if (command.startsWith("todo ")) {
                String description = command.substring(5).trim();
                if (description.isEmpty()) {
                    throw new EmptyException("Description of a task cannot be empty. Try again");
                }
                newTask = new ToDo(description);
            } else if (command.startsWith("deadline ")) {
                String[] parts = command.substring(9).split(" /by ");
                String description = parts[0].trim();
                if (description.isEmpty()) {
                    throw new EmptyException("Description of a task cannot be empty. Try again");
                }
                String by = parts[1];
                newTask = new Deadline(description, by);
            } else if (command.startsWith("event ")) {
                String[] parts = command.substring(6).split(" /from | /to ");
                String description = parts[0].trim();
                if (description.isEmpty()) {
                    throw new EmptyException("Description of a task cannot be empty. Try again");
                }
                String from = parts[1];
                String to = parts[2];
                newTask = new Event(description, from, to);
            } else {
                throw new UnrecognisableException("I'm sorry, but I don't know what that means.");
            }

            tasks.add(newTask); // Add task to ArrayList
            System.out.println("____________________________________________________________");
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + newTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");

        } catch (EmptyException | UnrecognisableException e) {
            System.out.println("____________________________________________________________");
            System.out.println(e.getMessage());
            System.out.println("____________________________________________________________");
        }
    }

    /**
     * Lists down all the tasks in the list.
     */
    public void listTasks() {
        System.out.println("____________________________________________________________");
        if (tasks.size() == 0) {
            System.out.println("No tasks in the list.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Gets a task from the task list
     *
     * @param index is the index of the task from the task list.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Marks a task as completed.
     *
     * @param index is the index of the task from the task list.
     */
    public void checkTask(int index) {
        tasks.get(index).setChecked();
    }

    /**
     * Unmarks a task from the task list.
     *
     * @param index is the index of the task from the task list.
     */
    public void uncheckTask(int index) {
        tasks.get(index).setUnchecked();
    }

    /**
     * Deletes a task from the list
     *
     * @param index is the index of the task from the task list.
     */
    public void deleteTask(int index) {
        // Ensure index is within bounds
        if (index >= 0 && index < tasks.size()) {
            Task deletedTask = tasks.get(index);
            tasks.remove(index); // Remove task from ArrayList
            System.out.println("____________________________________________________________");
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + deletedTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("Invalid task index. Please try again.");
        }
    }
}

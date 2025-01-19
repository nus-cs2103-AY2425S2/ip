public class TaskList {
    private Task[] tasks = new Task[100]; // Fixed-size array to hold tasks
    private int taskCount = 0; // Track the number of tasks

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

            if (taskCount < tasks.length) {
                tasks[taskCount] = newTask;
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + newTask);
                System.out.println("Now you have " + taskCount + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("____________________________________________________________");
                System.out.println("Task list is full. Cannot add more tasks.");
                System.out.println("____________________________________________________________");
            }
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
        if (taskCount == 0) {
            System.out.println("No tasks in the list.");
        } else {
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + tasks[i]);
            }
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * gets a task from the task list
     *
     * @param index is the index of the task from the task list.
     */
    public Task getTask(int index) {
        return this.tasks[index];
    }

    /**
     * checks a task from the task list
     *
     * @param index is the index of the task from the task list to be checked.
     */
    public void checkTask(int index) {
        this.tasks[index].setChecked();
    }

    /**
     * unchecks a task from the task list
     *
     * @param index is the index of the task from the task list to be unchecked.
     */
    public void uncheckTask(int index) {
        this.tasks[index].setUnchecked();
    }
}

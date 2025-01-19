public class TaskList {
    private String[] tasks = new String[100]; // Fixed-size array to hold tasks
    private int taskCount = 0; // Track the number of tasks

    /**
     * Adds a task to the list
     *
     * @param task the name of the chatbot.
     */
    public void addTask(String task) {
        if (taskCount < tasks.length) {
            tasks[taskCount] = task;
            taskCount++;
            System.out.println("____________________________________________________________");
            System.out.println("added: " + task);
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println("Task list is full. Cannot add more tasks.");
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
}

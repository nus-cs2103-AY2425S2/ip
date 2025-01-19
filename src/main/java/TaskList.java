public class TaskList {
    private Task[] tasks = new Task[100]; // Fixed-size array to hold tasks
    private int taskCount = 0; // Track the number of tasks

    /**
     * Adds a task to the list
     *
     * @param task the name of the chatbot.
     */
    public void addTask(String task) {
        if (taskCount < tasks.length) {
            tasks[taskCount] = new Task(task);
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

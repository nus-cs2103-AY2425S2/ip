package pluto;

import java.util.List;

/**
 * Represents a list of Tasks. This class handles the
 * different tasks in the list, with methods such as
 * adding, removing, and listing tasks
 */
public class TaskList {
    private List<Task> taskList;
    private Message message;

    /**
     * Creates a new TaskList, a list that contains
     * all the current tasks the user has inputted
     * @param tasks a List of Tasks
     */
    public TaskList(List<Task> tasks) {
        this.taskList = tasks;
        this.message = new Message();
    }

    /**
     * Adds the task to the task list
     * @param task the Task to be added to the list
     */
    public String addTask(Task task) {
        assert task != null : "Task should not be null";
        this.taskList.add(task);
        return message.addTaskMessage(task, taskList.size());
    }

    /**
     * Lists all the tasks currently in the task list
     */
    public String listTasks() {
        String response = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            int index = i + 1;
            response += message.listTaskMessage(taskList.get(i), index);
        }
        return response;
    }

    /**
     * Marks the specified task as completed
     * @param taskIndex the index of the specified task in the list
     */
    public String markTask(int taskIndex) {
        assert taskIndex >= 0 && taskIndex < taskList.size()
                : "Invalid task index for marking";
        Task t = taskList.get(taskIndex);
        t.markAsDone();
        return message.markTaskMessage(t);
    }

    /**
     * Marks the specified task as not completed
     * @param taskIndex the index of the specified task in the list
     */
    public String unmarkTask(int taskIndex) {
        assert taskIndex >= 0 && taskIndex < taskList.size()
                : "Invalid task index for unmarking";
        Task t = taskList.get(taskIndex);
        t.markAsUndone();
        return message.unmarkTaskMessage(t);
    }

    /**
     * Removes the specified task from the task list
     * @param taskIndex the index of the specified task in the list
     * @throws PlutoException if the task index is out of bounds
     */
    public String removeTask(int taskIndex) throws PlutoException {
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new PlutoException("Task number is out of range.");
        }
        Task removedTask = taskList.remove(taskIndex);
        return message.removeTaskMessage(removedTask, taskList.size());
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Finds tasks with matching keyword and
     * displays it to the user
     * @param keyword a String that describes the specific keyword
     */
    public String findTasks(String keyword) {
        String response = "Here are the matching tasks in your list:\n";
        int count = 1;

        for (Task task : taskList) {
            if (task.isDescriptionMatching(keyword)) {
                response += message.listTaskMessage(task, count);
                count++;
            }
        }
        return response;
    }

    public String scheduleTasks(String date) {
        String response = "Here are the tasks scheduled:\n";
        int count = 1;

        for (Task task : taskList) {
            if (task.isScheduledFor(date)) {
                response += message.listTaskMessage(task, count);
                count++;
            }
        }
        return response;
    }

}

package charlie;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;


/**
 * Represents a list of tasks that can be added, deleted, marked, unmarked, and listed.
 * This class manages tasks by interacting with the Storage class to persist data in a file.
 */
class TaskList {
    private final ArrayList<Task> tasks;
    private final Storage storage;

    public TaskList(Storage storage) {
        assert storage != null : "Storage must not be null";
        this.storage = storage;
        this.tasks = storage.loadTasks();
    }

    /**
     * Adds a new task to the list and saves the updated list to the storage file.
     *
     * @param task The task to be added to the list.
     * @return A string containing the task added confirmation message.
     */
    public String addTask(Task task) {
        assert task != null : "Task cannot be null";
        tasks.add(task);
        storage.saveTasks(tasks);
        return "Got it. I've added this task:\n" + task + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Deletes a task from the list by its index and saves the updated list to the storage file.
     *
     * @param index The index of the task to be deleted (1-based).
     * @return A string containing the task removed confirmation message.
     */
    public String deleteTask(int index) {
        if (index >= 1 && index <= tasks.size()) {
            Task removedTask = tasks.remove(index - 1);
            storage.saveTasks(tasks);
            return "Noted. I've removed this task:\n  "
                    + removedTask + "\nNow you have " + tasks.size() + " tasks in the list.";
        } else {
            return "Invalid task number.";
        }
    }

    /**
     * Marks a task as completed based on its index and saves the updated list to the storage file.
     *
     * @param index The index of the task to be marked as completed (1-based).
     * @return A string containing the task marked as done confirmation message.
     */
    public String markTask(int index) {
        if (index >= 1 && index <= tasks.size()) {
            tasks.get(index - 1).mark();
            storage.saveTasks(tasks);
            return "Nice! I've marked this task as done:\n" + tasks.get(index - 1);
        } else {
            return "Invalid task number.";
        }
    }

    /**
     * Unmarks a task, indicating it is not completed yet, based on its index
     * and saves the updated list to the storage file.
     *
     * @param index The index of the task to be unmarked (1-based).
     * @return A string containing the task unmarked confirmation message.
     */
    public String unmarkTask(int index) {
        if (index >= 1 && index <= tasks.size()) {
            tasks.get(index - 1).unmark();
            storage.saveTasks(tasks);
            return "OK, I've marked this task as not done yet:\n" + tasks.get(index - 1);
        } else {
            return "Invalid task number.";
        }
    }

    /**
     * Lists all the tasks currently in the list.
     * If no tasks exist, a message is shown indicating that the list is empty.
     *
     * @return A string containing the list of tasks or an empty list message.
     */
    public String listTasks() {
        if (tasks.isEmpty()) {
            return "There are no tasks, please feel free to add more";
        } else {
            StringBuilder taskList = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                taskList.append((i + 1) + ". " + tasks.get(i) + "\n");
            }
            return taskList.toString();
        }
    }

    /**
     * Finds tasks that contain the given task name.
     *
     * @param taskName The task name to search for.
     * @return A string containing the matching tasks.
     */
    public String findTask(String query) {
        PriorityQueue<TaskEntry> pq = new PriorityQueue<>(Comparator.comparingInt(TaskEntry::getScore).reversed());

        for (int i = 0; i < tasks.size(); i++) {
            int score = tasks.get(i).getMatchScore(query);
            if (score > 0) { // Only add relevant matches
                pq.offer(new TaskEntry(i + 1, tasks.get(i), score));
            }
        }

        if (pq.isEmpty()) {
            return "No matching tasks found.";
        }

        StringBuilder foundTasks = new StringBuilder();
        while (!pq.isEmpty()) {
            TaskEntry entry = pq.poll();
            foundTasks.append(entry.index).append(". ").append(entry.task).append("\n");
        }
        return foundTasks.toString();
    }

    private static class TaskEntry {
        private int index;
        private Task task;
        private int score;

        TaskEntry(int index, Task task, int score) {
            this.index = index;
            this.task = task;
            this.score = score;
        }

        public int getScore() {
            return score;
        }
    }
}

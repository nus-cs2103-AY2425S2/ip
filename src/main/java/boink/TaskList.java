package boink;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import boink.exceptions.InvalidIndexException;
import boink.tasks.Task;

/**
 * This class represents a list containing user's tasks.
 */

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for task list.
     */

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Loads task into tasklist.
     * @param task Task to load.
     */
    public void loadTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Adds task into tasklist and returns output to print.
     * @param task Task to add.
     * @return Print output for added task.
     */

    public String addTask(Task task) {
        assert (task != null) : "Task should not be null!";
        this.tasks.add(task);
        this.saveTasks();
        return "Got it. I've added this task:\n" + task + "\n"
                + "Now you have " + this.tasks.size() + " tasks in the list! \n";
    }

    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Marks task done and returns print output.
     * @param index
     * @return Print output for marked task.
     */

    public String markTask(int index) throws InvalidIndexException {
        validateIndex(index);
        tasks.get(index).setDone();
        this.saveTasks();
        return "Nice! I've marked this task as done: \n" + tasks.get(index);
    }

    /**
     * Unmark task as not done and returns print output.
     * @param index
     * @return Print output for unmarked task.
     */

    public String unmarkTask(int index) throws InvalidIndexException {
        validateIndex(index);
        this.tasks.get(index).setNotDone();
        this.saveTasks();
        return "OK, I've marked this task as not done yet: \n" + tasks.get(index);
    }

    /**
     * Delete task and returns print output.
     * @param index
     * @return Print output for deleted task.
     */

    public String deleteTask(int index) throws InvalidIndexException {
        validateIndex(index);
        Task removedTask = this.tasks.get(index);
        this.tasks.remove(index);
        this.saveTasks();
        return "OK, I've removed this task from the list: \n" + removedTask;
    }

    /**
     * Filters current tasklist for tasks containing keyword.
     * @param word Keyword to find in task.
     * @return Print output for filtered list of tasks containing keyword.
     */

    public String findWord(String word) {
        List<Task> filteredTasks = tasks.stream()
                .filter(task -> task.hasWord(word))
                .collect(Collectors.toList());

        List<String> output = IntStream.range(0, filteredTasks.size())
                .mapToObj(i -> ((i + 1) + "." + filteredTasks.get(i) + "\n"))
                .collect(Collectors.toList());

        return "Here are the matching tasks in your list: \n" + String.join("", output);
    }

    /**
     * Create output for printing lists of tasks.
     * @return Print output for list of tasks.
     */

    public String printTasks() {
        if (tasks.isEmpty()) {
            return "There are no tasks in the list currently. Please add a task!";
        } else {
            List<String> output = IntStream.range(0, this.tasks.size()).mapToObj(
                            i -> ((i + 1) + "." + this.tasks.get(i) + "\n"))
                    .collect(Collectors.toList());
            return "Here are the tasks in your list: \n" + String.join("", output);
        }
    }

    /**
     * Returns tasks from TaskList as string to save into file.
     * @return String output to save
     */

    public String saveTasks() {
        StringBuilder sb = new StringBuilder();
        for (Task task: tasks) {
            sb.append(task.saveTask() + "\n");
        }
        return sb.toString();
    }

    /**
     * Returns a message indicating the result of archiving tasks
     * and updating the task list to a new, empty state.
     * @return String message indicating the status of task archiving.
     */

    public String archiveAllTasks() {
        if (tasks.isEmpty()) {
            return "There are no tasks to archive. Please add a task!";
        } else {
            String output = String.format("Archived %d tasks successfully!", this.tasks.size());
            this.tasks = new ArrayList<>();
            return output;
        }
    }

    /**
     * Receives index and checks if index points to existing task.
     * @param index Index.
     * @throws InvalidIndexException If index is out of tasklist range.
     */

    private void validateIndex(int index) throws InvalidIndexException {
        assert (index >= 0 && index < tasks.size()) : "Index must point to an existing task!";
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidIndexException("Index does not match to a task!");
        }
    }
}

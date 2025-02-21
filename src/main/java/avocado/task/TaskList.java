package avocado.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;    
    private final Map<String, List<Task>> tagMap;


    /**
     * Constructs a TaskList object with an empty list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        assert tasks != null : "Tasks should not be null";
        this.tagMap = new HashMap<>();
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        assert tasks.contains(task) : "Task should be added to the list of tasks";
    }   

    /**
     * Deletes a task from the list of tasks.
     *
     * @param index The index of the task to be deleted.
     * @return The task that was deleted.
     */
    public Task deleteTask(int index) {
        Task task = tasks.remove(index);
        assert !tasks.contains(tasks.get(index)) : "Task should be deleted from the list of tasks";
        return task;
    }   

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked as done.
     */
    public boolean markTaskAsDone(int index) {
        if (index < 0 || index >= tasks.size()) {
            return false;
        }
        tasks.get(index).markAsDone();
        assert tasks.get(index).isDone() : "Task should be marked as done";
        return true;
    }

    /**
     * Marks a task as not done.
     *
     * @param index The index of the task to be marked as not done.
     */
    public boolean markTaskAsNotDone(int index) {
        if (index < 0 || index >= tasks.size()) {
            return false;
        }
        tasks.get(index).markAsNotDone();
        assert !tasks.get(index).isDone() : "Task should be marked as not done";
        return true;
    }

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets the task at the specified index.
     *
     * @param index The index of the task to get.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Tags a task with a specified tag.
     *
     * @param taskIndex The index of the task to tag.
     * @param tag The tag to add to the task.
     * @return A message indicating that the task has been tagged.
     */
    public String tagTask(int taskIndex, String tag) {
        Task task = tasks.get(taskIndex);
        task.addTag(tag);

        tagMap.putIfAbsent(tag, new ArrayList<>());
        tagMap.get(tag).add(task);
        return "Tagged task: " + task + " with tag: " + tag;
    }

    /**
     * Deletes a tag from a task.
     *
     * @param taskIndex The index of the task to untag.
     * @param tag The tag to remove from the task.
     * @return A message indicating that the tag has been removed from the task.
     */
    public String untagTask(int taskIndex, String tag) {
        Task task = tasks.get(taskIndex);

        // Check if tag exists in task
        if (!task.hasTag(tag)) {
            return "Tag not found in task!";
        }

        task.removeTag(tag);
        

        tagMap.get(tag).remove(task);
        return "Removed tag: " + tag + " from task: " + task;
    }

    /**
     * Prints the list of tasks.
     */
    
    public String printTaskList() {
        if (tasks.isEmpty()) {
            return " No tasks available!";
        } else {
            StringBuilder taskList = new StringBuilder();
            taskList.append(" Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                taskList.append(" " + (i + 1) + ". " + tasks.get(i) + "\n");
            }
            return taskList.toString();
        }
    }

    /**
     * Finds tasks that contain the specified keyword.
     *
     * @param keyword The keyword to search for.
     */

    public String findTask(String keyword) {
        StringBuilder matchingTasks = new StringBuilder();
        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(keyword)) {
                matchingTasks.append(" " + (i + 1) + ". " + tasks.get(i) + "\n");
                count++;
            }
        }
        if (count == 0) {
            return " No matching tasks found!";
        } else {
            return " Here are the matching tasks in your list:\n" + matchingTasks.toString();
        }
    }

    /**
     * Gets the list of tasks with the specified tag.
     *
     * @param tag The tag to search for.
     * @return The list of tasks with the specified tag.
     */
    public Set<String> getTags() {
        return tagMap.keySet();
    }
}

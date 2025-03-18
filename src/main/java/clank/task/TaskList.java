package clank.task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import clank.exception.ClankException;


/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with a given list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
        String message = "I've added \"" + task.getDescription() + "\" as "
                + (task instanceof Deadline ? "a task with deadline!"
                : task instanceof Event ? "an event!"
                : "a todo!")
                + "\nCheck it out!";
        System.out.println(message);
        listTasks();
    }

    /**
     * Lists all tasks in the task list.
     */
    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("You currently have no task!");
        } else {
            System.out.println("Here are your current tasks:");
            Stream.iterate(1, i -> i + 1)
                    .limit(tasks.size())
                    .map(i -> i + ". " + tasks.get(i - 1))
                    .forEach(System.out::println);
        }
    }

    /**
     * Validates that the given task index is within the valid range of the task list.
     *
     * @param index The index of the task to validate (zero-based index).
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    private void validateIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Invalid task index: " + (index + 1));
        }
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index The index of the task to mark as done (zero-based index).
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public void mark(int index) throws IndexOutOfBoundsException {
        validateIndex(index);
        tasks.get(index).mark();
        System.out.println("Marked " + (index + 1) + " as done.");
    }

    /**
     * Unmarks the task at the specified index, setting it as not done.
     *
     * @param index The index of the task to unmark (zero-based index).
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public void unmark(int index) throws IndexOutOfBoundsException {
        validateIndex(index);
        tasks.get(index).unmark();
        System.out.println("Unmarked " + (index + 1) + ".");
    }

    /**
     * Deletes a task from the task list at the specified index, or clears all tasks if specified.
     *
     * @param index The index of the task to delete (zero-based index).
     * @param toDeleteAll If true, deletes all tasks in the list.
     * @throws ClankException If an invalid format is encountered.
     * @throws IndexOutOfBoundsException If the index is out of range when deleting a single task.
     */
    public void deleteTask(int index, boolean toDeleteAll) throws ClankException, IndexOutOfBoundsException {
        if (toDeleteAll) {
            tasks.clear();
            System.out.println("Alright! I've deleted all the tasks!");
            return;
        }

        validateIndex(index);
        tasks.remove(index);
        System.out.println("I've successfully deleted task " + (index + 1)
                + ". Here are your remaining tasks.");
        listTasks();
    }

    /**
     * Finds and returns a list of tasks that contain any of the specified keywords in their description.
     * The search is case-insensitive, meaning it matches regardless of capitalization.
     *
     * @param keywords The keywords to search for within task descriptions.
     * @return An {@code ArrayList<Task>} containing tasks that match at least one of the given keywords.
     */
    public ArrayList<Task> findTasksWithKeywords(String... keywords) {
        return tasks.stream()
                .filter(task -> containsAnyKeyword(task.getDescription(), keywords))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Finds and retrieves a list of upcoming tasks with deadlines within the specified number of days.
     *
     * @param days The number of days ahead to check for upcoming deadlines.
     * @return An {@code ArrayList<Task>} containing deadline tasks that are due within the next {@code days}.
     */
    public ArrayList<Task> findUpcomingTasks(long days) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime reminderThreshold = now.plusDays(days);

        return tasks.stream()
                .filter(task -> task instanceof Deadline)
                .filter(task -> {
                    LocalDateTime taskDate = ((Deadline) task).getBy();
                    return taskDate.isBefore(reminderThreshold);
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Checks if a given task description contains any of the specified keywords.
     * The comparison is case-insensitive.
     *
     * @param description The task description to be checked.
     * @param keywords The array of keywords to match against the description.
     * @return {@code true} if at least one keyword is found in the description, otherwise {@code false}.
     */
    private boolean containsAnyKeyword(String description, String... keywords) {
        return Arrays.stream(keywords)
                .map(String::toLowerCase)
                .anyMatch(description::contains);
    }
}

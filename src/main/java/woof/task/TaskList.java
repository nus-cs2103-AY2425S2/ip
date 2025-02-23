package woof.task;

import java.util.ArrayList;
import java.util.List;

import woof.exception.IllegalDateException;
import woof.exception.IllegalDateTimeException;
import woof.exception.MarkedErrorException;
import woof.exception.UnmarkedErrorException;

/**
 * Represents the list of tasks.
 */
public class TaskList {
    private static ArrayList<Task> list = new ArrayList<>();

    /**
     * Converts a list of task into an instance of a task list, majorly used when importing locally stored tasks.
     *
     * @param tasks List of task usually scanned from a local source.
     */
    public TaskList(List<Task> tasks) {
        list.addAll(tasks);
    }

    /**
     * Creates an instance of a sample task list.
     */
    public TaskList() {
        list = new ArrayList<>(100);
    }

    /**
     * Adds a new todo task in the task list.
     *
     * @param task Description of the todo.
     */
    public static void addTodo(String task) {
        list.add(new Todo(task.trim()));
    }

    /**
     * Adds a new deadline task in the task list.
     *
     * @param task Description of the deadline.
     * @param date Date on which the deadline is due.
     * @throws IllegalDateException Invalid date format.
     */
    public static void addDeadline(String task, String date) throws IllegalDateException {
        list.add(new Deadline(task.trim(), date.trim()));
    }

    /**
     * Adds a new event task in the task list.
     *
     * @param task Description of the event.
     * @param from Date and time on which the event starts.
     * @param to Date and time on which the event ends.
     * @throws IllegalDateTimeException Invalid date and time format.
     */
    public static void addEvent(String task, String from, String to) throws IllegalDateTimeException {
        list.add(new Event(task.trim(), from.trim(), to.trim()));
    }

    /**
     * Prints all tasks in the list.
     *
     * @return String of tasks.
     */
    public static String print() {
        if (list.isEmpty()) {
            return "WERWER! You have no tasks!";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= list.size(); i++) {
            String curr = list.get(i - 1).toString();
            sb.append(i).append(". ").append(curr);
            if (i < list.size()) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Finds tasks that matches keywords
     *
     * @param query Keywords
     * @return String of tasks
     */
    public static String find(String query) throws IllegalArgumentException {
        if (list.isEmpty()) {
            return "WERWER! You have no tasks!";
        }
        StringBuilder sb = new StringBuilder();
        boolean isFound = false;
        for (int i = 1; i <= list.size(); i++) {
            if (list.get(i - 1).getDescription().contains(query)) {
                isFound = true;
                sb.append(i).append(". ").append(list.get(i - 1).toString());
                if (i < list.size()) {
                    sb.append("\n");
                }
            }
        }
        if (isFound) {
            return sb.toString();
        } else {
            throw new IllegalArgumentException("WERWER! No matching keyword found!");
        }
    }

    /**
     * Marks a task as done at the specified index.
     *
     * @param index The index of the task to mark as done (1-based indexing).
     * @throws MarkedErrorException If the task is already marked as done or the index is invalid.
     */
    public static void mark(int index) throws MarkedErrorException {
        list.get(index - 1).markAsDone();
    }

    /**
     * Unmarks a task as done at the specified index.
     *
     * @param index The index of the task to unmark (1-based indexing).
     * @throws UnmarkedErrorException If the task is already unmarked or the index is invalid.
     */
    public static void unmark(int index) throws UnmarkedErrorException {
        list.get(index - 1).unmarkAsDone();
    }

    /**
     * Deletes a task at the specified index.
     *
     * @param index The index of the task to delete (1-based indexing).
     */
    public static void delete(int index) {
        list.remove(index - 1);
    }

    /**
     * Retrieves the string representation of a task at the specified index.
     *
     * @param index The index of the task to retrieve (1-based indexing).
     * @return The string representation of the task.
     */
    public static String get(int index) {
        return list.get(index - 1).toString();
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public static int size() {
        return list.size();
    }

    /**
     * Retrieves the string representation of the last task in the list.
     *
     * @return The string representation of the last task.
     */
    public static String getLast() {
        return list.get(list.size() - 1).toString();
    }

    /**
     * Retrieves the task object at the specified index.
     *
     * @param index The index of the task to retrieve (1-based indexing).
     * @return The task object at the specified index.
     */
    public static Task getTask(int index) {
        return list.get(index - 1);
    }

    /**
     * Clears all tasks from the list.
     */
    public static void clear() {
        list.clear();
    }
}

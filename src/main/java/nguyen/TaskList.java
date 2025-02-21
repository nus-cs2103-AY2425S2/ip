package nguyen;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Represents a list of tasks and provides various task management functionalities.
 * Supports adding, deleting, marking, searching, sorting, and retrieving tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList; // List of tasks

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Initializes a TaskList with a given list of tasks.
     *
     * @param taskList The list of tasks to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Displays all tasks in the list with their indices.
     */
    public void printList() {
        assert taskList != null : "TaskList should not be null";
        System.out.println("Alright, there are tasks in your list:");
        AtomicInteger countId = new AtomicInteger(1);
        taskList.forEach(task -> System.out.println(countId.getAndIncrement() + ". " + task));
    }

    /**
     * Deletes a task from the list based on its index.
     *
     * @param number The index of the task to delete (1-based index).
     * @throws NguyenException If the index is out of range.
     */
    public void delete(int number) throws NguyenException {
        assert taskList != null : "TaskList should not be null";
        if (number <= 0 || number > taskList.size()) {
            throw new NguyenException("Bro, that task does not even exist");
        }
        System.out.println("Chill, I got rid of this for you:");
        System.out.println(taskList.get(number - 1));
        taskList.remove(number - 1);
        System.out.println("You have got " + taskList.size() + " tasks left No rush");
    }

    /**
     * Marks a task as completed.
     *
     * @param number The index of the task to mark as done (1-based index).
     * @throws NguyenException If the index is out of range.
     */
    public void mark(int number) throws NguyenException {
        assert taskList != null : "TaskList should not be null";
        if (number <= 0 || number > taskList.size()) {
            throw new NguyenException("Nah, that task is not on the list");
        }
        taskList.get(number - 1).mark();
        System.out.println("Nice, that is one less thing to worry about:");
        System.out.println(taskList.get(number - 1));
    }

    /**
     * Marks a task as not completed.
     *
     * @param number The index of the task to unmark (1-based index).
     * @throws NguyenException If the index is out of range.
     */
    public void unMark(int number) throws NguyenException {
        assert taskList != null : "TaskList should not be null";
        if (number <= 0 || number > taskList.size()) {
            throw new NguyenException("Bro, there is no such task to unmark.");
        }
        taskList.get(number - 1).unMark();
        System.out.println("Alright, I will pretend that did not happen:");
        System.out.println(taskList.get(number - 1));
    }

    /**
     * Adds new tasks to the list.
     *
     * @param tasks The tasks to add.
     */
    @SafeVarargs
    public final void add(Task... tasks) {
        assert taskList != null : "TaskList should not be null";
        for (Task task : tasks) {
            taskList.add(task);
        }
    }

    /**
     * Retrieves a task based on its index.
     *
     * @param number The index of the task to retrieve (0-based index).
     * @return The task at the specified index.
     */
    public Task get(int number) {
        assert taskList != null : "TaskList should not be null";
        return taskList.get(number);
    }

    /**
     * Returns the total number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        assert taskList != null : "TaskList should not be null";
        return taskList.size();
    }

    /**
     * Searches for tasks containing a specific keyword.
     *
     * @param keyword The keyword to search for.
     */
    public void find(String keyword) {
        assert taskList != null : "TaskList should not be null";
        System.out.println("Here is what I found for " + keyword + ":");
        int countId = 0;
        for (Task task : taskList) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(++countId + ". " + task);
            }
        }
    }

    /**
     * Sorts tasks by type.
     *
     * @param taskType The type of task to sort (todo, deadline, event).
     * @throws NguyenException If the task type is invalid.
     */
    public void sort(String taskType) throws NguyenException {
        assert taskList != null : "TaskList should not be null";
        if (taskList.isEmpty()) {
            throw new NguyenException("There is nothing to sort, bro. Try adding some tasks first.");
        }
        taskType = taskType.trim();
        System.out.println("Alright, here is your sorted " + taskType + " list:");
        switch (taskType.toLowerCase()) {
        case "todo":
            sortTodoTask();
            break;
        case "deadline":
            sortDeadlineTask();
            break;
        case "event":
            sortEventTask();
            break;
        default:
            throw new NguyenException("Uh that is not a valid task type");
        }
    }
    /**
     * Sort event tasks
     */
    private void sortEventTask() {
        AtomicInteger countId = new AtomicInteger(1);
        taskList.stream()
                .filter(task -> task.getType().equals("event"))
                .sorted(Comparator.comparing(Task::getDescription))
                .forEach(task -> System.out.println(countId.getAndIncrement() + ". " + task));
    }
    /**
     * Sort deadline tasks
     */
    private void sortDeadlineTask() {
        AtomicInteger countId = new AtomicInteger(1);
        taskList.stream()
                .filter(task -> task.getType().equals("deadline"))
                .sorted(Comparator.comparing(Task::getDescription))
                .forEach(task -> System.out.println(countId.getAndIncrement() + ". " + task));
    }
    /**
     * Sort todo tasks
     */
    private void sortTodoTask() {
        AtomicInteger countId = new AtomicInteger(1);
        taskList.stream()
                .filter(task -> task.getType().equals("todo"))
                .sorted(Comparator.comparing(Task::getDescription))
                .forEach(task -> System.out.println(countId.getAndIncrement() + ". " + task));
    }

    /**
     * Handles adding a Todo task.
     *
     * @param item The user input containing task details.
     * @throws NguyenException If the task description is empty.
     */
    public void handleTodoTask(String item) throws NguyenException {
        if (item.length() == 4) {
            throw new NguyenException("Empty task is invalid");
        }
        taskList.add(new Todo(item.substring(4).trim()));
    }

    /**
     * Handles adding a Deadline task.
     *
     * @param item The user input containing task details.
     * @throws NguyenException If the deadline format is incorrect.
     */
    public void handleDeadlineTask(String item) throws NguyenException {
        int indexBy = item.indexOf("/by");
        if (indexBy == -1) {
            throw new NguyenException("Invalid deadline format, " +
                    "expected format: deadline <task> /by <valid date>");
        }
        taskList.add(new Deadline(item.substring(8, indexBy).trim(), item.substring(indexBy + 4).trim()));
    }

    /**
     * Handles adding an Event task.
     *
     * @param item The user input containing task details.
     * @throws NguyenException If the event format is incorrect.
     */
    public void handleEventTask(String item) throws NguyenException {
        int indexFrom = item.indexOf("/from");
        int indexTo = item.indexOf("/to");
        if (indexFrom == -1 || indexTo == -1 || indexFrom > indexTo) {
            throw new NguyenException("Invalid event format, " +
                    "expected format: event <task> /from <valid date> /to <valid date>");
        }
        taskList.add(new Event(
                item.substring(5, indexFrom).trim(),
                item.substring(indexFrom + 6, indexTo).trim(),
                item.substring(indexTo + 4).trim()));
    }

    /**
     * Handles adding different types of tasks based on user input.
     *
     * @param item The task details provided by the user.
     * @throws NguyenException If the task format is invalid.
     */
    public void handleTask(String item) throws NguyenException {
        if (item.startsWith("todo")) {
            handleTodoTask(item);
        } else if (item.startsWith("deadline")) {
            handleDeadlineTask(item);
        } else if (item.startsWith("event")) {
            handleEventTask(item);
        } else {
            throw new NguyenException("Nah, that is not a real task type");
        }
        System.out.println("So cool, I added this to your list:");
        System.out.println(taskList.get(taskList.size() - 1));
        System.out.println("Now you are got " + taskList.size() + " things to do");
    }
}

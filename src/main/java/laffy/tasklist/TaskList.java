package laffy.tasklist;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import laffy.tasklist.exceptions.IndexOutOfRange;
import laffy.tasklist.tasks.Deadline;
import laffy.tasklist.tasks.Event;
import laffy.tasklist.tasks.Task;
import laffy.tasklist.tasks.ToDo;


/**
 * Represents the list of tasks.
 */
public class TaskList {
    //    stores the list of tasks
    private final ArrayList<Task> tasks;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for TaskList. Used when loading data from file.
     *
     * @param tasksData The data of the tasks.
     */
    public TaskList(ArrayList<ArrayList<String>> tasksData) {
        this.tasks = new ArrayList<>();
        for (ArrayList<String> taskData : tasksData) {
            String taskType = taskData.get(0);
            boolean isDone = taskData.get(1).equals("1");
            String desc = taskData.get(2);
            switch (taskType) {
            case "T":
                this.tasks.add(new ToDo(desc, isDone));
                break;
            case "D":
                LocalDateTime by = TaskDateProvider.parseFromStorage(taskData.get(3));
                this.tasks.add(new Deadline(desc, isDone, by));
                break;
            case "E":
                LocalDateTime from = TaskDateProvider.parseFromStorage(taskData.get(3));
                LocalDateTime to = TaskDateProvider.parseFromStorage(taskData.get(4));
                this.tasks.add(new Event(desc, isDone, from, to));
                break;
            default:
                break;
            }
        }
    }

    private static String tasklistToString(ArrayList<Task> listOfTasks) {
        StringBuilder sb = new StringBuilder();
        int sizeMagnitude = (int) Math.log10(listOfTasks.size()) + 1;
        String space = " ";
        for (int i = 0; i < listOfTasks.size(); i++) {
            int iMagnitude = (int) Math.log10(i + 1) + 1;
            sb.append(i + 1)
                    .append(".").append(space.repeat(sizeMagnitude - iMagnitude + 1))
                    .append(listOfTasks.get(i).toString());
            if (i != listOfTasks.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Converts the tasks in the TaskList to data format.
     * The data format is a list of lists, where each inner list
     * represents a task. Used by Storage to save data to file.
     *
     * @return The data of the tasks.
     */
    public ArrayList<ArrayList<String>> toTasksData() {
        ArrayList<ArrayList<String>> tasksData = new ArrayList<>();
        for (Task task : this.tasks) {
            tasksData.add(task.toTaskData());
        }
        return tasksData;
    }

    public int size() {
        return this.tasks.size();
    }

    /**
     * Adds a ToDo task to the TaskList.
     *
     * @param desc The description of the task.
     * @return The string representation of the task.
     */
    public String addTodo(String desc) {
        ToDo todo = new ToDo(desc);
        this.tasks.add(todo);
        return todo.toString();
    }

    /**
     * Adds a Deadline task to the TaskList.
     *
     * @param desc  The description of the task.
     * @param byStr The deadline of the task.
     * @return The string representation of the task.
     */
    public String addDeadline(String desc, String byStr) {
        LocalDateTime byDateTime = TaskDateProvider.parseDateTime(byStr);
        Deadline deadline = new Deadline(desc, byDateTime);
        this.tasks.add(new Deadline(desc, byDateTime));
        return deadline.toString();
    }

    /**
     * Adds an Event task to the TaskList.
     *
     * @param desc  The description of the task.
     * @param fromStr The start time of the event.
     * @param toStr The end time of the event.
     * @return The string representation of the task.
     */
    public String addEvent(String desc, String fromStr, String toStr) {
        LocalDateTime fromDateTime = TaskDateProvider.parseDateTime(fromStr);
        LocalDateTime toDateTime = TaskDateProvider.parseDateTime(toStr);
        Event event = new Event(desc, fromDateTime, toDateTime);
        this.tasks.add(new Event(desc, fromDateTime, toDateTime));
        return event.toString();
    }

    /**
     * Marks a task as done.
     * The task must be marked as not done before it can be marked as done.
     * If the task is already done, the method will return a message indicating that
     * the task is already marked as done.
     *
     * @param index The index of the task to be marked as done.
     * @return The string representation of the task.
     * @throws IndexOutOfRange If the index is out of range.
     */
    public String markAsDone(int index) throws IndexOutOfRange {
        checkIndexAndThrow(index);
        if (this.tasks.get(index).isDone()) {
            return "Task is already marked as done!";
        } else {
            this.tasks.get(index).markAsDone();
            return "Nice! I've marked this task as done:\n  "
                    + this.tasks.get(index).toString();
        }
    }

    /**
     * Marks a task as not done.
     * The task must be marked as done before it can be marked as not done.
     * If the task is not done, the method will return a message indicating that
     * the task is already marked as not done.
     *
     * @param index The index of the task to be marked as not done.
     * @return The string representation of the task.
     * @throws IndexOutOfRange If the index is out of range.
     */
    public String markAsUndone(int index) throws IndexOutOfRange {
        checkIndexAndThrow(index);
        if (!this.tasks.get(index).isDone()) {
            return "Task is already marked as not done!";
        } else {
            this.tasks.get(index).markAsUndone();
            return "Ok, I've marked this task as not yet done:\n  "
                    + this.tasks.get(index).toString();
        }
    }

    /**
     * Finds tasks with the keyword in the description.
     *
     * @param keyword The keyword to be searched.
     * @return The string representation of the tasks.
     */
    public String find(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        if (foundTasks.isEmpty()) {
            return "No tasks found with the keyword: " + keyword;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        sb.append(tasklistToString(foundTasks));
        return sb.toString();
    }

    /**
     * Gets the upcoming tasks in the TaskList.
     *
     * @return The string representation of the upcoming tasks.
     */
    public String getUpcomingTasks() {
        HashMap<LocalDateTime, Task> upcomingTasks = new HashMap<>();
        for (Task task : this.tasks) {
            if (task.isUpcoming()) {
                upcomingTasks.put(task.getDeadline(), task);
            }
        }
        if (upcomingTasks.isEmpty()) {
            return "No upcoming tasks found!";
        }
        ArrayList<Task> sortedUpcomingTasks = new ArrayList<>();
        upcomingTasks.keySet().stream()
                .sorted()
                .forEach(key -> sortedUpcomingTasks.add(upcomingTasks.get(key)));
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the upcoming tasks in your list:\n");
        sb.append(tasklistToString(sortedUpcomingTasks));
        return sb.toString();
    }

    /**
     * Deletes a task from the TaskList.
     *
     * @param index The index of the task to be deleted.
     * @return The string representation of the task.
     * @throws IndexOutOfRange If the index is out of range.
     */
    public String delete(int index) throws IndexOutOfRange {
        checkIndexAndThrow(index);
        Task task = this.tasks.get(index);
        this.tasks.remove(index);
        return "Noted. I've removed this task:\n  "
                + task.toString()
                + "\nNow you have " + this.tasks.size() + " tasks in the list.";
    }

    public boolean isValidIndex(int index) {
        return index >= 0 && index < this.size();
    }

    /**
     * Checks if the index is out of range.
     *
     * @param index The index to be checked.
     * @throws IndexOutOfRange If the index is out of range.
     */
    public void checkIndexAndThrow(int index) throws IndexOutOfRange {
        if (!isValidIndex(index)) {
            throw new IndexOutOfRange("Valid index range is 1 to " + this.tasks.size());
        }
    }

    @Override
    public String toString() {
        if (this.tasks.isEmpty()) {
            return "There are no tasks in the list.";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        sb.append(tasklistToString(this.tasks));
        return sb.toString();
    }


}

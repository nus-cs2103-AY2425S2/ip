package thoughtbot;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import exceptions.OutOfBoundsTaskException;
import exceptions.TaskNotFoundException;
import tasks.Task;
import tasks.TaskDeadline;
import tasks.TaskEvent;
import tasks.TaskToDo;

/**
 * The thoughtbot.TaskList object that keeps track of the Entry objects the user has currently added.
 * This object also manages all the operations on the Entries itself.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private String printString = "";

    public TaskList() {
        this.tasks = new ArrayList<>(100);
    }

    /**
     * Adds a task to the tasks ArrayList, without any return. This is mainly used by the load function in SaveLoad
     * @param t The task to be added
     */
    public void addTask(Task t) {
        this.tasks.add(t);
    }

    /**
     * Adds a Todo Task to the tasks ArrayList and returns the corresponding response
     *
     * @param entryName Name of the task to be added
     * @return Response after adding task
     */
    public String addTodo(String entryName) {
        Task tTodo = new TaskToDo(entryName);
        this.tasks.add(tTodo);
        return "Added task:\n" + tTodo.getFullName() + "\n";
    }

    /**
     * Adds a Deadline Task to the tasks ArrayList and returns the corresponding response
     *
     * @param entryName Name of the task to be added
     * @param deadline Deadline of the task
     * @return Response after adding task
     */
    public String addDeadline(String entryName, LocalDateTime deadline) {
        Task tDeadline = new TaskDeadline(entryName, deadline);
        this.tasks.add(tDeadline);
        return "Added task:\n" + tDeadline.getFullName() + "\n";
    }

    /**
     * Adds an Event Task to the tasks ArrayList and returns the corresponding response
     *
     * @param entryName Name of the task to be added
     * @param fromTime Start time of the task
     * @param toTime End time of the task
     * @return Response after adding task
     */
    public String addEvent(String entryName, LocalDateTime fromTime, LocalDateTime toTime) {
        Task tEvent = new TaskEvent(entryName, fromTime, toTime);
        this.tasks.add(tEvent);
        return "Added task:\n" + tEvent.getFullName() + "\n";
    }

    /**
     * Marks the corresponding task with at the index number as done and returns the
     * corresponding response
     *
     * @param number Index of the task to be marked
     * @return Response after marking task
     * @throws OutOfBoundsTaskException If number is > size of tasks ArrayList
     */
    public String markEntry(int number) throws OutOfBoundsTaskException {
        printString = "";
        if (number > tasks.size() || number < 0) {
            throw new OutOfBoundsTaskException();
        }

        Task taskToMark = tasks.get(number - 1);
        boolean isMarked = taskToMark.markDone();
        if (isMarked) {
            printString += "I have marked this task as done.\n" + taskToMark.getFullName();
        } else {
            printString += "This task has already been marked.";
        }
        printString += "\n";
        return printString;
    }

    /**
     * Unmarks the corresponding task with at the index number as done and returns the
     * corresponding response
     *
     * @param number Index of the task to be unmarked
     * @return Response after unmarking task
     * @throws OutOfBoundsTaskException If number is > size of tasks ArrayList
     */
    public String unmarkEntry(int number) throws OutOfBoundsTaskException {
        printString = "";
        if (number > tasks.size() || number < 0) {
            throw new OutOfBoundsTaskException();
        }

        Task taskToUnmark = tasks.get(number - 1);
        boolean isUnmarked = taskToUnmark.unmarkDone();
        if (isUnmarked) {
            printString += "I have unmarked this task as done.\n" + taskToUnmark.getFullName();
        } else {
            printString += "This task has already been unmarked.";
        }
        printString += "\n";
        return printString;
    }

    /**
     * Deletes the corresponding task with at the index number and returns the
     * corresponding response
     *
     * @param number Index of the task to be deleted
     * @return Response after deleting task
     * @throws OutOfBoundsTaskException If number is > size of tasks ArrayList
     */
    public String deleteTask(int number) throws OutOfBoundsTaskException {
        printString = "";
        if (number > tasks.size() || number < 0) {
            throw new OutOfBoundsTaskException();
        }

        Task taskToDelete = tasks.get(number - 1);
        printString = "I have deleted this task:\n" + taskToDelete.getFullName() + "\n";
        tasks.remove(taskToDelete);
        return printString;
    }

    /**
     * Finds the tasks that contain the substring of findString
     *
     * @param findString Substring to search for in the tasks
     * @return String containing the tasks that have matched
     * @throws TaskNotFoundException If there are no tasks with a matching substring
     */
    public String findTasks(String findString) throws TaskNotFoundException {
        String introString = "Here are the tasks matching the search for \"" + findString + "\":\n";
        printString = "";
        for (Task t: tasks) {
            String taskName = t.getName();
            if (taskName.contains(findString.toLowerCase())) {
                printString += t.getFullName() + "\n";
            }
        }

        if (!printString.isBlank()) {
            return introString + printString;
        } else {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Identifies tasks that are due within one week and returns it as a String
     *
     * @return String containing tasks due within a week
     */
    public String remindTasks() {
        String introString = "Here are the tasks that are due/happening within the week:\n";
        printString = "";
        long weekInMinutes = 10080;

        LocalDateTime currentTime = LocalDateTime.now();

        for (Task t: tasks) {
            if (t.getClass() == TaskToDo.class) { // ToDo tasks have no deadline
                continue;
            }

            long minutesDifference = 0;

            if (t.getClass() == TaskDeadline.class) {
                TaskDeadline deadlineTask = (TaskDeadline) t;
                LocalDateTime deadlineTime = deadlineTask.getDeadlineLdt();
                minutesDifference = ChronoUnit.MINUTES.between(currentTime, deadlineTime);
            } else if (t.getClass() == TaskEvent.class) {
                TaskEvent eventTask = (TaskEvent) t;
                LocalDateTime eventFromTime = eventTask.getFromTimeLdt();
                minutesDifference = ChronoUnit.MINUTES.between(currentTime, eventFromTime);
            }

            assert minutesDifference != 0 : "Unhandled task type in TaskList remindTasks";

            boolean isWithinWeek = minutesDifference > 0 && minutesDifference < weekInMinutes;

            if (isWithinWeek) {
                printString += t.getFullName() + "\n";
            }
        }

        if (printString.isEmpty()) {
            return "There are no current tasks due/happening within the week.";
        }

        return introString + printString;
    }

    /**
     * Returns the current tasks, formatted nicely
     *
     * @return Current tasks String
     */
    public String getTaskList() {
        printString = "Here are your current tasks:\n";
        int currentNumber = 1;

        for (Task e: tasks) {
            String numberString = String.valueOf(currentNumber);
            currentNumber++;

            printString = printString + numberString + ". " + e.getFullName() + "\n";
        }

        return printString;
    }

    /**
     * Returns the number of current tasks
     *
     * @return Current number of tasks String
     */
    public String getTaskNumbersString() {
        return "There is/are currently " + String.valueOf(tasks.size()) + " task(s) in the list.\n";
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}

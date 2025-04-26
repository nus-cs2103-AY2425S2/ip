package ui;

import task.PriorityRoster;
import task.Task;


/**
 * The Ui class handles user interactions by returning formatted messages to the Sunpter main class.
 */
public class Ui {
    private static final String GREETING = "Now this is a story all about how I became a chatbot just messing around!" +
                                                "\n Type help to find out more";
    private static final String FIN = "Alright show’s over. Keep my name out your mouth, and keep your tasks in check.";
    private static final String TASK_DONE = "That’s what I like to see!\n Now this is how you win an award.";
    private static final String TASK_UNDONE = "You unmarked it? SLAP👋" +
                                                "\nThat’s like taking back an apology after the show ended.";

    public Ui() {
    }

    /**
     * Prints the welcome message.
     */
    public static String showWelcome() {
        return GREETING;
    }

    /**
     * Returns a message indicating a task has been removed.
     *
     * @param task   The task to be removed.
     * @param roster The roster from which the task is removed.
     */
    public String removeTaskMessage(Task task, PriorityRoster roster) {
        return "removed" +
                "\n" + task.toString() + "\n" +
                "Now you have " + roster.numberOfTasks() + " tasks in the list.";
    }

    /**
     * Returns a message indicating a task has been marked as uncompleted.
     *
     * @param roster The roster containing the task.
     * @param number The index of the task.
     */
    public String markAsUncompleted(PriorityRoster roster, int number) {
        return TASK_UNDONE + "\n" + roster.getTask(number).toString();
    }

    /**
     * Prints the exit message.
     */
    public String endMessage() {
        return FIN;
    }

    /**
     * Prints a message indicating a task has been marked as completed.
     *
     * @param roster The roster containing the task.
     * @param number The index of the task.
     */
    public String markAsCompleted(PriorityRoster roster, int number) {
        return TASK_DONE + "\n" + roster.getTask(number).toString();
    }

    /**
     * Prints an error message for incorrect formatting.
     *
     * @param formatting The correct formatting required.
     */
    public static String incorrectFormattingError(String formatting) {
        return"SLAP👋 That isn’t the right format. Try:" + formatting;
    }

    /**
     * Prints a message indicating a task has been added.
     *
     * @param task       The task that was added.
     * @param rosterSize The current size of the roster.
     */
    public String showTaskAddedMessage(Task task, int rosterSize) {
        String taskAdded = "Gotcha. I've added this task:" +
                "\n" + task.toString() + "\n" +
                "Now you have " + rosterSize + " tasks in the list.";
        return taskAdded;
    }

    /**
     * Shows the number of priority points earned by completed tasks
     */
    public String priorityPoints(PriorityRoster roster) {
        int completedPoints = roster.getCompletedPriorityPoints();
        int totalPoints = roster.getTotalPriorityPoints();
        int percentage = (int) Math.round(((double) completedPoints / totalPoints) * 100);
        return " Alright, here’s where you stand: " +
                 completedPoints+ "/" + totalPoints +
                "priority points (" + percentage +"%). Keep going! 🚀";
    }
}
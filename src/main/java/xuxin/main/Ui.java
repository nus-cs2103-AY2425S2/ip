package xuxin.main;

import java.util.ArrayList;
import xuxin.task.Task;

/**
 * A Ui class to manage IO output.
 */
public class Ui {
    private StringBuilder output;

    public Ui() {
        output = new StringBuilder();
    }

    /**
     * Set the output to an empty String.
     */
    void resetOutput() {
        output.setLength(0);
    }

    String showOutput() {
        return output.toString();
    }

    private void appendToOutput(String message) {
        output.append(message + "\n");
    }

    public void showError(String message) {
        appendToOutput("OOPS!!! " + message);
    }

    /**
     * Prints the exit message.
     */
    public void showGoodbye() {
        appendToOutput("Bye. Hope to see you again!");
    }

    /**
     * Prints the tasks in the ArrayList.
     * @param tasks an ArrayList storing all tasks.
     */
    public void showTaskList(ArrayList<Task> tasks) {
        appendToOutput("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            appendToOutput((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Prints the number of task in the task list.
     * @param count the count in the task list.
     */
    public void showTaskCount(int count) {
        appendToOutput("Now you have " + count + " tasks in the list.");
    }

    /**
     * Prints the message saying a task is added to the task list.
     * @param task the task that is added to the task list.
     */
    public void addSuccess(Task task) {
        appendToOutput("Got it. I've added this task: \n" + task.toString());
    }

    /**
     * Prints the message to be passed into the Ui.
     * @param message the task that is added to the task list.
     */
    public void addMessage(String message) {
        appendToOutput(message);
    }
}

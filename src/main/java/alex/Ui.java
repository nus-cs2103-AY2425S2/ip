package alex;

import java.util.ArrayList;
import alex.task.Task;

public interface Ui {
    /**
     * Prints the welcome message when launching the program
     */
    void printWelcomeMsg();

    /**
     * Reads a line from input
     * @return the command read
     */
    void readCommand();


    /**
     * Prints message when user exits program
     */
    void printExitMsg();

    /**
     * Return the string to be printed when the task count is requested
     * @param count the count of tasks
     * @return the message to be printed
     */
    String getTaskCount(int count);

    /**
     * Prints the response message when a task is added
     * @param task task details
     * @param count the count after adding the task
     */
    void addItemResponse(String task, int count);

    /**
     * Prints the response message when a task is deleted
     * @param task task details
     * @param count the count after deleting the task
     */
    void deleteTaskResponse(String task, int count);

    /**
     * Shows the error message when getting the error
     * @param e
     */
    void showErrorMsg(Exception e);

    /**
     * Shows the search result
     * @param result list of tasks
     */
    void showSearchResult(ArrayList<Task> result);

    void printMsg(String msg);
}
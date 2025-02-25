package alex;

import alex.task.Task;

import java.util.ArrayList;

public class MockUi implements Ui {
    /**
     * Prints the welcome message when launching the program
     */
    public void printWelcomeMsg() {
    }

    /**
     * Reads a line from input
     * @return the command read
     */
    public void readCommand(){};


    /**
     * Prints message when user exits program
     */
    public void printExitMsg(){};

    /**
     * Return the string to be printed when the task count is requested
     * @param count the count of tasks
     * @return the message to be printed
     */
    public String getTaskCount(int count){
        return "";
    };

    /**
     * Prints the response message when a task is added
     * @param task task details
     * @param count the count after adding the task
     */
    public void addItemResponse(String task, int count){};

    /**
     * Prints the response message when a task is deleted
     * @param task task details
     * @param count the count after deleting the task
     */
    public void deleteTaskResponse(String task, int count){};

    /**
     * Shows the error message when getting the error
     * @param e
     */
    public void showErrorMsg(Exception e){};

    /**
     * Shows the search result
     * @param result list of tasks
     */
    public void showSearchResult(ArrayList<Task> result){};

    public void printMsg(String msg){
    };
}

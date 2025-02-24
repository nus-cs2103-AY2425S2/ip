package miki.ui;

import java.util.Scanner;
import java.util.stream.Collectors;

import miki.task.Task;
import miki.task.TaskList;

/**
 * Ui class to handle all user interface interactions.
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructor for Ui class
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Displays a loading error if tasks fail to load from file in the CLI.
     * Returns a string containing the error message for the GUI to display.
     *
     * @returns String containing error message
     */
    public String showLoadingError() {
        String error = "Error loading tasks from file. Starting with an empty task list.";
        System.out.println(error);
        return error;
    }

    /**
     * Displays a line in the CLI.
     * Returns a string containing a line for the GUI to display.
     *
     * @returns String containing a line.
     */
    public String showLine() {
        String line = "____________________________________________________________";
        System.out.println(line);
        return line;
    }

    /**
     * Displays the intro in the CLI.
     * Returns a string containing the intro for the GUI to display.
     *
     * @returns String containing the intro.
     */
    public String showIntro() {
        String logo = "      __  _________ __ ____\n"
                + "     /  |/  /  _/ //_//  _/\n"
                + "    / /|_/ // // ,<   / /  \n"
                + "   / /  / // // /| |_/ /   \n"
                + "  /_/  /_/___/_/ |_/___/   \n"
                + "                           \n"
                + "          ฅ^•ﻌ•^ฅ         ";

        String intro = "Hello from \n"
                + logo
                + "\n"
                + "Your ChatBot assistant Dawg :). \n"
                + "\n"
                + "Please enter a command to start: \n";

        showLine();
        System.out.println(intro);
        showLine();
        return intro;
    }

    /**
     * Displays the greeting in the CLI.
     * Returns a string containing the greeting for the GUI to display.
     *
     * @returns String containing the greeting.
     */
    public String showGreeting() {
        String greeting = "What's good Dawg! Whatchu need?";
        System.out.println(greeting);
        return greeting;
    }

    /**
     * Reads the next command from the CLI.
     * Returns a string containing the input from the user.
     *
     * @returns String containing input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays the error message in the CLI.
     * Returns a string containing the error message for the GUI to display.
     *
     * @param errorMessage String containing the error message.
     *
     * @returns String containing the error message.
     */
    public String showErrorMessage(String errorMessage) {
        System.out.println("Error Occured Dawg.\n" + errorMessage);
        return "Error Occured Dawg.\n" + errorMessage;
    }

    /**
     * Displays the task added in the CLI.
     * Returns a string containing the task added for the GUI to display.
     *
     * @param task        Task object containing task added
     * @param sizeOfTasks Integer containing size of task list
     *
     * @returns String to display the task added.
     */
    public String showTaskAdded(Task task, int sizeOfTasks) {
        String output = task.getTaskType().toString()
                + " task has been added Dawg.\n"
                + task.toString()
                + "You now have " + sizeOfTasks + " tasks in the list Dawg.";
        System.out.println(output);
        return output;
    }

    /**
     * Displays the task deleted in the CLI.
     * Returns a string containing the task deleted for the GUI to display.
     *
     * @param task      Task object containing task deleted
     * @param taskIndex Integer containing task number
     *
     * @returns String to display the task deleted.
     */
    public String showTaskDeleted(Task task, int taskIndex) {
        String output = "Task " + taskIndex + " has been deleted Dawg.\n"
                + task.toString();
        System.out.println(output);
        return output;
    }

    /**
     * Displays the list of tasks in the CLI.
     * Returns a string containing the list of tasks for the GUI to display.
     *
     * @param tasks TaskList object containing list of tasks
     *
     * @returns String to display the list of tasks.
     */
    public String showList(TaskList tasks) {
        if (tasks.getSize() == 0) { // account for no tasks
            String output = "No tasks in the list Dawg.";
            System.out.println(output);
            return output;
        }
        String output = "Here are the tasks in your list Dawg:\n";
        // add tasks to output string 1 by 1
        output += tasks.getTaskList().stream()
                .map(task -> (tasks.getTaskList().indexOf(task) + 1) + ". " + task.toString())
                .collect(Collectors.joining("\n")) + "\n";
        System.out.println(output);
        return output;
    }

    /**
     * Displays the completion status of a given task in the CLI.
     * Returns a string containing the completion status of the task for the GUI to
     * display.
     *
     * @param task      Task object containing task marked
     * @param taskIndex Integer containing task number
     *
     * @returns String to display the completion status of the task.
     */
    public String showTaskCompletion(Task task, int taskIndex) {
        boolean isCompleted = task.checkCompleted();
        String output = "Task " + taskIndex + (isCompleted ? " completed.\n" : " is not completed.\n")
                + task.toString();
        System.out.println(output);
        return output;
    }

    /**
     * Displays the exit message in the CLI.
     * Returns a string containing the exit message for the GUI to display.
     *
     * @returns String to display the exit message.
     */
    public String showExit() {
        String message = "End of session Dawg. Goodbye Dawg.\n";
        System.out.println(message);
        showLine();
        sc.close();
        return message;
    }
}

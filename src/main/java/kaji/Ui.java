package kaji;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

import kaji.task.Task;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private static final String INDENTATION = "    ";
    // Logo design adapted from https://patorjk.com/software/taag
    private static final String LOGO = INDENTATION
            + "    )               (\n"
            + "      ( /(   (           )\\ )\n"
            + "      )\\())  )\\      (  (()/(\n"
            + "     ((_)\\((((_)(    )\\  /(_))\n"
            + "     _ ((_))\\ _ )\\  ((_)(_))\n"
            + "    | |/ / (_) \\(_)_ | ||_ _|\n"
            + "    | ' (   / _ \\ | || | | |\n"
            + "    |_|\\_\\ /_/ \\_\\ \\__/ |___|";
    private static final String GREET = INDENTATION + "Hello! I'm KAJI, your personal virtual assistant.\n"
            + INDENTATION + "How can i help you today?";
    private static final String EXIT = INDENTATION + "Thank you for chatting with me!\n"
            + INDENTATION + "See you again soon!";

    /**
     * Adds indentation to text.
     */
    public String addIndentation() {
        return INDENTATION;
    }

    /**
     * Displays the welcome message.
     */
    public String showWelcome() {
        return GREET;
    }

    /**
     * Displays the exit message.
     */
    public String showExit() {
        return EXIT;
    }

    /**
     * Displays error for storage load fail.
     */
    public void showLoadingError() {
        System.out.println("File could not be loaded...");
    }

    /**
     * Displays confirmation message if task is successfully added to task list.
     *
     * @param taskList the task list containing task added.
     */
    public String showTaskAdded(ArrayList<Task> taskList) {
        Task addedTask = taskList.get(taskList.size() - 1);
        String taskWord = (taskList.size() == 1) ? "task" : "tasks";
        return String.format(
                "%sThis task has been added:%n%s  %s%n%sThere are now %d %s in your list.",
                INDENTATION, INDENTATION, addedTask.toString(), INDENTATION, taskList.size(), taskWord
        );
    }

    /**
     * Displays confirmation message if task is successfully deleted from task list.
     *
     * @param taskList the task list without the deleted task.
     * @param taskDeleted the task that is deleted.
     */
    public String showTaskDeleted(ArrayList<Task> taskList, Task taskDeleted) {
        String taskWord = (taskList.size() == 1) ? "task" : "tasks";
        return String.format(
                "%sThis task has been removed:%n%s%s%s%n%sThere are %d %s left in the list.",
                INDENTATION, INDENTATION, INDENTATION, taskDeleted.toString(), INDENTATION, taskList.size(), taskWord
        );
    }

    /**
     * Displays confirmation message if task is successfully marked in the task list.
     *
     * @param markedTask the task that is marked.
     */
    public String showMarkedTask(Task markedTask) {
        return String.format(
                "%sYay! You've completed a task:%n%s%s%s",
                INDENTATION, INDENTATION, INDENTATION, markedTask.toString()
        );
    }

    /**
     * Displays confirmation message if task is successfully unmarked in the task list.
     *
     * @param unmarkedTask the task that is unmarked.
     */
    public String showUnmarkedTask(Task unmarkedTask) {
        return String.format(
                "%sUh oh, task is now unmarked:%n%s%s%s",
                INDENTATION, INDENTATION, INDENTATION, unmarkedTask.toString()
        );
    }

    /**
     * Displays all the tasks in the current task list.
     *
     * @param taskList the current task list
     */
    public String showTaskList(TaskList taskList) {
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add(INDENTATION + "Here are the current tasks in your list:");
        for (int i = 0; i < taskList.taskList.size(); i++) {
            joiner.add(String.format("%s%d. %s",
                    INDENTATION + INDENTATION, i + 1, taskList.taskList.get(i).toString()));
        }
        return joiner.toString();
    }

    /**
     * Displays all tasks that contains the keyword.
     * This method is invoked by find command.
     *
     * @param taskList The current task list.
     */
    public String showMatchingTasks(ArrayList<Task> taskList) {
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add(INDENTATION + "Here are the tasks you asked for:");
        for (int i = 0; i < taskList.size(); i++) {
            joiner.add(String.format("%s%d. %s",
                    INDENTATION + INDENTATION, i + 1, taskList.get(i).toString()));
        }
        return joiner.toString();
    }

    /**
     * Displays message for no matching tasks in the current task list.
     * This method is invoked by find command.
     */
    public String showNoMatchingTasks() {
        return INDENTATION + "There are no matching tasks :(";
    }

    /**
     * Displays message for invalid command.
     */
    public String showInvalidCommandMessage() {
        return INDENTATION + "I don't understand what that means :(";
    }

    /**
     * Displays message for task tagged.
     */
    public String showTagAdded(Task task, String tagName) {
        return String.format(
                "%s%s has been tagged with %s",
                INDENTATION, task.getDescription(), tagName
        );
    }

    /**
     * Displays message for task untagged.
     */
    public String showTagRemoved(Task task, String tagName) {
        return String.format(
                "%sTag %s has been removed from %s",
                INDENTATION, tagName, task.getDescription()
        );
    }

    /**
     * Displays a message when a tag is not found in the task.
     */
    public String showTagNotFound(String tagName) {
        return String.format("%sTag '%s' not found in the task.", INDENTATION, tagName);
    }

    /**
     * Reads the command entered by user.
     *
     * @return user command in the form of a String.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}

package helix.ui;

import java.util.List;
import java.util.Scanner;

import helix.enums.OutputSymbol;
import helix.enums.TaskType;
import helix.task.Task;

/**
 * Handles all user interaction for the helix.Helix application.
 *
 * <p>
 * This class provides methods to display messages to the user, read user input, and
 * format output for various operations, such as adding, removing, or listing tasks.
 * It centralizes all console-based interactions to maintain a clear separation of concerns.
 * </p>
 */
public class ConsoleUi {

    private String lastMessage = "";

    private final String logo = """
           ╔════════════════════════════════════════════════════════╗
           ║                                                        ║
           ║             _   _  _____  _      ___ __  __            ║
           ║            | | | || ____|| |    |_ _|\\ \\/ /            ║
           ║            | |_| ||  _|  | |     | |  \\  /             ║
           ║            |  _  || |___ | |___  | |  /  \\             ║
           ║            |_| |_||_____||_____||___|/_/\\_\\            ║
           ║                                                        ║
           ╚════════════════════════════════════════════════════════╝
           """;

    // Output symbols for formatting
    private final String helixSymbol = OutputSymbol.HELIX.getSymbol();
    private final String userSymbol = OutputSymbol.USER.getSymbol();
    private final String warningSymbol = OutputSymbol.WARNING.getSymbol();
    private final String folderSymbol = OutputSymbol.FOLDER.getSymbol();
    private final String clipboardSymbol = OutputSymbol.CLIPBOARD.getSymbol();
    private final String noteSymbol = OutputSymbol.NOTE.getSymbol();
    private final String calendarSymbol = OutputSymbol.CALENDAR.getSymbol();
    private final String clockSymbol = OutputSymbol.CLOCK.getSymbol();
    private final String handwaveSymbol = OutputSymbol.HANDWAVE.getSymbol();
    private final String sparkleSymbol = OutputSymbol.SPARKLE.getSymbol();
    private final String removedSymbol = OutputSymbol.BIN.getSymbol();
    private final String typeSymbol = OutputSymbol.CLIPBOARD.getSymbol();
    private final String descriptionSymbol = OutputSymbol.NOTE.getSymbol();
    private final String completedSymbol = OutputSymbol.WRENCH.getSymbol();
    private final String checkSymbol = OutputSymbol.CHECK.getSymbol();
    private final String crossSymbol = OutputSymbol.CROSS.getSymbol();

    /**
     * Displays the welcome message when the application starts.
     */
    public void showWelcome() {
        // print introduction message
        System.out.println(logo);
        System.out.println(
                helixSymbol + " [Helix] : Hello! I'm your personal assistant, "
        );
        System.out.println(
                helixSymbol + " [Helix] : What can I do for you today?\n"
        );
    }

    /**
     * Displays the exit message when the application terminates.
     */
    public void showExit() {
        System.out.println(
                helixSymbol
                        + " [Helix] : Bye! Hope to see you soon! "
                        + handwaveSymbol
        );
        System.out.println("=========================================================");
        System.out.println(
                sparkleSymbol
                        + " Thank you for using  Have a great day! "
                        + sparkleSymbol
        );
        System.out.println("=========================================================");
        lastMessage = handwaveSymbol + "Bye! Hope to see you soon!";
    }

    /**
     * Reads a command input from the user.
     *
     * @param sc the {@link Scanner} object for reading user input
     * @return the user input as a {@link String}
     */
    public String readCommand(Scanner sc) {
        // retrieve user input
        System.out.print(userSymbol + " [You]   : ");
        return sc.nextLine();
    }

    /**
     * Displays a decorative line for formatting.
     */
    public void showLine() {
        System.out.println("════════════════════════════════════");
    }

    /**
     * Displays an error message.
     *
     * @param message the error message to display
     */
    public void showError(String message) {
        lastMessage = String.format("%s Error message - %s", warningSymbol, message);
        System.out.println(warningSymbol + " [Helix] : " + message + "\n");
    }

    /**
     * Displays an error message indicating a failure to load tasks from storage.
     */
    public void showLoadingStorageError() {
        lastMessage = String.format("%s Failed to load tasks. Starting with an empty list.", warningSymbol);
        System.out.println(
                warningSymbol + " [Helix] : Failed to load tasks. Starting with an empty list."
        );
    }

    /**
     * Displays an error message indicating a failure to save tasks to storage.
     *
     * @param message the detailed error message
     */
    public void showSavingStorageError(String message) {
        lastMessage = String.format("%s Failed to save tasks: %s", warningSymbol, message);
        System.out.println(
                warningSymbol + " [Helix] : Failed to save tasks: " + message
        );
    }

    /**
     * Displays a message indicating that a task was added successfully.
     *
     * @param task the {@link Task} that was added
     * @param taskCount the total number of tasks in the list after the addition
     */
    public void showTaskAdded(Task task, int taskCount) {
        assert task != null : "Task should not be null!";

        TaskType taskType = task.getTaskType();
        assert taskType != null : "Task type should not be null!";

        String taskDescription = task.getDescription();
        String taskDetails = task.getTaskDetails();

        showLine();
        System.out.println(folderSymbol + "  Task Added!");
        showLine();

        System.out.println("  " + clipboardSymbol + " Type: " + taskType.name());
        System.out.println("  " + noteSymbol + " Description: " + taskDescription);

        lastMessage = folderSymbol + "  Task Added!\n"
                + "  " + clipboardSymbol + " Type: " + taskType.name() + "\n"
                + "  " + noteSymbol + " Description: " + taskDescription + "\n";

        if (taskType == TaskType.DEADLINE) {
            System.out.println("  " + calendarSymbol + " Due: " + taskDetails);
            lastMessage += "  " + calendarSymbol + " Due: " + taskDetails;
        } else if (taskType == TaskType.EVENT) {
            String[] parts = taskDetails.split(" - ");
            String from = parts[0];
            String to = parts[1];
            System.out.println("  " + clockSymbol + " From: " + from + "\n  " + clockSymbol + " To: " + to);
            lastMessage += "  " + clockSymbol + " From: " + parts[0] + "\n  " + clockSymbol + " To: " + parts[1] + "\n";
        }
        System.out.println("\nYou now have " + taskCount + " task(s) in your list.");
        lastMessage += "\nYou now have " + taskCount + " task(s) in your list.\n";
        showLine();
        System.out.println();
    }

    /**
     * Displays a message indicating that a task was removed successfully.
     *
     * @param task the {@link Task} that was removed
     * @param taskCount the total number of tasks in the list after the removal
     */
    public void showTaskRemoved(Task task, int taskCount) {
        assert task != null : "Task should not be null!";

        TaskType taskType = task.getTaskType();
        assert taskType != null : "Task type should not be null!";

        String taskStatus = task.getTaskStatus().name();
        String taskDescription = task.getDescription();
        String taskDetails = task.getTaskDetails();

        showLine();
        System.out.println(removedSymbol + "  Task Removed!");
        lastMessage = removedSymbol + "  Task Removed!\n";

        showLine();
        System.out.println("  " + typeSymbol + " Type: " + taskType.name());
        System.out.println("  " + descriptionSymbol + " Description: " + taskDescription);
        lastMessage += "  " + typeSymbol + " Type: " + taskType.name() + "\n"
                + "  " + descriptionSymbol + " Description: " + taskDescription + "\n";

        if (taskType == TaskType.DEADLINE) {
            System.out.println("  " + calendarSymbol + " Due: " + taskDetails);
            lastMessage += "  " + calendarSymbol + " Due: " + taskDetails;
        } else if (taskType == TaskType.EVENT) {
            String[] parts = taskDetails.split(" - ");
            String from = parts[0];
            String to = parts[1];
            System.out.println("  " + clockSymbol + " From: " + from + "\n  " + clockSymbol + " To: " + to);
            lastMessage += "  " + clockSymbol + " From: " + from + "\n  " + clockSymbol + " To: " + to;
        }
        System.out.println("  " + completedSymbol + " Task Status: " + taskStatus);
        System.out.println("\nYou now have " + taskCount + " task(s) in your list.");
        lastMessage += "  " + completedSymbol + " Task Status: " + taskStatus
                + "\n\nYou now have " + taskCount + " task(s) in your list.";
        showLine();
        System.out.println();
    }

    /**
     * Displays a list of tasks to the user.
     *
     * @param tasks the {@link List} of {@link Task} objects to display
     */
    public void showTaskList(List<Task> tasks) {
        System.out.println(helixSymbol + " [Helix] : Listing tasks...");
        showLine();
        System.out.println(noteSymbol + " Task List:");

        lastMessage = "Listing tasks...\n" + noteSymbol + " Task List:\n";

        if (tasks.isEmpty()) {
            System.out.println("There's no pending tasks.");
            showLine();
            System.out.println();
            lastMessage += "There's no pending tasks.";
            return;
        }

        StringBuilder messageBuilder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("    %d. %s%n", i + 1, tasks.get(i));
            messageBuilder.append(String.format("    %d. %s%n", i + 1, tasks.get(i)));
        }
        lastMessage += messageBuilder.toString();

        showLine();
        System.out.println();
    }

    /**
     * Displays a message indicating that the task is already marked as completed.
     */
    public void showTaskAlreadyCompleted() {
        System.out.println(helixSymbol + " [Helix] : This task is already marked as done!\n");
        lastMessage = warningSymbol + " This task is already completed!";
    }

    /**
     * Displays a message indicating that the task was marked as complete.
     *
     * @param task the {@link Task} that was marked as complete
     */
    public void showTaskMarkedComplete(Task task) {
        System.out.println(checkSymbol + " [Helix] : Task marked as complete!");
        System.out.println("    " + task + "\n");
        lastMessage = checkSymbol + " Task marked as complete!\n" + "    " + task;
    }

    /**
     * Displays a message indicating that the task is already marked as pending.
     */
    public void showTaskAlreadyPending() {
        System.out.println(helixSymbol + " [Helix] : This task is already not done!\n");
        lastMessage = warningSymbol + " This task is already pending!";
    }

    /**
     * Displays a message indicating that the task was marked as pending.
     *
     * @param task the {@link Task} that was marked as pending
     */
    public void showTaskMarkedPending(Task task) {
        System.out.println(crossSymbol + " [Helix] : Task marked as incomplete!");
        System.out.println("    " + task + "\n");
        lastMessage = crossSymbol + " Task marked as incomplete!\n" + "    " + task;
    }

    /**
     * Displays the matching tasks found in the task list.
     *
     * @param matchingTasks the list of tasks that match the search keyword
     */
    public void showMatchingTasks(List<Task> matchingTasks) {
        assert matchingTasks != null : "matchingTasks list should not be null!";

        if (matchingTasks.isEmpty()) {
            System.out.println(helixSymbol + " [Helix] : No matching tasks found!");
            lastMessage = "No matching tasks found!";
        } else {
            showLine();
            System.out.println(helixSymbol + " [Helix] : Tasks with matching keywords...");
            lastMessage = "Tasks with matching keywords...\n";

            StringBuilder messageBuilder = new StringBuilder();
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.printf("    %d. %s%n", i + 1, matchingTasks.get(i));
                messageBuilder.append(String.format("    %d. %s%n", i + 1, matchingTasks.get(i)));
            }
            lastMessage += messageBuilder.toString();
            showLine();
        }
        System.out.println();
    }

    /**
     * Displays a message indicating that a task has been successfully updated.
     *
     * @param task The task that was updated.
     */
    public void showTaskUpdated(Task task) {
        System.out.println(helixSymbol + " [Helix] : Task updated successfully:\n  " + task + "\n");
        lastMessage = checkSymbol + " Task updated successfully!\n" + "    " + task;
    }

    /**
     * Retrieves the last message displayed by the ConsoleUi.
     *
     * @return the last message as a String
     */
    public String getLastMessage() {
        assert lastMessage != null : "lastMessage should not be null!";
        return lastMessage;
    }
}

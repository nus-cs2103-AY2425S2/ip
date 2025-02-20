package nana.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * The Ui class handles all interactions with the user.
 * It reads user input and displays messages to the user.
 */
public class Ui {

    private ArrayList<String> inputArrayList;
    private String rawInput;
    private Scanner scanner;
    private String[] inputList;

    /**
     * Constructs a new Ui instance.
     * Initializes the input array list and the scanner for reading user input.
     */
    public Ui() {
        this.inputArrayList = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the greeting message to the user.
     */
    public String printGreeting() {
        String s = "    ____________________________________________________________\n"
                +
                "     Hello! I'm nana.logic.Nana\n"
                +
                "     What can I do for you?\n"
                +
                "    ____________________________________________________________";
        System.out.println(s);
        return s;
    }

    /**
     * Prints the goodbye message to the user.
     */
    public String printBye() {
        String s = "    ____________________________________________________________\n"
                +
                "     Bye. Hope to see you again soon!\n"
                +
                "    ____________________________________________________________";
        System.out.println();
        return s;
    }

    /**
     * Prints the specified NanaException message to the user.
     *
     * @param e the NanaException to be printed
     */
    public static String printNanaException(NanaException e) {
        String s = "    ____________________________________________________________\n"
                +
                "     Exception: " + e.getMessage() + "\n"
                +
                "    ____________________________________________________________";
        System.out.println(s);
        return s;
    }

    /**
     * Prints the specified IOException message to the user.
     *
     * @param e the IOException to be printed
     */
    public static String printIoException(IOException e) {
        String s = "    ____________________________________________________________\n"
                +
                "     Exception: " + e.getMessage() + "\n"
                +
                "    ____________________________________________________________";
        System.out.println(s);
        return s;
    }

    /**
     * Shows a loading error message to the user.
     */
    public String showLoadingError() {
        String s = "    ____________________________________________________________\n"
                +
                "     Exception: File not found\n"
                +
                "    ____________________________________________________________";
        System.out.println(s);
        return s;
    }

    /**
     * Prints a message indicating that the specified task has been marked as done.
     *
     * @param task the task that has been marked as done
     */
    public static String printMarkTask(Task task) {
        String s = "    ____________________________________________________________\n"
                +
                "     Nice! I've marked this task as done:\n"
                +
                "       [X] " + task.getDescription() + "\n"
                +
                "    ____________________________________________________________";
        System.out.println(s);
        return s;
    }

    /**
     * Prints a message indicating that the specified task has been marked as undone.
     *
     * @param task the task that has been marked as undone
     */
    public static String printUnmarkTask(Task task) {
        String s = "    ____________________________________________________________\n"
                +
                "     Nice! I've marked this task as undone:\n"
                +
                "       [ ] " + task.getDescription() + "\n"
                +
                "    ____________________________________________________________";
        System.out.println(s);
        return s;
    }

    /**
     * Prints a message indicating that a new todo task has been added.
     *
     * @param task the task that has been added
     * @param taskCount the current number of tasks in the list
     */
    public static String printAddTodo(Task task, int taskCount) {
        String s = "    ____________________________________________________________\n"
                +
                "     Got it. I've added this task:\n"
                +
                "       " + task + "\n"
                +
                "     Now you have " + taskCount + " tasks in the list.\n"
                +
                "    ____________________________________________________________";
        System.out.println(s);
        return s;
    }

    /**
     * Prints a message indicating that a new deadline task has been added.
     *
     * @param task the task that has been added
     * @param taskCount the current number of tasks in the list
     */
    public static String printAddDeadline(Task task, int taskCount) {
        String s = "    ____________________________________________________________\n"
                +
                "     Got it. I've added this task:\n"
                +
                "       " + task + "\n"
                +
                "     Now you have " + taskCount + " tasks in the list.\n"
                +
                "    ____________________________________________________________";
        System.out.println(s);
        return s;
    }

    /**
     * Prints a message indicating that a new event task has been added.
     *
     * @param task the task that has been added
     * @param taskCount the current number of tasks in the list
     */
    public static String printAddEvent(Task task, int taskCount) {
        String s = "    ____________________________________________________________\n"
                +
                "     Got it. I've added this task:\n"
                +
                "       " + task + "\n"
                +
                "     Now you have " + taskCount + " tasks in the list.\n"
                +
                "    ____________________________________________________________";
        System.out.println(s);
        return s;
    }

    /**
     * Prints a message indicating that the specified task has been deleted.
     *
     * @param task the task that has been deleted
     * @param taskCount the current number of tasks in the list
     */
    public static String printDeleteTask(Task task, int taskCount) {
        String s = "    ____________________________________________________________\n"
                +
                "     Noted. I've removed this task:\n"
                +
                "       " + task + "\n"
                +
                "     Now you have " + taskCount + " tasks in the list.\n"
                +
                "    ____________________________________________________________";
        System.out.println(s);
        return s;
    }

    /**
     * Prints a message indicating that the specified task has been added.
     *
     * @param task the task that has been added
     */
    public static String printAddTask(Task task) {
        String s = "    ____________________________________________________________\n"
                +
                "     added: " + task.getDescription() + "\n"
                +
                "    ____________________________________________________________";
        System.out.println(s);
        return s;
    }

    /**
     * Prints the list of tasks to the user.
     *
     * @param tasks the list of tasks
     * @param taskCount the current number of tasks in the list
     */
    public static String printListTasks(ArrayList<Task> tasks, int taskCount) {
        String s = "    ____________________________________________________________\n"
                +
                "     Here are the tasks in your list:\n";

        for (int i = 0; i < taskCount; i++) {
            s += "     " + (i + 1) + "." + tasks.get(i) + "\n";
        }
        s += "    ____________________________________________________________";
        System.out.println(s);
        return s;
    }

    public static String printFindTasks(ArrayList<Task> tasks) {
        String s = "    ____________________________________________________________\n"
                +
                "     Here are the matching tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            s += "     " + (i + 1) + "." + tasks.get(i) + "\n";
        }
        s += "    ____________________________________________________________";
        System.out.println(s);
        return s;
    }

    /**
     * Reads the user input and processes it into an array list.
     */
    public void readInput() {
        String input = this.scanner.nextLine().trim();
        rawInput = input;
        inputArrayList.clear();
        inputList = input.split("\\s+");
        for (String s: inputList) {
            inputArrayList.add(s);
        }
    }

    public void readInput(String input) {
        rawInput = input;
        inputArrayList.clear();
        inputList = input.split("\\s+");
        for (String s: inputList) {
            inputArrayList.add(s);
        }
    }

    /**
     * Returns the raw user input.
     *
     * @return the raw user input
     */
    public String getRawInput() {
        return this.rawInput;
    }

    /**
     * Returns the first word of the user input, which is typically the command signal.
     *
     * @return the command signal
     */
    public String getSignal() {
        return inputArrayList.get(0);
    }

    /**
     * Returns the processed user input as an array list.
     *
     * @return the processed user input
     */
    public ArrayList<String> getInfo() {
        return inputArrayList;
    }


}

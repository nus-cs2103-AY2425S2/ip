package orim.ui;

import orim.tasklist.TaskList;

import java.util.Scanner;

public class Ui {

    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public static void showLine() { // shows a line
        System.out.println("____________________________________________________________\n");
    }

    public static void showWelcome() { // shows welcome message
        showLine();
        System.out.println("Hello! I'm Orim.\n" + "What can I do for you?\n");
        showLine();
    }

    public static void showGoodbye() { // shows goodbye message
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public static String showListing(TaskList tasks) { // shows listing of tasks
        String log = "\nHere are the tasks in your list:\n";
        for (int i = 1; i < tasks.size() + 1; i++) {
            log += i + "." + tasks.get(i - 1) + "\n";
        }
        return log;
    }

    public static void showError(String errorMessage) { // returns error message
        System.out.println(errorMessage);
    }

    public String readCommand() { // gets the next command
        return sc.nextLine();
    }


}

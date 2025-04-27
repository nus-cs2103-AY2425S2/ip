package Ninon;

import java.util.Scanner;

public class Ui {

    public static final String SPLIT = "____________________________________________________________\n";

    public Ui() {
    }

    public void greeting() {
        System.out.println("Hello! I'm NINON\n" +
                "What can I do for you?");
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public void split() {
        System.out.println(SPLIT);
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public String readCommand() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
    public void output(String respond) {
        System.out.println(SPLIT + respond + "\n" + SPLIT);
    }

    public void showLoadingError() {
        System.out.println("load failed");
    }
}

package teddy;

import java.util.Scanner;

public class Ui {

    private final Scanner sc;
    private final String SEPERATOR =  "_".repeat(60);

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void welcome() {
        System.out.println(SEPERATOR + "\nHello! I'm Teddy\nWhat can I do for you?\n" + SEPERATOR);
    }

    public void goodbye() {
        System.out.println(SEPERATOR + "\nBye! Hope to see you again soon!\n" + SEPERATOR);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void setSEPERATOR() {
        System.out.println(SEPERATOR);
    }

    public void error(String errorMessage) {
        System.out.println(SEPERATOR + "\n" + errorMessage + "\n" + SEPERATOR);
    }
}

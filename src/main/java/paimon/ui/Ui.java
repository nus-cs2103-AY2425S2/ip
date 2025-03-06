package paimon.ui;

import java.util.Scanner;

/**
 * Ui class that interacts with the user.
 * For example, it reads input from users. 
 */
public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints the greeting msg.
     */
    public void greet() {
        // greeting message
        System.out.println("Hello! I'm Paimon");
        System.out.println("What can I do for you?\n");    
    }

    /**
     * Reads the a str command from the user.
     * 
     * @return String
     */
    public String readCommand() {
        System.out.print("\nPlease enter a command: ");
        return sc.nextLine();
    }

    /**
     * Prints the str to terminal.
     * 
     * @param str
     */
    public void print(Object str) {
        System.out.println(str);
    }
    
    /** 
     * Prints the goodbye message.
     */
    public void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }


    public String getWelcomeMessage() {
        return "Hello! I'm Paimon\nWhat can I do for you?\n";
    }
}

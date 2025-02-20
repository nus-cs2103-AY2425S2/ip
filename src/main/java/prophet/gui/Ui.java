package prophet.gui;

import java.util.Scanner;

/**
 * The Ui class represents the user interface of the chatbot.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Initialises a newly created Ui object.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the welcome message of the chatbot.
     * @return the welcome message when Prophet is started up
     */
    public static String greetHello() {
        return ("Hi! Welcome to the Daily Prophet! You can call me Prophet for short.\n"
                + "For now, I can keep track of your tasks."
                + "I can do the following: \n"
                + "1. Add tasks (keywords are todo, deadline or event)\n"
                + "2. Mark tasks done or not done (keywords are mark or unmark)\n"
                + "3. List out your tasks (keyword is list)\n"
                + "4. Delete tasks (keyword is delete)\n"
                + "5. Find tasks by name (keyword is find)\n"
                + "6. See your schedule for a specific day (keyword is schedule)\n"
                + "What would you like to do?\n");
    }

    /**
     * Prints the farewell message of the chatbot.
     * @return the goodbye message when 'bye' is inputted
     */
    public static String greetGoodbye() {
        return ("Bye! Hope to see you again!\n");
    }

    /**
     * Prints the argument supplied.
     * @param message the message to be printed
     * @return the message to be printed. It is returned to the GUI Main Window
     */
    public String print(String message) {
        return (message);
    }
}

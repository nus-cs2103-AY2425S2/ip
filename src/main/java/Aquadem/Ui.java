package Aquadem;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * A class that encapsulates the User Interactions and visual output with the chatbot
 */
public class Ui {
    protected final static String bar = "____________________________________________________________";

    protected static Scanner inputter = new Scanner(System.in);

    /**
     * Default constructor for the class Ui
     */
    public Ui() {

    }

    /**
     * Asks for user input while ensuring it is not empty
     * @return
     */
    public static String generateInput(){

        while(true) {
            try {
                String currInput = inputter.nextLine();
                Parser.detailCheck(currInput);
                return currInput;
            } catch (DetailException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public static String getBar() {
        return bar;
    }

    /**
     * Prints the bar (visual element) for the chatbot
     */
    public static void printBar() {
        System.out.println(bar);
    }

    /**
     * Prints the added statement
     * @param t
     */
    public static void printAdded(Task t) {
        System.out.println("Okay : ), added: " + t );
    }

    /**
     * Prints the deleted statement
     * @param t
     */
    public static void printDeleted(Task t) {
        System.out.println("Okay : ), deleted: " + t );
    }

    /**
     * Prints the task remaining statement
     * @param tasks
     */
    public static void printRemaining(TaskList tasks) {
        System.out.println("You have " + tasks.size() + " tasks in the list ;)");
    }

    /**
     * Prints the mark statement
     * @param t
     */
    public static void printMarked(Task t) {
        System.out.println("Task marked: " + t);
    }

    /**
     * Prints the unmarked statement
     * @param t
     */
    public static void printUnmarked(Task t) {
        System.out.println("Task unmarked: " + t);
    }

    /**
     * Prints the tasklist
     * @param tasks
     */
    public static void printList(TaskList tasks) {

        for(int i = 0; i < tasks.size(); i++){
            Task t = tasks.get(i);
            String s = t.toString();
            System.out.println(i+1 + ": " + s);
        }
    }

    /**
     * Prints the deadline date statement while checking the task type
     * @param t
     */
    public static void printDate(Task t) {
        if (t instanceof Deadline) {
            System.out.println("Your deadline date is: ");
            DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
            String niceDate = t.getDate().format(format);
            System.out.println(niceDate);


        } else {
            System.out.println("This task has no deadline date");
            System.out.println(bar);
        }
    }

    /**
     * Prints the IDK message/ cant understand userinput message
     */
    public static void printError() {
        System.out.println("I don't know what that is sorry : (");
    }

    /**
     * Prints the introduction of the chatbot
     */
    public static void intro() {
        String str =  "\n" +  "Hello! I'm AquaDem\n" +
                "What can I do for you?\n" +
                "fyi: yyyy-MM-dd HH:mm is the valid date format for a deadline\n";
        System.out.println(str);
    }

    /**
     * Prints the Ending message/Exit message of the chatbot
     */
    public static void printBye() {
        System.out.println("Ok then bye!!!");
    }

    public static void printFound() {
        System.out.println("Here are the tasks matching your search: ");
    }




}

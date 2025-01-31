package Aquadem;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Ui {
    protected final static String bar = "____________________________________________________________";

    protected static Scanner inputter = new Scanner(System.in);

    public Ui() {

    }

    public static String generateInput(){

        while(true) {
            try {
                String currInput = inputter.nextLine();
                Aquadem.Parser.detailCheck(currInput);
                return currInput;
            } catch (DetailException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public static String getBar() {
        return bar;
    }

    public static void printBar() {
        System.out.println(bar);
    }

    public static void printAdded(Task t) {
        System.out.println("Okay : ), added: " + t );
    }

    public static void printDeleted(Task t) {
        System.out.println("Okay : ), deleted: " + t );
    }

    public static void printRemaining(TaskList tasks) {
        System.out.println("You have " + tasks.size() + " tasks in the list ;)");
    }

    public static void printMarked(Task t) {
        System.out.println("Task marked: " + t);
    }

    public static void printUnmarked(Task t) {
        System.out.println("Task unmarked: " + t);
    }

    public static void printList(TaskList tasks) {

        for(int i = 0; i < tasks.size(); i++){
            Task t = tasks.get(i);
            String s = t.toString();
            System.out.println(i+1 + ": " + s);
        }
    }

    public static void printDate(Aquadem.Task t) {
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

    public static void printError() {
        System.out.println("I don't know what that is sorry : (");
    }

    public static void intro() {
        String str =  "\n" +  "Hello! I'm AquaDem\n" +
                "What can I do for you?\n" +
                "fyi: yyyy-MM-dd HH:mm is the valid date format for a deadline\n";
        System.out.println(str);
    }

    public static void printBye() {
        System.out.println("Ok then bye!!!");
    }

    public static void printFound() {
        System.out.println("Here are the tasks matching your search: ");
    }




}

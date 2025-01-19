import java.util.Scanner;

public class Ui {
    private final String name;

    /* constructor for name of the bot */
    Ui(String name) {
        this.name = name;
    }

    /* greet() greets the user by introducing its name */
    public void greet() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /* exit_dialogue() exits current dialogue, and closes the bot */
    public void exitDialogue() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    /* echo(string) echoes the string parsed into the function */
    public void echo(String input) {
        System.out.println("____________________________________________________________");
        System.out.println(input);
        System.out.println("____________________________________________________________");
    }
}

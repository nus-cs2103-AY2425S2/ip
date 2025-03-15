package lebum.main;
import java.util.Scanner;

/**
 * A User interface class to interact with users and read their commands
 */
public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine().trim();
    }
    public void showWelcomeMessage() {
        System.out.println("Yo wassup! I'm Lebum");
        System.out.println("Anything the GOAT can do for you today?");
    }

    public void showByeMessage() {
        System.out.println("Bye. Hope I never see you again!");
    }

    public void showErrorMessage(Exception e) {
        System.out.println("OOPS!!! CHECK YOUR COMMAND AGAIN! " + e.getMessage());
    }
    public void showErrorMessage() {
        System.out.println("OOPS!!! CHECK YOUR COMMAND AGAIN!");
    }

    public void showErrorMessage(String solution) {
        System.out.println("OOPS!!! CHECK YOUR COMMAND AGAIN!" + solution);
    }

    public String welcome() {
        return "Hello! I'm Lebum\nAnything the GOAT can do for you?";
    }
}

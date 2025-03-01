package bebop.ui;
import java.util.Scanner;

/**
 * Ui for the Bebop Chatbot, methods to greet/goodbye and readCommands.
 */
public class Ui {
    private final Scanner scan;

    /**
     * Ui Constructor
     */
    public Ui() {
        scan = new Scanner(System.in);
    }

    /**
     * Welcomes the Guest and explains the format
     */
    public void welcomeGuest() {
        String logo = "\t _          _\n"
                + "\t| |        | |\n"
                + "\t| |__   ___| |__   ___  _ __\n"
                + "\t| '_ \\ / _ \\ '_ \\ / _ \\| '_ \\\n"
                + "\t| |_) |  __/ |_) | (_) | |_) |\n"
                + "\t|_.__/ \\___|_.__/ \\___/| .__/\n"
                + "\t                       | |\n"
                + "\t                       |_|" + "\n\tWhat will you be doing today?\n"
                + "\t__________________________________";
        System.out.println("\tHowdy! How's it going?\n" + logo);
        System.out.println("\tTODO : todo EVENTNAME");
        System.out.println("\tDEADLINE : deadline EVENTNAME /by ENDTIME");
        System.out.println("\tEVENT : event EVENTNAME /from STARTTIME /to ENDTIME");
    }

    /**
     * Adds a divider between commands.
     */
    public void divider() {
        System.out.println("\t__________________________________");
    }

    /**
     * Reads the next user input.
     */
    public String readCommand() {
        return scan.nextLine();
    }

    /**
     * Error message.
     *
     * @param m is the error message/fix.
     */
    public void showError(String m) {
        System.out.println("ERROR");
        System.out.println("m");
    }

}

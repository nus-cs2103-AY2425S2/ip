package universe;

import java.util.Scanner;

/**
 * Responsible for greeting the user, waiting for user input and saying goodbye.
 *
 * @author yuqing-tham
 */
public class Ui {
    private static final String INTRO = "Welcome to the Universe!\n"
            + "What cosmic quest can I assist you with today?\n"
            + "Baby travellers, please input the command 'help' to retrieve the list of approved commands.\n";
    private static final String BYE = "Farewell, Universe traveller. May our paths intersect again soon!";
    private final Scanner scanner;

    /**
     * Constructs a new Ui that takes in keyboard input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the greeting message.
     */
    public void greet() {
        System.out.println(INTRO);
    }

    /**
     * Returns the greeting message.
     */
    public String getGreeting() {
        return INTRO;
    }

    /**
     * Prints the goodbye message.
     */
    public void bye() {
        System.out.println(BYE);
    }

    /**
     * Checks for keyboard input by the user.
     *
     * @return A String containing the user's response.
     */
    public String getResponse() {
        return scanner.nextLine();
    }
}

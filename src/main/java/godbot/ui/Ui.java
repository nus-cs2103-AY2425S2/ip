package godbot.ui;

import java.util.Scanner;

/**
 * Handles all user interactions, including displaying messages and reading user input.
 * Provides methods to show welcome and goodbye messages, display custom messages,
 * and read commands from the user.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a new {@code Ui} object with an initialized {@link Scanner} for reading user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message along with the GodBot logo when the program starts.
     */
    public String showWelcomeMessage() {
        String logo = "  ________           ._____________        __    \n"
                     + " /  _____/  ____   __| _/\\______   \\ _____/  |_  \n"
                     + "/   \\  ___ /  _ \\ / __ |  |    |  _//  _ \\   __\\ \n"
                     + "\\    \\_\\  (  <_> ) /_/ |  |    |   (  <_> )  |   \n"
                     + " \\______  /\\____/\\____ |  |______  /\\____/|__|   \n"
                     + "        \\/            \\/         \\/              \n";
        return "Speak, puny mortal.\n" + logo;
    }

    /**
     * Displays the goodbye message when the program ends.
     */
    public String showGoodbyeMessage() {
        return "Begone mortal.";
    }

    /**
     * Displays a custom message to the user.
     *
     * @param message The message to be displayed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Reads a command from the user input.
     *
     * @return The command entered by the user as a {@code String}.
     */
    public String readCommand() {
        return scanner.nextLine();
    }
}


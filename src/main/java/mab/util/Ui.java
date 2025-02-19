package mab.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Manages user interaction including input handling and formatted output display.
 * Handles console I/O operations with consistent visual formatting.
 */
public class Ui {
    BufferedReader reader;

    private static final String GREETING_STRING = 
        """
        ██╗  ██╗███████╗██╗     ██╗      ██████╗  ██╗        ██╗███╗   ███╗    ███╗   ███╗ █████╗ ██████╗ 
        ██║  ██║██╔════╝██║     ██║     ██╔═══██╗ ██║        ██║████╗ ████║    ████╗ ████║██╔══██╗██╔══██╗
        ███████║█████╗  ██║     ██║     ██║   ██║ ██║        ██║██╔████╔██║    ██╔████╔██║███████║██████╔╝
        ██╔══██║██╔══╝  ██║     ██║     ██║   ██║ ╚═╝        ██║██║╚██╔╝██║    ██║╚██╔╝██║██╔══██║██╔══██╗
        ██║  ██║███████╗███████╗███████╗╚██████╔╝ ██╗        ██║██║ ╚═╝ ██║    ██║ ╚═╝ ██║██║  ██║██████╔╝
        ╚═╝  ╚═╝╚══════╝╚══════╝╚══════╝ ╚═════╝  ╚═╝        ╚═╝╚═╝     ╚═╝    ╚═╝     ╚═╝╚═╝  ╚═╝╚═════╝ 


        how can i help you?
        """;


    private static final String GOODBYE_STRING = 
        """
        ╭───────────────────╮
        │     Goodbye!      │
        │  See you later!   │
        ╰───────────────────╯
        """;

    private static final String DIVIDER = "\n============================================================================\n";

    /**
     * Initializes user input reader for console interaction.
     */
    public Ui() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

     /**
     * Prints horizontal divider line for output organization.
     */
    public void divider() {
        System.out.println(DIVIDER);
    }

     /**
     * Displays the application's welcome message with ASCII art branding.
     */
    public void showGreeting() {
        System.out.println(GREETING_STRING);
    }

    /**
     * Displays farewell message upon application exit.
     */
    public void showGoodbye() {
        System.out.println(GOODBYE_STRING);
    }

    /**
     * Reads user input from console.
     * 
     * @return Trimmed input string, empty string on I/O error
     */
    public String readCommand() {
        try {
            return reader.readLine().trim(); //removes trailing spaces so that the DateTime utility can parse the date
        } catch (IOException e) {
            System.out.println("An error occurred while reading the command");
            return "";
        }
    }

}

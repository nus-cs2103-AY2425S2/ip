package bob.cli;

import java.util.Scanner;

import bob.util.Formatter;

/**
 * This class deals with user interactions, both input and output.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";
    private static final String LOGO = """
                 ____     _____    ____
                |  _  \\  |     |  |  _  \\
                | |_| /  |  _  |  | |_| /
                |____/   | | | |  |____/      / \\/ \\
                |  _  \\  | |_| |  |  _  \\     \\    /
                | |_| /  |     |  | |_| /      \\  /
                |____/   |_____|  |____/        \\/
                """;

    private Scanner scanner;

    /**
     * Constructs a new Ui object and initialises Scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the next command from the user.
     *
     * @return trimmed version of the user input (String)
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Accepts one string as input, and
     * Will print a separation line before and after the input.
     *
     * @param s String to be printed.
     */
    public void print(String s) {
        StringBuilder tmp = new StringBuilder();
        tmp.append("\t").append(LINE).append("\n").append(s).append("\n").append(LINE);
        String output = tmp.toString().replaceAll("\n", "\n\t");
        System.out.println(output);
    }

    /**
     * Prints the welcome message (Bob's Logo) when Bob starts up.
     */
    public void printWelcome() {
        System.out.println(LOGO);
        this.print(Formatter.format("Hello! I'm Bob!", "What can I do for you?"));
    }

    /**
     * Closes the scanner object. Called when program exits.
     */
    public void closeScanner() {
        scanner.close();
    }
}

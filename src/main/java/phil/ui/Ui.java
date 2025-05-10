package phil.ui;

import java.util.Scanner;

import phil.exception.PhilException;
import phil.main.Parser;

/**
 * Represents the text-based user interface for interacting with the chatbot.
 */
public class Ui {

    private Parser parser;

    /**
     * Sets up the Ui object.
     *
     * @param parser Parser to be used by the Ui.
     */
    public Ui(Parser parser) {
        this.parser = parser;
    }

    /**
     * Prints the given output with additional lines to separate the output.
     *
     * @param output output to be printed.
     */
    public void printOutput(String output) {
        System.out.println("\n-----------------------------------------\n");
        System.out.println(output);
        System.out.println("\n-----------------------------------------\n");
    }

    /**
     * Runs the user interface of the bot, including receiving input and printing output.
     */
    public void runBot() {
        Scanner sc = new Scanner(System.in);
        printOutput("Hello. I'm phil.main.Phil.\nWhat can I do for you?"
                    + "\nTip: say 'list' to get a list of actions to do, "
                    + "say 'bye' to end the conversation.");

        while (sc.hasNextLine()) {
            try {
                String input = sc.nextLine();
                printOutput(parser.generateStringOutputFromInput(input));
                if (input.equals("bye")) {
                    break;
                }
            } catch (PhilException e) {
                printOutput(e.getMessage());
            }
        }
    }
}

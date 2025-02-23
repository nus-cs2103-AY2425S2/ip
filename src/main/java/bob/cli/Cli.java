package bob.cli;

import bob.command.ExitCommand;
import bob.core.Bob;
import bob.util.Formatter;

/**
 * This class is the entry point of the Bob program (CLI).
 */
public class Cli {

    /**
     * Acts as main entry point of CLI application.
     * <p>
     * This method initializes the user interface, prints the welcome message,
     * and enters an infinite loop to read and process user commands.
     * It handles various exceptions, such as invalid commands and input errors,
     * and provides feedback to the user through the {@link Ui} class.
     * </p>
     */
    public static void main(String[] args) {

        Ui ui = new Ui();
        ui.printWelcome();

        try {
            while (true) {
                String userInput = ui.readCommand();
                String output = Bob.processUserInput(userInput);

                if (output.isEmpty()) {
                    return;
                }

                ui.print(output);
            }
        } catch (Exception e) {
            ui.print(Formatter.format("Unexpected error occurred."));
        } finally {
            ui.print(ExitCommand.EXIT_MESSAGE);
            ui.closeScanner();
            System.exit(0);
        }
    }
}

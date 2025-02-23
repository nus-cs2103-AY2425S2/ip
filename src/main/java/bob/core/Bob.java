package bob.core;

import java.io.IOException;

import bob.command.Command;
import bob.command.WrongCommandException;
import bob.parser.Parser;
import bob.util.Formatter;

/**
 * This class is the core of the Bob program.
 * Used by both CLI (bob.cli.Cli) and GUI (bob.gui.MainWindow).
 */
public class Bob {

    /**
     * Processes the user input and returns the output.
     *
     * @param input a String.
     * @return a String representing the output.
     */
    public static String processUserInput(String input) {
        try {
            Command c = Parser.parse(input);
            assert c != null : "Command should not be null";

            String output = c.execute();
            assert output != null : "Output should not be null";

            return output;

        } catch (WrongCommandException e) {
            return Formatter.format(e.getMessage(), "Please try again!");
        } catch (IndexOutOfBoundsException e) {
            return Formatter.format("Uh oh! Bob says...one of the task numbers does not exist :(");
        } catch (NumberFormatException e) {
            return Formatter.format("Uh oh! Bob says...task number(s) must be integers :(");
        } catch (IOException e) {
            return Formatter.format("Uh oh! Bob says...I'm sorry, there was an error saving the task :(");
        } catch (Exception e) {
            return Formatter.format("Uh oh! Bob says...I'm sorry, there was an error :(");
        }
    }
}

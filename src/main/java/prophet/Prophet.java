package prophet;

import java.util.stream.Stream;

import prophet.command.Command;
import prophet.exception.InvalidTaskNumberException;
import prophet.exception.NoDescriptionException;
import prophet.exception.ProphetException;
import prophet.gui.Ui;
import prophet.parser.Parser;
import prophet.storage.Save;
import prophet.storage.Storage;

/**
 * The Prophet class is the main class that runs the chatbot.
 */

public class Prophet {
    private static Storage storage;
    private static Ui ui;

    /**
     * Initialises a newly created Prophet object.
     */
    public Prophet() {
        Prophet.storage = new Storage();
        Prophet.ui = new Ui();
        Save.load(ui, Prophet.storage);
    }

    /**
     * Returns the size of storage of tasks.
     * @return the size of storage of tasks
     */
    public static int getStorageSize() {
        return storage.getListSize();
    }

    public static void main(String[] args) {
    }

    /**
     * Generates a response for the user's chat message. Parses the input and does as user inputs,
     * returning a string that will be printed by the GUI.
     * @param input the user's chat message
     */
    public String getResponse(String input) {
        try {
            Stream<Command> command = Parser.parse(input);
            StringBuilder response = new StringBuilder();
            command.forEach(c -> {
                try {
                    response.append(c.execute(ui, Prophet.storage));
                } catch (InvalidTaskNumberException e) {
                    response.append("Please enter a valid integer!");
                } catch (NoDescriptionException e) {
                    response.append("Please enter a valid command.");
                }
            });
            Save.save(Prophet.ui, Prophet.storage);
            if (input.equalsIgnoreCase("bye")) {
                return Ui.greetGoodbye();
            }
            return response.toString();
        } catch (ProphetException e) {
            return e.getMessage();
        } catch (NumberFormatException e) {
            return "Please enter a valid integer!";
        }
    }
}

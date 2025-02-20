package adam.core;

import adam.command.Command;
import adam.exceptions.AdamException;
import adam.parser.Parser;

/**
 * Represents the chatbot Adam.
 */
public class Adam {
    /** Name used in greeting */
    private static final String CHATBOT_NAME = "Adam";

    /** An instance of TaskList handling the information of current tasks */
    private TaskList manager;

    /**
     * Constructor for adam.core.Adam.
     *
     * Initializes the chatbot with a new Ui and a new TaskList.
     */
    public Adam() {
        this.manager = new TaskList(new Storage());
    }

    /**
     * Gets the greeting from the chatbot.
     *
     * @return Greeting from the chatbot.
     */
    public String getGreeting() {
        return "Hello! I'm " + CHATBOT_NAME + ". What can I do for you today?";
    }

    /**
     * Gets the response from the chatbot.
     *
     * @param input User input.
     * @return Response from the chatbot.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parseInput(input);
            return c.execute(manager);
        } catch (AdamException e) {
            assert !e.getMessage().isEmpty() : "Exception should have a message";
            return "Oh no! " + e;
        }
    }

    /**
     * Checks if the user input is an exit command.
     *
     * @param input User input.
     * @return True if the user input is an exit command, false otherwise.
     */
    public boolean checkIsExit(String input) {
        try {
            Command c = Parser.parseInput(input);
            return c.isExit();
        } catch (AdamException e) {
            return false;
        }
    }
}

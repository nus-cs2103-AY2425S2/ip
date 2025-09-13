package universe.response;

import universe.Checklist;
import universe.exceptions.EmptyResponseException;
import universe.exceptions.InvalidResponseException;
import universe.exceptions.MissingDescriptionException;
import universe.exceptions.MissingFilterDateException;
import universe.exceptions.MissingIndexException;
import universe.exceptions.MissingKeywordException;
import universe.exceptions.MissingListItemException;
import universe.exceptions.MissingStartAndEndException;
import universe.exceptions.MissingTimeException;
import universe.exceptions.UniverseException;

/**
 * Responsible for checking if a user input contains all the necessary details.
 *
 * @author yuqing-tham
 */
public class ResponseChecker {
    private final String response;
    private final Checklist chores;

    /**
     * Constructs a ResponseChecker with the specified user response and chores list.
     *
     * @param response A String response by the user.
     * @param chores The Checklist containing all the existing Chores.
     */
    public ResponseChecker(String response, Checklist chores) throws EmptyResponseException {
        this.response = response;
        this.chores = chores;
        if (response.isEmpty()) {
            throw new EmptyResponseException();
        }
    }

    /**
     * Checks the user input and calls the corresponding function to check respond specific details.
     */
    public void handleError() throws UniverseException {
        UserInputParser parser = new UserInputParser(response);
        String[] parts = parser.splitCommand();
        String command = parser.getCommand();

        switch (command) {
        case "todo", "deadline", "event" -> handleAddChoreError(parts, command);
        case "check", "uncheck", "remove" -> handleMarkDoneAndRemoveError(parts);
        case "filter", "find" -> handleFilterAndFindError(parts, command);
        case "list", "clear", "hi", "help", "bye" -> { }
        default -> throw new InvalidResponseException();
        }
    }

    /**
     * Checks the add Chore commands and throws the corresponding exceptions.
     */
    public void handleAddChoreError(String[] parts, String command) throws UniverseException {
        if (parts.length < 2) {
            throw new MissingDescriptionException();
        } else if (command.equals("deadline") && !(response.contains("by"))) {
            throw new MissingTimeException();
        } else if (command.equals("event")) {
            if (!(response.contains("from")) || !(response.contains("to"))) {
                throw new MissingStartAndEndException();
            }
        }
    }

    /**
     * Checks the check, uncheck and remove Chore commands and throws the corresponding exceptions.
     */
    public void handleMarkDoneAndRemoveError(String[] parts) throws UniverseException {
        if (parts.length < 2) {
            throw new MissingIndexException();
        } else {
            int choreIndex = Integer.parseInt(parts[1]);
            if (choreIndex < 1 || choreIndex > chores.getSize()) {
                throw new MissingListItemException();
            }
        }
    }

    /**
     * Checks the filter and find commands and throws the corresponding exceptions.
     */
    public void handleFilterAndFindError(String[] parts, String command) throws UniverseException {
        if (parts.length < 2) {
            if (command.equals("filter")) {
                throw new MissingFilterDateException();
            } else {
                throw new MissingKeywordException();
            }
        }
    }
}

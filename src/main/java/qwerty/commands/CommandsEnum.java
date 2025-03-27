package qwerty.commands;

import qwerty.exceptions.BotException;
import qwerty.exceptions.UnknownCommandException;

/**
 * Enum representing the various commands available to the bot.
 * Each constant in the enum corresponds to a specific command type.
 */
public enum CommandsEnum {
    bye,
    list,
    mark,
    unmark,
    delete,
    todo,
    deadline,
    event,
    loan,
    find;

    /**
     * Checks if the provided command string corresponds to a valid command in the enum.
     *
     * @param command The command string to check.
     * @throws BotException If the command is not recognized.
     */
    public static void contains(String command) throws BotException {
        try {
            valueOf(command); // Tries to map the string to an enum value.
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException(); // Throws exception if the command is invalid.
        }
    }
}

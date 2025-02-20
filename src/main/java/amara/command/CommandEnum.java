package amara.command;

import amara.exceptions.AmaraException;

/**
 * {@code enum} class to make the various {@code switch-case} more understandable.
 */
public enum CommandEnum {
    BYE,
    LIST,
    MARK,
    UNMARK,
    TODO,
    DEADLINE,
    EVENT,
    DELETE,
    FIND,
    HELP,
    SORT;

    /**
     * Converts a given string into the corresponding {@code enum}.
     *
     * @param commandString
     * @return Enumeration of the provided string.
     * @throws AmaraException
     */
    public static CommandEnum fromString(String commandString) throws AmaraException {
        try {
            return CommandEnum.valueOf(commandString.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw AmaraException.invalidCommand();
        }
    }
}

package steve.commands;

/**
 * Enum representing the different types of commands that can be executed.
 * This enum defines various command types, such as list, mark, unmark, todo, etc.
 * Each value corresponds to a specific command in the task management system.
 */
public enum CommandType {
    List, Mark, Unmark, Todo, Deadline, Event, Delete, Bye, Unknown
}

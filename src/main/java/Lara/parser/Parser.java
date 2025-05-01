/**
 * The Parser class is responsible for interpreting user input commands
 * and converting them into executable program actions.
 *
 * Methods include:
 * - handleCommand(String, TaskList, Ui, Storage) : Processes input and returns the appropriate command.
 * @author Maliha Haque
 * @version 1.0
 */

package Lara.parser;

import Lara.ui.Task;
import Lara.storage.Storage;
import Lara.ui.TaskList;
import Lara.ui.Ui;
import Lara.exception.LaraException;

public class Parser {
    public String handleCommandAndReturn(String command, TaskList tasks, Ui ui, Storage storage) throws LaraException {
        command = command.trim();

        String[] words = command.split(" ", 2);
        String action = words[0];

        try {
            switch (action) {
                case "hello":
                    return "How can I help you?";
                case "bye":
                    return "Goodbye! Have a great day!";
                case "help":
                    return ui.showHelp();
                case "list":
                    return tasks.getTaskList(tasks);
                case "mark":
                    if (words.length < 2) {
                        throw new LaraException("Please specify a task number to mark.");
                    }
                    String markResult = tasks.markTask(words[1].trim());
                    storage.save(tasks.getTasks());
                    return markResult;
                case "unmark":
                    if (words.length < 2) {
                        throw new LaraException("Please specify a task number to unmark.");
                    }
                    String unmarkResult = tasks.unmarkTask(words[1].trim());
                    storage.save(tasks.getTasks());
                    return unmarkResult;
                case "delete":
                    if (words.length < 2) {
                        throw new LaraException("Please specify a task number to delete.");
                    }
                    String deleteResult = tasks.deleteTask(words[1].trim());
                    storage.save(tasks.getTasks());
                    return deleteResult;
                case "sort":
                    return tasks.sortTasks();
                case "todo":
                case "deadline":
                case "event":
                    if (words.length < 1) {
                        throw new LaraException("Please provide details for the task.");
                    }
                    String taskResult = tasks.addTask(command);
                    storage.save(tasks.getTasks());
                    return taskResult;
                case "find":
                    if (words.length < 2) {
                        throw new LaraException("Please provide a keyword to search for.");
                    }
                    return tasks.findTasks(tasks, words[1].trim());
                default:
                    throw new LaraException("I do not understand what you mean! Try typing \"help\" to see the list of available commands!");
            }
        } catch (LaraException e) {
            return "Sorry! " + e.getMessage();
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Please enter a valid command format!";
        }
    }
}
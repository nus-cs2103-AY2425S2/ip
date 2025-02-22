package command;

import task.TaskList;
import task.Todo;
import task.Deadline;
import task.Event;
import task.Task;
import ui.Ui;
import exception.SamanthaException;
import exception.CommandException;

/**
 * The Parser class is responsible for parsing user commands
 * and executing the corresponding actions on the TaskList.
 */
public class Parser {
    /**
     * Parses the user input command and executes the corresponding action.
     *
     * @param command The user input command as a String.
     * @param tasks The TaskList that stores all tasks.
     * @param ui The UI instance for displaying messages.
     * @throws SamanthaException If the command is invalid.
     */
    public static String parseCommand(String command, TaskList tasks, Ui ui) throws SamanthaException {
        if (command.equals("list") || command.equals("l")) {
            return ui.showMessage(tasks.listTasks());
        } else if (command.startsWith("mark ")) {
            int taskIndex = Integer.parseInt(command.substring(5)) - 1;
            assert taskIndex >= 0 && taskIndex < tasks.getSize() : "Invalid task index";
            tasks.markTask(taskIndex, true);
            return ui.showMessage("Nice! I've marked this task as done:\n    " + tasks.getTask(taskIndex));
        } else if (command.startsWith("m ")) {
            int taskIndex = Integer.parseInt(command.substring(2)) - 1;
            assert taskIndex >= 0 && taskIndex < tasks.getSize() : "Invalid task index";
            tasks.markTask(taskIndex, true);
            return ui.showMessage("Nice! I've marked this task as done:\n    " + tasks.getTask(taskIndex));
        } else if (command.startsWith("unmark ")) {
            int taskIndex = Integer.parseInt(command.substring(7)) - 1;
            assert taskIndex >= 0 && taskIndex < tasks.getSize() : "Invalid task index";
            tasks.markTask(taskIndex, false);
            return ui.showMessage("OK, I've marked this task as not done yet:\n    " + tasks.getTask(taskIndex));
        } else if (command.startsWith("u ")) {
            int taskIndex = Integer.parseInt(command.substring(2)) - 1;
            assert taskIndex >= 0 && taskIndex < tasks.getSize() : "Invalid task index";
            tasks.markTask(taskIndex, false);
            return ui.showMessage("OK, I've marked this task as not done yet:\n    " + tasks.getTask(taskIndex));
        } else if (command.startsWith("delete ")) {
            int taskIndex = Integer.parseInt(command.substring(7)) - 1;
            assert taskIndex >= 0 && taskIndex < tasks.getSize() : "Invalid task index";
            Task removedTask = tasks.removeTask(taskIndex);
            return ui.showMessage("OK. I've removed this task:\n    " + removedTask + "\nNow you have " + tasks.getSize() + " tasks in the list.");
        } else if (command.startsWith("d ")) {
            int taskIndex = Integer.parseInt(command.substring(2)) - 1;
            assert taskIndex >= 0 && taskIndex < tasks.getSize() : "Invalid task index";
            Task removedTask = tasks.removeTask(taskIndex);
            return ui.showMessage("OK. I've removed this task:\n    " + removedTask + "\nNow you have " + tasks.getSize() + " tasks in the list.");
        } else if (command.startsWith("todo ")) {
            assert command.length() > 5 : "Todo command must have a description";
            tasks.addTask(new Todo(command.substring(5)));
            return ui.showMessage("Got it. I've added this task:\n    " + tasks.getLastTask() + "\nNow you have " + tasks.getSize() + " tasks in the list.");
        } else if (command.startsWith("t ")) {
            assert command.length() > 2 : "Todo command must have a description";
            tasks.addTask(new Todo(command.substring(2)));
            return ui.showMessage("Got it. I've added this task:\n    " + tasks.getLastTask() + "\nNow you have " + tasks.getSize() + " tasks in the list.");
        } else if (command.startsWith("deadline ")) {
            String[] parts = command.substring(9).split(" /by ");
            assert parts.length == 2 : "Deadline command format incorrect";
            tasks.addTask(new Deadline(parts[0], parts[1]));
            return ui.showMessage("Got it. I've added this task:\n    " + tasks.getLastTask() + "\nNow you have " + tasks.getSize() + " tasks in the list.");
        } else if (command.startsWith("de ")) {
            String[] parts = command.substring(3).split(" /by ");
            assert parts.length == 2 : "Deadline command format incorrect";
            tasks.addTask(new Deadline(parts[0], parts[1]));
            return ui.showMessage("Got it. I've added this task:\n    " + tasks.getLastTask() + "\nNow you have " + tasks.getSize() + " tasks in the list.");
        } else if (command.startsWith("event ")) {
            String[] parts = command.substring(6).split(" /from | /to ");
            assert parts.length == 3 : "Event command format incorrect";
            tasks.addTask(new Event(parts[0], parts[1], parts[2]));
            return ui.showMessage("Got it. I've added this task:\n    " + tasks.getLastTask() + "\nNow you have " + tasks.getSize() + " tasks in the list.");
        }  else if (command.startsWith("e ")) {
            String[] parts = command.substring(2).split(" /from | /to ");
            assert parts.length == 3 : "Event command format incorrect";
            tasks.addTask(new Event(parts[0], parts[1], parts[2]));
            return ui.showMessage("Got it. I've added this task:\n    " + tasks.getLastTask() + "\nNow you have " + tasks.getSize() + " tasks in the list.");
        } else if (command.startsWith("find ")) {
            String keyword = command.substring(5).trim();
            assert !keyword.isEmpty() : "Find command must have a keyword";
            return ui.showMessage(tasks.findTasks(keyword));
        } else if (command.startsWith("f ")) {
            String keyword = command.substring(2).trim();
            assert !keyword.isEmpty() : "Find command must have a keyword";
            return ui.showMessage(tasks.findTasks(keyword));
        } else {
            throw new CommandException("Your command beyond my ability.");
        }
    }
}
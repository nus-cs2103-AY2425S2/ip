package amara.command;

import java.util.ArrayList;

import amara.storage.Storage;
import amara.task.Task;
import amara.ui.Ui;

/**
 * * A {@link Command} implementation that returns all command formats
 */
public class HelpCommand extends Command {
    @Override
    public String execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        return "Here are the list of available commands!\n"
                + "  <bye>: See you next time!\n\n"
                + "  <list>: I'll print out all of the tasks in the list!\n\n"
                + "  <mark index>: I'll mark the task as done on that index!\n\n"
                + "  <unmark index>: I'll unmark the task as done on that index!\n\n"
                + "  <todo description>: I'll add a new To Do task to your list!\n\n"
                + "  <deadline description /by date-time format>: I'll add a new Deadline task to your list!\n\n"
                + "  <event description /from date-time format /to date-time format>:"
                + " I'll add a new event task to your list!\n\n"
                + "  <delete Index>: I'll remove a task from the given index!\n\n"
                + "  <find query>: I'll return a list of tasks of the word you want!\n\n"
                + "  <Sort>: I'll sort your task list by To-Do, Deadline and Events, I'll also"
                + " breakt ties for Deadlines and Events by their due date and start times!\n\n"

                + "Note the following format!\n"
                + "  index: Integer between 0 and size of list.\n"
                + "  date-time: <YYYY-MM-DD HHMM>\n"
                + "  description: non-empty string.";
    }
}

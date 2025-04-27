package a.parser;

import a.exception.AException;
import a.task.*;
import a.tasklist.TaskList;
import a.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Handles parsing and executing user commands for task management.
 */

public class Parser {


    /**
     * Parses and executes the user command.
     *
     * @param input The user input command.
     * @param ui The UI instance for displaying messages.
     * @param list The TaskList instance containing tasks.
     * @return {@code false} if the command is "bye" (to exit the program), otherwise {@code true}.
     */
    public static void parse(String input, Ui ui, TaskList list) {
        assert ui != null : "UI should not be null";
        try {
            if (input.equals("bye")) {
                ui.bye();
                System.exit(0);
            }
            else if (input.startsWith("find ")) {
                String keyword = input.substring(5);
                int count = 1;
                String s = "Here are the matching tasks in your list:\n";
                for (Task task : list.getTasks()) {
                    if (task.getDescription().contains(keyword)) {
                        s = s + count + "." + task + "\n";
                        count++;
                    }
                }
                ui.showMessage(s);
                if (count == 1) {
                    ui.showMessage("No matching tasks found.");
                }
            } else if (input.equals("list")) {
                if (list.size() == 0) {
                    throw new AException("Your task list is empty.");
                }
                String s = "";
                for (int i = 0; i < list.size(); i++) {
                    s = s + (i + 1) + "." + list.get(i) +"\n";
                }
                ui.showMessage("Here are the tasks in your list:\n" + s);


            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                if (index < 0 || index >= list.size()) {
                    throw new AException("Task number out of range.");
                }
                list.get(index).markAsDone();
                ui.showMessage("Nice! I've marked this task as done:\n" + list.get(index));

            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                if (index < 0 || index >= list.size()) {
                    throw new AException("Task number out of range.");
                }
                list.get(index).unmark();
                ui.showMessage("OK, I've marked this task as not done yet:\n" + list.get(index));

            } else if (input.startsWith("todo ")) {
                String description = input.substring(5);
                if (description.isEmpty()) {
                    throw new AException("The description of a todo cannot be empty.");
                }
                list.add(new Todo(description));
                ui.showMessage("Got it. I've added this task:\n" + " " + list.get(list.size() - 1) + "\n" + "Now you have " + list.size() + " tasks in the list.");
            } else if (input.startsWith("deadline ")) {
                String[] parts = input.substring(9).split(" /by ", 2);
                if (parts.length < 2) {
                    throw new AException("Invalid format! Correct format: deadline <description> /by <YYYY-MM-DD>");
                }
                try {
                    LocalDate date = LocalDate.parse(parts[1]);
                    list.add(new Deadline(parts[0], date.toString()));
                    ui.showMessage("Got it. I've added this task:\n" + " " + list.get(list.size() - 1) + "\n" + "Now you have " + list.size() + " tasks in the list.");
                } catch (DateTimeParseException e) {
                    throw new AException("Invalid date format! Please use YYYY-MM-DD.");
                }
            } else if (input.startsWith("event ")) {
                String[] eventParts = input.substring(6).split(" /from ", 2);
                if (eventParts.length < 2) {
                    throw new AException("Invalid format! Correct format: event <description> /from <start date> /to <end date>");
                }
                String description = eventParts[0];
                String[] time = eventParts[1].split(" /to ", 2);
                if (time.length < 2) {
                    throw new AException("Invalid format! Correct format: event <description> /from <start date> /to <end date>");
                }
                try {
                    LocalDate start = LocalDate.parse(time[0]);
                    LocalDate end = LocalDate.parse(time[1]);
                    list.add(new Event(description, start.toString(), end.toString()));
                    ui.showMessage("Got it. I've added this task:\n" + " " + list.get(list.size() - 1) + "\n" + "Now you have " + list.size() + " tasks in the list.");
                } catch (DateTimeParseException e) {
                    throw new AException("Invalid date format! Please use YYYY-MM-DD.");
                }
            } else if (input.startsWith("delete ")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                if (index < 0 || index >= list.size()) {
                    throw new AException("Task number out of range.");
                } else {
                    Task removedTask = list.remove(index);
                    ui.showMessage("Noted. I've removed this task:\n" + " " + removedTask + "\n" + "Now you have " + list.size() + " tasks in the list.");
                }
            } else if (input.startsWith("fixed ")) {
                String[] parts = input.substring(6).split(" /for ");
                if (parts.length < 2) {
                    throw new AException("Please provide a valid description and duration.");
                }
                String description = parts[0];
                int duration;
                try {
                    duration = Integer.parseInt(parts[1]);
                } catch (NumberFormatException e) {
                    throw new AException("Please enter a valid number for the duration.");
                }
                list.add(new FixedDurationTask(description, duration));
                ui.showMessage("Got it. I've added this task:\n" + " " + list.get(list.size() - 1) + "\n" + "Now you have " + list.size() + " tasks in the list.");
            } else {
                ui.showMessage("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

        } catch (AException e) {
            ui.showError(e.getMessage());
        } catch (NumberFormatException e) {
            ui.showError("Please enter a valid number for marking tasks.");
        }

    }

}

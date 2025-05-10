package parser;
import commands.AddCommand;
import commands.ByeCommand;
import commands.Commands;
import commands.DeleteCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.UnmarkCommand;
import commands.FindCommand;
import commands.UpdateCommand;
import command.Command;
import task.Deadlines;
import task.Events;
import task.ToDos;
import task.Task;
import command.Section;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    /**
     * Parses the user input and returns the corresponding command to execute.
     * If the input is unclear or invalid, null is returned.
     *
     * @param response String input by the user
     * @return Commands command to execute.
     */
    public static Commands executeCommand(String response){
        assert response != null : "Input command should not be null";
        assert !response.trim().isEmpty() : "Input command should not be empty";

        Command cmd;
        try {
            cmd = Command.valueOf(response.split("\\s+")[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid command! Try again.");
            return null;
        }

        switch (cmd){
            case UPDATE:
                try {
                    Section section = Section.valueOf(response.split("\\s+")[1].split("\\s+")[0].
                            trim().toUpperCase());
                    int i = Integer.parseInt((String)response.split("of ")[1].split(" to")[0].trim());
                    String update = response.split("to ")[1];
                    return new UpdateCommand(section, i - 1, update);

                } catch (Exception e) {
                    update();
                }
                break;

            case FIND:
                try {
                    String des = response.split("\\s+")[1];
                    assert !des.isEmpty() : "Find command should have a search term";
                    return new FindCommand(des);

                } catch (Exception e) {
                    System.out.println("OOPS, please valid input after find: find [term]");
                }
                break;

            case BYE:
                return new ByeCommand();

            case LIST:
                return new ListCommand();

            case MARK:
                try {
                    assert response.length() > 5 : "Mark command should have an index";
                    int i = Integer.parseInt((String) response.subSequence(5, response.length()));
                    assert i > 0 : "Task index should be positive";
                    return new MarkCommand(i - 1);

                } catch (Exception e) {
                    System.out.println("OOPS, please provide a valid number after mark: mark [index]");
                }
                break;

            case UNMARK:
                try {
                    assert response.length() > 7 : "Unmark command should have an index";
                    int i = Integer.parseInt((String) response.subSequence(7, response.length()));
                    assert i > 0 : "Task index should be positive";
                    return new UnmarkCommand(i - 1);

                } catch (Exception e) {
                    System.out.println("OOPS, please provide a valid number after unmark: unmark [index]");
                }
                break;

            case DELETE:
                try {
                    assert response.length() > 7 : "Delete command should have an index";
                    int i = Integer.parseInt((String) response.subSequence(7, response.length()));
                    assert i > 0 : "Task index should be positive";
                    return new DeleteCommand(i - 1);

                } catch (Exception e) {
                    System.out.println("OOPS, please provide a valid number after delete: delete [index]");
                }
                break;

            case TODO:
                try {
                    String x = response.substring(5).trim();

                    if (x.isEmpty()) {
                        throw new IllegalArgumentException("To-do description should not be empty.");
                    }

                    ToDos c = new ToDos(x);
                    return new AddCommand(c);

                } catch (Exception e) {
                    todo();
                }
                break;

            case DEADLINE:
                try {
                    assert response.contains("/by") : "Deadline command must contain '/by'";
                    String des = response.split("deadline ")[1].split("/by")[0].strip();
                    String date = response.split("/by")[1].strip();
                    LocalDateTime by = LocalDateTime.parse(date.replace(" ", "T") + ":00");
                    assert by != null : "Parsed deadline date should not be null";
                    Deadlines c = new Deadlines(des, by);
                    return new AddCommand(c);

                } catch (Exception e) {
                    deadline();
                }
                break;

            case EVENT:
                try {
                    assert response.contains("/from") && response.contains("/to") : "Event command must contain " +
                            "'/from' and '/to'";
                    String des = response.split("event ")[1].split("/from")[0].strip();
                    String from = response.split("/from")[1].split("/to")[0].strip();
                    LocalDateTime from_time = LocalDateTime.parse(from.replace(" ", "T") + ":00");
                    String to = response.split("/to")[1].strip();
                    LocalDateTime to_time = LocalDateTime.parse(to.replace(" ", "T") + ":00");
                    assert from_time.isBefore(to_time) : "Event start time must be before end time";
                    Events c = new Events(des, from_time, to_time);
                    return new AddCommand(c);

                } catch (Exception e) {
                    event();
                }
                break;

            default:
                System.out.println("Invalid Input, try again!");
                return null;
        }
        return null;

    }

    public static void update() {
        System.out.println("OOPS! Invalid input. Please provide a valid update command in the format:");
        System.out.println("update [section] of [index] to [new_value]");
        System.out.println("Valid sections: des, by, to, from");
        System.out.println("Example: update des of 2 to Buy groceries");
    }

    public static void todo() {
        System.out.println("OOPS try giving this input, \ntodo [task description]");
        System.out.println("Example: todo Buy groceries");
    }

    public static void deadline() {
        System.out.println("OOPS try giving this input, \ndeadline [task description] /by [yyyy-MM-dd HH:mm]");
        System.out.println("Example: deadline Submit report /by 2025-03-15 23:59");
    }

    public static void event() {
        System.out.println("OOPS try giving this input, \nevent [event description] /from [yyyy-MM-dd HH:mm] /to [yyyy-MM-dd HH:mm]");
        System.out.println("Example: event Team meeting /from 2025-03-15 14:00 /to 2025-03-15 15:30");
    }

    /**
     * Parses the string input form the file and returns the corresponding task.
     * If the input is unclear or invalid, null is returned.
     *
     * @param x String input by the user
     * @return Task that the string represents.
     */
    public static Task txtToTask(String x) {

        try {
            if (x.charAt(1) == 'T') {
                ToDos t = new ToDos(x.substring(7));
                if (x.charAt(4) == 'X') {
                    t.markAsDone();
                }
                return t;
            } else if (x.charAt(1) == 'D') {
                String des = x.split("] ")[1].split(" \\(")[0].strip();
                String byStr = x.split("by: ")[1].split("\\)")[0].strip();
                LocalDateTime by = Parser.listToTask(byStr);
                Deadlines d = new Deadlines(des, by);
                if (x.charAt(4) == 'X') {
                    d.markAsDone();
                }
                return d;
            } else if (x.charAt(1) == 'E') {
                String des = x.split("] ")[1].split(" \\(")[0].strip();
                String fromStr = x.split("from: ")[1].split(" to:")[0].strip();
                String toStr = x.split("to: ")[1].split("\\)")[0].strip();
                LocalDateTime from_time = Parser.listToTask(fromStr);
                LocalDateTime to_time = Parser.listToTask(toStr);
                Events e = new Events(des, from_time, to_time);
                if (x.charAt(4) == 'X') {
                    e.markAsDone();
                }
                return e;
            } else {
                throw new IllegalArgumentException("Unknown task type");
            }
        } catch (Exception e) {
            System.out.println("Error parsing task: " + e.getMessage());
            return null;
        }
    }

    /**
     * Parses the string input into LocalDateTime object.
     *
     * @param s String in [MMM dd yyyy hh:mm a] format.
     * @return LocalDateTime object that the string represents.
     */
    public static LocalDateTime listToTask(String s) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        return LocalDateTime.parse(s, inputFormatter);
    }

    /**
     * Parses the LocalDateTime object into String.
     *
     * @param dateTime LocalDateTime object.
     * @return String  that the LocalDateTime represents.
     */
    public static String fromatToString(LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        return dateTime.format(formatter);

    }

}

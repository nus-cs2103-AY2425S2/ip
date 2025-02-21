package ekko.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import ekko.notes.NotesCollection;
import ekko.storage.Storage;
import ekko.task.Deadline;
import ekko.task.Event;
import ekko.task.Todo;
import ekko.task.Todolist;

/**
 * Handle the parsing of user's input.
 */
public class Parser {

    private static final List<DateTimeFormatter> DATE_TIME_FORMATTERS = List.of(
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("dd MM yyyy HH:mm"),
            DateTimeFormatter.ofPattern("yyyy MM dd HH:mm"),
            DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm"),
            DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"),
            DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH"),
            DateTimeFormatter.ofPattern("MM-dd-yyyy HH"),
            DateTimeFormatter.ofPattern("MMM dd yyyy HH"),
            DateTimeFormatter.ofPattern("dd MMM yyyy HH")
    );

    private static final List<DateTimeFormatter> DATE_FORMATTERS = List.of(
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("dd MM yyyy"),
            DateTimeFormatter.ofPattern("yyyy MM dd"),
            DateTimeFormatter.ofPattern("MM-dd-yyyy"),
            DateTimeFormatter.ofPattern("MMM dd yyyy"),
            DateTimeFormatter.ofPattern("dd MMM yyyy")
    );


    /**
     * Extract the command word of an input
     * @param input String user input
     * @return String command word
     */
    public static Commands parseCommand(String input) {
        return Commands.valueOf(input.trim().split(" ")[0].toUpperCase());
    }

    /**
     * Parse the String date and time input to a LocalDateTime object
     * @param input String date and time
     * @return LocalDateTime
     */
    public static LocalDateTime parseDateTime(String input) {
        String trimmedInput = input.trim();
        for (DateTimeFormatter formatter : DATE_TIME_FORMATTERS) {
            try {
                return LocalDateTime.parse(trimmedInput, formatter);
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        for (DateTimeFormatter formatter : DATE_FORMATTERS) {
            try {
                return LocalDate.parse(trimmedInput, formatter).atTime(23, 59, 59);
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        throw new IllegalArgumentException("Invalid date or date-time format: " + trimmedInput);
    }

    /**
     * Parse input with command EVENT
     * @param todolist todolist in ekko
     * @param storage file in ekko
     * @param input String user input
     * @return new Event created
     */
    public static String parseEvent(Todolist todolist, Storage storage, String input) {
        String des = input.split(" ", 2)[1];
        String start = des.split("/from ")[1].split(" /to")[0];
        LocalDateTime startTime = Parser.parseDateTime(start);
        String end = des.split("/to ")[1];
        LocalDateTime endTime = Parser.parseDateTime(end);
        des = des.split("/from")[0];
        String resp = todolist.add(new Event(des, startTime, endTime));
        storage.updateFile(todolist);
        return resp;
    }

    /**
     * Parse input with command TODO to create a Todo object
     * @param todolist todolist in ekko
     * @param storage file in ekko
     * @param input String user input
     * @return new Todo created
     */
    public static String parseTodo(Todolist todolist, Storage storage, String input) {
        String des = input.split(" ", 2)[1];
        String resp = todolist.add(new Todo(des));
        storage.updateFile(todolist);
        return resp;
    }

    /**
     * Parse input with command DEADLINE to create a Deadline Object
     * @param todolist todolist in ekko
     * @param storage file in ekko
     * @param input String user input
     * @return new Deadline created
     */
    public static String parseDeadline(Todolist todolist, Storage storage, String input) {
        String des = input.split(" ", 2)[1].split("/by")[0];
        String stringDate = input.split("/by ")[1];
        LocalDateTime dueDateTime = Parser.parseDateTime(stringDate);
        String resp = todolist.add(new Deadline(des, dueDateTime));
        storage.updateFile(todolist);
        return resp;
    }

    /**
     * Output words of love (just an Easter egg)
     * @return String easter egg
     */
    public static String parseMeow() {
        return "Meow, I love you too.";
    }

    /**
     * Parse input with Find command.
     * @param todolist todolist in ekko
     * @param storage file in ekko
     * @param input String user input
     * @return search keyword
     */
    public static String parseFind(Todolist todolist, Storage storage, String input) {
        String keyword = input.split(" ", 2)[1];
        String resp = todolist.filter(keyword);
        if (resp.isBlank()) {
            return "Nothing found meow, maybe you can add that into your list? ";
        }
        return "Here are the relevant items on your list: \n" + resp;
    }

    /**
     * Parse the MARK command.
     * @param todolist todolist in ekko
     * @param storage file in ekko
     * @param input from user
     * @return Response from Ekko
     */
    public static String parseMark(Todolist todolist, Storage storage, String input) {
        try {
            int index = Integer.parseInt(input.split(" ")[1]);
            String resp = todolist.mark(index);
            storage.updateFile(todolist);
            return resp;
        } catch (NumberFormatException e) {
            return "meow, please put a number after the command.";
        }
    }

    /**
     * Parse the UNMARK command.
     * @param todolist todolist in ekko
     * @param storage file in ekko
     * @param input from user
     * @return Response from Ekko
     */
    public static String parseUnmark(Todolist todolist, Storage storage, String input) {
        try {
            int index = Integer.parseInt(input.split(" ")[1]);
            String resp = todolist.unmark(index);
            storage.updateFile(todolist);
            return resp;
        } catch (NumberFormatException e) {
            return "meow, please put a number after the command.";
        }
    }

    /**
     * Parse the DELETE command.
     * @param todolist todolist in ekko
     * @param storage file in ekko
     * @param input from user
     * @return Response from Ekko
     */
    public static String parseDelete(Todolist todolist, Storage storage, String input) {
        try {
            int index = Integer.parseInt(input.split(" ")[1]);
            String resp = todolist.delete(index);
            storage.updateFile(todolist);
            return resp;
        } catch (NumberFormatException e) {
            return "meow, please put a number after the command.";
        }
    }

    /**
     * Parse the NOTE command.
     * @param notelist note collection in ekko
     * @param storage storage instance
     * @param input user input
     * @return response message
     */
    public static String parseNote(NotesCollection notelist, Storage storage, String input) {
        String title = input.split("/t ", 2)[1].split("/d ", 2)[0].trim();
        String description = input.split("/d ", 2)[1].trim();
        String resp = notelist.addNote(title, description);
        storage.updateNotes(notelist);
        return resp;
    }

    /**
     * Parse the RMNOTE command.
     * @param notelist note collection in ekko
     * @param storage storage instance in ekko
     * @param input user input
     * @return response message by ekko
     */
    public static String parseRMnote(NotesCollection notelist, Storage storage, String input) {
        String title = input.split(" ", 2)[1];
        String resp = notelist.removeNote(title);
        storage.updateNotes(notelist);
        return resp;
    }

    /**
     * Parse the LIST command.
     * @param notelist note collection in ekko
     * @param todolist todolist in ekko
     * @return string of both the task list and the note collection
     */
    public static String parseList(NotesCollection notelist, Todolist todolist) {
        String todo = todolist.toString();
        String note = notelist.toString();
        if (note.isEmpty()) {
            note = "Meow, your note list is empty! Anything you want ekko to remember? ";
        }
        if (todo.isEmpty()) {
            todo = "Meow, your task list is empty! Yayy let's take a break together~";
        }
        return "Your tasks: \n" + todo + "\n\nYour notes: \n" + note;
    }
}

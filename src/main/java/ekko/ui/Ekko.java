package ekko.ui;

import ekko.core.Commands;
import ekko.core.Parser;
import ekko.notes.NotesCollection;
import ekko.storage.Storage;
import ekko.task.Todolist;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Handle user interface functionalities.
 */
public class Ekko {

    /**
     * Brief intro from Ekko and guide to use the chatbot
     */
    private static final String MANUAL = """
    Here's how you can use me :3

    📌 Task Management:
    - TODO <description> ➝ Add a generic task to your to-do list.
    - DEADLINE <description> /by DD/MM/YYYY HH ➝ Add a task with a due date and time.
    - EVENT <description> /from DD/MM/YYYY HH /to DD/MM/YYYY HH ➝ Add an activity with a start and end time.

    ✅ Task Updates:
    - MARK <index> ➝ Mark the task at the given index as done.
    - UNMARK <index> ➝ Mark the task at the given index as not done.

    🔍 Searching & Listing:
    - FIND <keyword> ➝ Search for tasks with relevant keywords.
    - LIST ➝ Display all tasks and notes in your to-do list.

    🗑️ Deletion:
    - DELETE <index> ➝ Remove a task from the to-do list.

    📝 Notes:
    - NOTE /t <title> /d <description> ➝ Add a note.
    - RMNOTE <title> ➝ Remove a note.
        """;

    private Storage storage;
    private Todolist todolist;
    private NotesCollection notelist;

    /**
     * Initialise the todolist and storage file for an Ekko instance
     */
    public Ekko() {
        this.storage = new Storage();
        try {
            this.todolist = storage.loadTask();
            this.notelist = storage.loadNotes();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get the String output for any user input, and print greeting upon empty input
     * @param input user input
     * @return response from ekko
     */
    public String getResponse(String input) {
        // Check if the input is empty or null, and return the greeting
        if (input == null || input.trim().isEmpty()) {
            return greet();
        }
        try {
            Commands command = Parser.parseCommand(input);
            switch (command) {
            case TODO:
                return Parser.parseTodo(todolist, storage, input);
            case BYE:
                Ekko.exit();
                break;
            case MARK:
                return Parser.parseMark(todolist, storage, input);
            case UNMARK:
                return Parser.parseUnmark(todolist, storage, input);
            case LIST:
                return Parser.parseList(notelist, todolist);
            case DEADLINE:
                return Parser.parseDeadline(todolist, storage, input);
            case EVENT:
                return Parser.parseEvent(todolist, storage, input);
            case DELETE:
                return Parser.parseDelete(todolist, storage, input);
            case MEOW:
                return Parser.parseMeow();
            case FIND:
                return Parser.parseFind(todolist, storage, input);
            case NOTE:
                return Parser.parseNote(notelist, storage, input);
            case RMNOTE:
                return Parser.parseRMnote(notelist, storage, input);
            case HOW:
                return MANUAL;
            default:
                return "Meow?";
            }
        } catch (IllegalArgumentException e) {
            return "Meow, sorry I am just a little Ekko. ";

        } catch (ArrayIndexOutOfBoundsException e) {
            return "Meow, please complete your command!";
        }
        return "Meow, sorry I am just a little Ekko. ";
    }

    /**
     * Greet the user when starting the program.
     */
    public static String greet() {
        return ("Hello from Ekko <3\n" + "\n" + MANUAL + "\nWhat can I do for you?\n(Input \"bye\" to exit)");
    }

    /**
     * Exit the program.
     */
    public static void exit() {
        System.exit(0);
    }

    /**
     * Repeat whatever that was said by the user.
     * @param text String to repeat
     */
    public static String echo(String text) {
        return text;
    }

}


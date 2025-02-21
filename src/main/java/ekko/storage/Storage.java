package ekko.storage;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import ekko.core.Commands;
import ekko.core.Parser;
import ekko.notes.Note;
import ekko.notes.NotesCollection;
import ekko.task.Deadline;
import ekko.task.Event;
import ekko.task.Todo;
import ekko.task.Todolist;

/**
 * Handles the creation of directory and file, writing and updating the file.
 */
public class Storage {

    private String dirPath;
    private String todoPath;
    private String notePath;

    /**
     * Instantiates a Storage object with one directory and two files.
     */
    public Storage() {
        this.dirPath = makeDir();
        this.todoPath = createFile("ekko.txt");
        this.notePath = createFile("note.txt");
    }

    /**
     * Creates a directory named "data" in the project folder if it does not exist.
     * @return the path to the created directory.
     */
    private String makeDir() {
        String projPath = System.getProperty("user.dir");
        String dirPath = projPath + File.separator + "data";
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        return dirPath;
    }

    /**
     * Creates a file in the directory if it does not exist.
     * @param fileName name of the file.
     * @return the path to the created file.
     */
    private String createFile(String fileName) {
        String filePath = dirPath + File.separator + fileName;
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
        return filePath;
    }

    /**
     * Updates both todolist and notelist in this storage to sync it with the current todolist.
     * @param todolist current todolist to sync.
     */
    public void updateFile(Todolist todolist) {
        if (todoPath == null) {
            System.out.println("Error: File path is not initialized.");
            return;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(todoPath))) {
            bw.write(todolist.toStore());
        } catch (IOException e) {
            System.out.println("Uh oh, something went wrong when trying to write into a file.");
        }
    }

    /**
     * Update the note tracking file to the latest change.
     * @param notelist current notelist in the storage instance.
     */
    public void updateNotes(NotesCollection notelist) {
        if (notePath == null) {
            System.out.println("Error: File path is not initialized.");
            return;
        }
        try (BufferedWriter bw2 = new BufferedWriter(new FileWriter(notePath))) {
            bw2.write(notelist.toString());
        } catch (IOException e) {
            System.out.println("Uh oh, something went wrong when trying to write into a file.");
        }
    }

    /**
     * Convert a line in file to a Todo
     */
    public static Todo convertToTask(String input) {
        String[] parts = input.split("//");
        Commands taskType = Commands.valueOf(parts[0]);
        boolean isDone = Boolean.parseBoolean(parts[1]);
        switch (taskType) {
        case TODO:
            return new Todo(parts[2], isDone);
        case DEADLINE:
            return new Deadline(parts[2], Parser.parseDateTime(parts[3]));
        case EVENT:
            return new Event(parts[2], Parser.parseDateTime(parts[3]),
                    Parser.parseDateTime(parts[4]));
        default:
            return null;
        }
    }

    /**
     * Convert String to Note
     */
    public Note convertToNote(String input) {
        String[] parts = input.split(": ");
        return new Note(parts[0].trim(), parts[1].trim());
    }

    /**
     * Load Tasklist from hard disk
     */
    public Todolist loadTask() throws FileNotFoundException, IOException {
        Todolist todolist = new Todolist();
        File file = new File(todoPath);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                todolist.add(convertToTask(line));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return todolist;
    }

    /**
     * Load notes from hard disk
     */
    public NotesCollection loadNotes() throws FileNotFoundException, IOException {
        NotesCollection notes = new NotesCollection();
        File file = new File(notePath);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                notes.addNote(convertToNote(line));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return notes;
    }
}

package kyrie;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the storage of the task list.
 */
public class Storage {
    private final String DIR_PATH;
    private final String FILE_PATH;

    /**
     * Constructor for Storage.
     * 
     * @param dirPath The directory path to save the task list to.
     */
    public Storage(String dirPath) {
        DIR_PATH = dirPath;
        FILE_PATH = dirPath + "/tasks.txt";
    }

    /**
     * Creates the directory and file to save the task list to.
     */
    public void createDirAndFile() {
        File dir = new File(this.DIR_PATH);
        if (!dir.exists()) {
            dir.mkdir();
        }
        File file = new File(this.FILE_PATH);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("There seems to be something wrong: " + e);
        }
    }

    /**
     * Saves the task list to the file.
     * 
     * @param tasks The task list to save.
     */
    public void saveData(TaskList tasks) {
        try {
            File file = new File(this.FILE_PATH);
            FileWriter writer = new FileWriter(file);
            writer.write(tasks.toFileString());
            writer.close();
        } catch (IOException e) {
            System.out.println("There seems to be something wrong: " + e);
        }
    }

    /**
     * Loads the task list from the file.
     * 
     * @return The task list loaded from the file.
     * @throws IOException If there is an error loading the file.
     */
    public TaskList loadData() throws IOException {
        TaskList tasks = new TaskList();
        File file = new File(this.FILE_PATH);
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            try {
                Task task = parseTask(line);
                if (isTaskDone(line)) {
                    task.markAsDone();
                }
                tasks.addTask(task);
            } catch (KyrieException e) {
                System.out.println("There seems to be something wrong: " + e);
            }
        }
        sc.close();
        return tasks;
    }

    /**
     * Converts a string of tags to a TagList.
     * 
     * @param tagString The string of tags.
     * @return The corresponding TagList.
     */
    public TagList toTagList(String tagString) {
        TagList tags = new TagList();
        String[] tagArray = tagString.split(" ");
        for (String tag : tagArray) {
            tags.addTag(new Tag(tag));
        }
        return tags;
    }

    /**
     * Parses a line from the file and returns the corresponding todo task.
     * 
     * @param line The line to parse.
     * @return The corresponding todo task.
     * @throws KyrieException If the task format is invalid.
     */
    public Todo parseTodoTask(String line) throws KyrieException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3 || parts.length > 4) {
            throw new KyrieException("Invalid task format");
        } else if (parts.length == 3) {
            return new Todo(parts[2]);
        }
        return new Todo(parts[2], toTagList(parts[3]));
    }

    /**
     * Parses a line from the file and returns the corresponding deadline task.
     * 
     * @param line The line to parse.
     * @return The corresponding deadline task.
     * @throws KyrieException If the task format is invalid.
     */
    public Deadline parseDeadlineTask(String line) throws KyrieException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 4 || parts.length > 5) {
            throw new KyrieException("Invalid task format");
        } else if (parts.length == 4) {
            return new Deadline(parts[2], new DateTime(parts[3]));
        }
        return new Deadline(parts[2], new DateTime(parts[4]), toTagList(parts[3]));
    }

    /**
     * Parses a line from the file and returns the corresponding event task.
     * 
     * @param line The line to parse.
     * @return The corresponding event task.
     * @throws KyrieException If the task format is invalid.
     */
    public Event parseEventTask(String line) throws KyrieException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 5 || parts.length > 6) {
            throw new KyrieException("Invalid task format");
        } else if (parts.length == 5) {
            return new Event(parts[2], new DateTime(parts[3]), new DateTime(parts[4]));
        }
        return new Event(parts[2], new DateTime(parts[4]), new DateTime(parts[5]), toTagList(parts[3]));
    }

    /**
     * Parses a line from the file and returns an invalid task.
     * @param line
     * @return The corresponding invalid task.
     * @throws KyrieException
     */
    public Task parseInvalidTask(String line) throws KyrieException {
        throw new KyrieException("Invalid task format");
    }

    /**
     * Parses a line from the file and returns the corresponding task.
     * 
     * @param line The line to parse.
     * @return The corresponding task.
     * @throws KyrieException If the task format is invalid.
     */
    private Task parseTask(String line) throws KyrieException {
        String[] parts = line.split(" \\| ");
        switch (parts[0]) {
            case "TODO":
                return parseTodoTask(line);
            case "DEADLINE":
                return parseDeadlineTask(line);
            case "EVENT":
                return parseEventTask(line);
            default:
                return parseInvalidTask(line);
        }
    }

    /**
     * Checks if the task is marked as done.
     * 
     * @param line The line to check.
     * @return True if the task is marked as done, false otherwise.
     */
    private boolean isTaskDone(String line) {
        String[] parts = line.split(" \\| ");
        return parts[1].equals("1");
    }
}
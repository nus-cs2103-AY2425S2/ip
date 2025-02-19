package thoughtbot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import exceptions.LoadErrorException;
import tasks.Task;
import tasks.TaskDeadline;
import tasks.TaskEvent;
import tasks.TaskToDo;

/**
 * This is an uninitializable class meant to handle the saving and loading of TaskLists from the user's
 * hard disk. There are two main function, save and load, which perform their respective actions
 */
public class SaveLoad {
    private static final String absoluteFilePath = new File("").getAbsolutePath();
    private static final String relativeFilePath = "/data/thoughtbot.txt";
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private SaveLoad() {
        // to prevent instantiation
    }

    /**
     * Saves the currentTaskList to the relative path ./data/thoughtbot.txt, in the format of
     * taskType | doneOrNot | taskName | otherParameters
     * @param currentTaskList Task:ist to be saved to the hard disk
     */
    public static void save(TaskList currentTaskList) {
        try {
            File saveDir = new File(absoluteFilePath + "/data");
            File saveFile = new File(absoluteFilePath + relativeFilePath);

            if (!saveDir.exists()) {
                saveDir.mkdir();
            }
            if (!saveFile.exists()) {
                saveFile.createNewFile();
            }

            FileWriter saveWrite = new FileWriter(absoluteFilePath + relativeFilePath);

            // for each task, parse it to the correct format and save it
            for (Task t: currentTaskList.getTasks()) {
                String line = parseToSaveFormat(t);
                saveWrite.write(line + System.lineSeparator());
            }

            saveWrite.close();
        } catch (IOException e) {
            System.out.printf("File was unable to be created to save: " + e);
        }
    }

    /**
     * Parses a task into the save file format and return it
     * @param t Task to be parsed
     * @return String format of the task for the save file
     */
    private static String parseToSaveFormat(Task t) {
        String retString;
        String doneString = t.getDone() ? "1" : "0";
        String nameString = t.getName();

        if (t.getClass() == TaskToDo.class) {
            retString = "T | " + doneString + " | " + nameString;
        } else if (t.getClass() == TaskDeadline.class) {
            TaskDeadline deadlineT = (TaskDeadline) t;
            String deadlineString = deadlineT.getDeadline();
            retString = "D | " + doneString + " | " + nameString + " | " + deadlineString;
        } else {
            TaskEvent eventT = (TaskEvent) t;
            String fromString = eventT.getFromTime();
            String toString = eventT.getToTime();
            retString = "E | " + doneString + " | " + nameString + " | " + fromString + " | " + toString;
        }

        return retString;
    }

    /**
     * Loads the TaskList that was previously saved from memory, from the relative path ./data/thoughtbot.txt
     * @return TaskList that was loaded from memory
     */
    public static TaskList load() {
        try {
            File loadFile = new File(absoluteFilePath + relativeFilePath);
            Scanner loadScanner = new Scanner(loadFile);
            TaskList retList = new TaskList();

            while (loadScanner.hasNext()) {
                Task t = parseToTask(loadScanner.nextLine());
                retList.addTask(t);
            }

            return retList;
        } catch (FileNotFoundException e) {
            return new TaskList();
        } catch (LoadErrorException e) {
            String errorMessage = "The current save file is corrupted due to the following reason:\n"
                    + e.getMessage()
                    + "\nCreating a new task list...";
            System.out.println(errorMessage);
            return new TaskList();
        }
    }

    /**
     * Parses the string form of a line is the save file to the actual Task object
     * @param s One line of the save file
     * @return Task parsed from the given line
     * @throws LoadErrorException If the save file is not in the correct format
     */
    private static Task parseToTask(String s) throws LoadErrorException {
        String[] splitSections = s.split(" \\| ");

        if (splitSections.length < 3) {
            throw new LoadErrorException("There are tasks without a type, mark or name.");
        }

        String type = splitSections[0];
        String done = splitSections[1];
        String name = splitSections[2];

        assert !type.isBlank() : "No type in load file but LoadErrorException wasn't thrown";
        assert !done.isBlank() : "No done in load file but LoadErrorException wasn't thrown";
        assert !name.isBlank() : "No name in load file but LoadErrorException wasn't thrown";

        switch (type) {
        case "T":
            TaskToDo tToDo = new TaskToDo(name);
            markOrNotDone(tToDo, done);
            return tToDo;
        case "D":
            try {
                String deadline = splitSections[3];
                LocalDateTime deadlineDateTime = LocalDateTime.parse(deadline, formatter);
                TaskDeadline tDeadline = new TaskDeadline(name, deadlineDateTime);
                markOrNotDone(tDeadline, done);
                return tDeadline;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new LoadErrorException("A deadline task does not have a valid number of parameters.");
            } catch (DateTimeParseException e) {
                throw new LoadErrorException("A deadline task has a wrongly formatted datetime.");
            }
        case "E":
            try {
                String fromTime = splitSections[3];
                String toTime = splitSections[4];
                LocalDateTime fromDateTime = LocalDateTime.parse(fromTime, formatter);
                LocalDateTime toDateTime = LocalDateTime.parse(toTime, formatter);
                TaskEvent tEvent = new TaskEvent(name, fromDateTime, toDateTime);
                markOrNotDone(tEvent, done);
                return tEvent;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new LoadErrorException("An event task does not have a valid number of parameters.");
            } catch (DateTimeParseException e) {
                throw new LoadErrorException("An event task has a wrongly formatted datetime.");
            }
        default:
            throw new LoadErrorException("The type of entry in the save file is corrupted.");
        }
    }

    /**
     * Marks a task as done if the done String is "1"
     * @param t Task to be marked or not
     * @param done String containing either "0" or "1"
     */
    private static void markOrNotDone(Task t, String done) {
        if (done.equals("1")) {
            t.markDone();
        }
    }
}

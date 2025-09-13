package universe;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import universe.chores.Chore;
import universe.chores.Deadline;
import universe.chores.Event;
import universe.chores.ToDo;
import universe.exceptions.IncorrectFormatException;
import universe.response.FileParser;

/**
 * Responsible for reading and writing to an existing checklist file.
 * Creates the file if file does not exist.
 *
 * @author yuqing-tham
 */
public class Storage {
    private final String filepath;

    /**
     * Constructs a new Storage with a specified filepath.
     *
     * @param filepath A String pointing to the file.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Reads the content of the file and create a new Checklist.
     *
     * @throws IOException If there are any issues with reading the file.
     * @throws IncorrectFormatException If there are any issues with data in the file.
     */
    public void readFile() throws IOException, IncorrectFormatException {
        File f = new File(filepath);

        // if the directory does not exist, create the "data" directory
        f.getParentFile().mkdirs();

        // if the file does not exist, create the file
        if (!f.exists()) {
            f.createNewFile();
        }

        Scanner scanner = new Scanner(f);
        this.createChoresList(scanner);
        scanner.close();
    }

    /**
     * Creates a Checklist to store all the information read from the file.
     */
    public void createChoresList(Scanner scanner) throws IncorrectFormatException {
        while (scanner.hasNext()) {
            String choreInFile = scanner.nextLine();
            FileParser fileParser = new FileParser(choreInFile);
            String choreType = fileParser.getChoreType();
            String isDone = fileParser.getStatus();
            String description = fileParser.getChoreDescription();

            Chore chore;
            switch (choreType) {
            case "T":
                chore = new ToDo(description);
                break;
            case "D":
                LocalDateTime deadlineDate = fileParser.getDeadlineDateTime();
                chore = new Deadline(description, deadlineDate);
                break;
            case "E":
                LocalDateTime startDateTime = fileParser.getEventStartDateTime();
                LocalDateTime endDateTime = fileParser.getEventEndDateTime();
                chore = new Event(description, startDateTime, endDateTime);
                break;
            default:
                throw new IncorrectFormatException();
            }
            if (isDone.equals("1")) {
                chore.markAsDone(); // mark the chore as done if isDone is true (indicated as 1)
            }
            Checklist.getChores().add(chore);
        }
    }

    /**
     * Saves the modified Checklist to the file.
     *
     * @throws IOException If there are any issues writing to the file.
     */
    public void saveChores() throws IOException {
        FileWriter fw = new FileWriter(filepath);

        // format the checklist
        StringBuilder textToWrite = new StringBuilder();
        ArrayList<Chore> list = Checklist.getChores();
        for (Chore c : list) {
            textToWrite.append(c.toFileString());
            textToWrite.append("\n");
        }

        // write to the file
        fw.write(textToWrite.toString());
        fw.close();
    }
}

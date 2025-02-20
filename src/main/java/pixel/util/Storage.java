package pixel.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

import pixel.task.Deadline;
import pixel.task.Event;
import pixel.task.TaskList;
import pixel.task.ToDo;

/**
 * Utility class which handles loading and storing of TaskList contents to a file in hard disk.
 */
public class Storage {
    private final String filePath;

    public Storage() {
        this.filePath = "./data/Pixel.txt";
        try {
            File directory = new File("./data");
            directory.mkdir();
            File file = new File("./data/Pixel.txt");
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Reads contents from the file at designated filePath, parses them into task details,
     * which are used to construct the respective Tasks, and then adds them to the TaskList
     *
     * @param taskList TaskList which will store the loaded Tasks
     * @throws PixelException If file contents at designated filePath does not conform to recognized format
     */
    public void load(TaskList taskList) throws PixelException {
        String keyword;
        String desc;
        String isDone;
        try {
            File file = new File(this.filePath);
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                keyword = sc.nextLine();
                switch (keyword) {
                case "todo":
                    desc = sc.nextLine();
                    isDone = sc.nextLine();
                    taskList.addTask(new ToDo(desc, Boolean.parseBoolean(isDone)));
                    continue;
                case "deadline":
                    desc = sc.nextLine();
                    isDone = sc.nextLine();
                    String dueBy = sc.nextLine();
                    taskList.addTask(new Deadline(desc, Boolean.parseBoolean(isDone), LocalDateTime.parse(dueBy)));
                    continue;
                case "event":
                    desc = sc.nextLine();
                    isDone = sc.nextLine();
                    String from = sc.nextLine();
                    String to = sc.nextLine();
                    taskList.addTask(new Event(desc, Boolean.parseBoolean(isDone),
                            LocalDateTime.parse(from), LocalDateTime.parse(to)));
                    continue;
                case "EOF":
                    return;
                default:
                    throw new PixelException("Invalid file contents");
                }
            }
        } catch (FileNotFoundException e) {
            throw new PixelException("Invalid file path!");
        }
    }

    /**
     * Writes the contents of the TaskList into the file at the designated filePath
     *
     * @param taskList TaskList currently storing the Tasks
     */
    public void save(TaskList taskList) {
        try {
            FileWriter writer = new FileWriter(this.filePath);
            writer.write(taskList.toFileFormat());
            writer.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}

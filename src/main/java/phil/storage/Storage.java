package phil.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import phil.model.Deadline;
import phil.model.Event;
import phil.model.Note;
import phil.model.NoteList;
import phil.model.Task;
import phil.model.TaskList;
import phil.model.Todo;

/**
 * Represents the storage element which allows the loading and storing of data to disk.
 */
public class Storage {

    private Path dirPath;
    private Path taskPath;
    private Path notePath;

    /**
     * Sets up the Storage object.
     *
     * @param dirPath directory path for storing data
     * @param taskPath file path for storing data
     */
    public Storage(String dirPath, String taskPath, String notePath) {
        this.dirPath = Paths.get(dirPath);
        this.taskPath = Paths.get(dirPath, taskPath);
        this.notePath = Paths.get(dirPath, notePath);
    }

    /**
     * Loads file specified at dirPath and taskPath.
     *
     * @return task list obtained from loading file at taskPath.
     * @throws IOException if directory or file cannot be opened or other IO issues.
     */
    public TaskList loadTasks() throws IOException {
        TaskList taskList = new TaskList();

        // Create folder 'data' and/or file 'phil.txt' if it does not exist
        if (!Files.exists(this.dirPath)) {
            Files.createDirectory(this.dirPath);
        }
        if (!Files.exists(this.taskPath)) {
            Files.createFile(this.taskPath);
        }

        // Assert that both the directory path and the data path must exist.
        assert Files.exists(this.dirPath) && Files.exists(this.taskPath);

        // Load data from 'phil.txt'
        List<String> lines = Files.readAllLines(this.taskPath);

        for (String line : lines) {
            String[] lineArgs = line.split(" - ");
            if (lineArgs[0].equals("Todo")) {
                Task taskToAdd = new Todo(lineArgs[2]);
                if (lineArgs[1].equals("X")) {
                    taskToAdd.markDone();
                }
                taskList.addTask(taskToAdd);
            } else if (lineArgs[0].equals("Deadline")) {
                Task taskToAdd = new Deadline(lineArgs[2], lineArgs[3]);
                if (lineArgs[1].equals("X")) {
                    taskToAdd.markDone();
                }
                taskList.addTask(taskToAdd);
            } else if (lineArgs[0].equals("Event")) {
                Task taskToAdd = new Event(lineArgs[2], lineArgs[3], lineArgs[4]);
                if (lineArgs[1].equals("X")) {
                    taskToAdd.markDone();
                }
                taskList.addTask(taskToAdd);
            }
        }
        return taskList;
    }

    /**
     * Loads file specified at dirPath and taskPath.
     *
     * @return task list obtained from loading file at taskPath.
     * @throws IOException if directory or file cannot be opened or other IO issues.
     */
    public NoteList loadNotes() throws IOException {
        NoteList noteList = new NoteList();

        // Create folder 'data' and/or file 'phil.txt' if it does not exist
        if (!Files.exists(this.dirPath)) {
            Files.createDirectory(this.dirPath);
        }
        if (!Files.exists(this.notePath)) {
            Files.createFile(this.notePath);
        }

        // Assert that both the directory path and the data path must exist.
        assert Files.exists(this.dirPath) && Files.exists(this.notePath);

        // Load data from note path
        List<String> lines = Files.readAllLines(this.notePath);

        for (String line : lines) {
            noteList.addNote(new Note(line));
        }
        return noteList;
    }

    /**
     * Saves task list into a file at a specified taskPath.
     *
     * @param taskList list of tasks to save.
     * @throws IOException thrown if there are IO errors when saving.
     */
    public void save(TaskList taskList, NoteList noteList) throws IOException {
        // Assert that both the directory path and the data path must exist.
        assert Files.exists(this.dirPath) && Files.exists(this.taskPath) && Files.exists(this.notePath);

        StringBuilder tasksString = new StringBuilder();
        for (Task task : taskList.getListOfTasks()) {
            tasksString.append(task.toLoadString()).append("\n");
        }
        Files.writeString(this.taskPath, tasksString.toString());

        Files.writeString(this.notePath, noteList.toLoadString());
    }
}

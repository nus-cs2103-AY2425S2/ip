package tasks;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import exceptions.MarkException;
import exceptions.TaskException;
import storage.SaveFileIO;
import tasks.parser.TaskDateTimeParser;

/**
 * This class manages the list of tasks.
 * It provides functionalities to add, delete, mark/unmark tasks.
 * It will also load/save them to a file.
 *
 * @author Yashvan
 */
public class TaskManager {
    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Empty constructor to initialise instance of TaskManager Class.
     */
    public TaskManager() {}

    /**
     * Adds task to task list.
     *
     * @param task The task to add to the list.
     */
    public String addTask(Task task) throws TaskException, IOException {
        assert task != null : "Task should not be null";

        if (task.isEmpty()) {
            throw new TaskException("Hey! The description of your tasks cannot be empty!");
        } else {
            taskList.add(task);
            assert taskList.contains(task) : "Tasklist should have task after adding it";

            return "______________________________________________________________________________________\n"
                    + "I've added this to tasks:\n"
                    + task
                    + "\nCool. You have " + taskList.size() + " tasks now. Anything else?\n"
                    + "______________________________________________________________________________________\n";
        }
    }

    /**
     * Deletes a task from the task list based on its index.
     *
     * @param index The index of the task to be deleted.
     * @throws TaskException If the task list is empty or the index is invalid.
     */
    private String deleteTask(int index) throws TaskException {
        assert taskList != null : "Task list should not be null";

        if (taskList.isEmpty()) {
            throw new TaskException("Can't delete work if you don't have work to do!");
        }

        if (index < 0 || index >= taskList.size()) {
            throw new TaskException("I don't know if you've noticed BUT we don't have that many tasks!");
        }

        Task removedTask = taskList.remove(index);

        return "______________________________________________________________________________________\n"
                + "I've removed this from tasks:\n"
                + removedTask
                + "\nCool. You have " + taskList.size() + " tasks now. Anything else?\n"
                + "______________________________________________________________________________________\n";
    }

    /**
     * Parses input and deletes a task.
     *
     * @param input       The user input in the format of "delete task_number".
     * @param taskManager The {@link TaskManager} instance managing the tasks.
     * @throws NumberFormatException If the task number is missing, empty, or invalid.
     * @throws TaskException         If the task list is empty or the task number is invalid.
     */
    public static String deleteTask(String input, TaskManager taskManager)
            throws NumberFormatException, TaskException {
        assert input != null && !input.isBlank() : "Input should not be null or empty";
        assert taskManager != null : "TaskManager should not be null";

        if (input.length() <= 7) {
            throw new NumberFormatException("Boh... Use: delete <task_number>");
        }

        String numberString = input.substring(7).trim();
        if (numberString.isEmpty()) {
            throw new NumberFormatException("Boh... Use: delete <task_number>");
        }

        int indexToDelete;
        try {
            indexToDelete = Integer.parseInt(numberString) - 1;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Does that look like a number to you bruh? Enter a valid number!");
        }

        return taskManager.deleteTask(indexToDelete);
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param index The index at which the task should be marked as done.
     */
    private String markTask(int index) throws MarkException, TaskException {
        assert taskList != null : "Task list should not be null";

        if (taskList.isEmpty()) {
            throw new MarkException("You have no tasks to mark. Look's like someone's lazing around!");
        }

        if (index < 0 || index >= taskList.size()) {
            throw new MarkException("I don't know if you've noticed BUT we don't have that many tasks!");
        }

        taskList.get(index).markTask();

        return "Nice! I've marked task " + (index + 1) + " as done!\n" + displayList();
    }

    /**
     * Marks the specified task as done based on the user input.
     * Parses the input string to extract the task number.
     *
     * @param input       The user input in the format of "mark task_number".
     * @param taskManager The {@link TaskManager} instance managing the tasks.
     * @throws NumberFormatException If the task number is missing, empty or invalid.
     * @throws MarkException         If the specified task does not exist.
     * @throws TaskException         For other task-related errors.
     */
    public static String markTask(String input, TaskManager taskManager)
            throws NumberFormatException, MarkException, TaskException, IOException {
        assert input != null && !input.isBlank() : "Input should not be null or empty";
        assert taskManager != null : "Task Manager should not be null";

        if (input.length() <= 5) {
            throw new NumberFormatException("Boh... Use: mark <task_number>");
        }

        String numberString = input.substring(5).trim();
        if (numberString.isEmpty()) {
            throw new NumberFormatException("Boh... Use: mark <task_number>");
        }

        int indexToMark;
        try {
            indexToMark = Integer.parseInt(input.substring(5)) - 1;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Does that look like a number to you bruh? Enter a valid number!");
        }

        return taskManager.markTask(indexToMark);
    }

    /**
     * Unmarks the task at the given index.
     *
     * @param index The index at which the task should be marked as undone.
     */
    private String unmarkTask(int index) throws MarkException, TaskException {
        assert taskList != null : "Task list should not be null";

        if (taskList.isEmpty()) {
            throw new MarkException("You have no tasks to unmark. Look's like someone's lazing around!");
        }

        if (index < 0 || index >= taskList.size()) {
            throw new MarkException("I don't know if you've noticed BUT we don't have that many tasks!");
        }

        taskList.get(index).unmarkTask();

        return "An uno reverse? Task " + (index + 1) + " has been unmarked!\n" + displayList();
    }

    /**
     * Marks the specified task as undone based on the user input.
     * Parses the input string to extract the task number.
     *
     * @param input       The user input in the format of "unmark task_number".
     * @param taskManager The {@link TaskManager} instance managing the tasks.
     * @throws NumberFormatException If the task number is missing, empty or invalid.
     * @throws MarkException         If the specified task does not exist.
     * @throws TaskException         For other task-related errors.
     */
    public static String unmarkTask(String input, TaskManager taskManager)
            throws NumberFormatException, MarkException, TaskException {
        assert input != null && !input.isBlank() : "Input should not be null or empty";
        assert taskManager != null : "Task Manager should not be null";

        if (input.length() <= 7) {
            throw new NumberFormatException("Boh... Use: unmark <task_number>");
        }
        String numberString = input.substring(5).trim();
        if (numberString.isEmpty()) {
            throw new NumberFormatException("Boh... Use: unmark <task_number>");
        }
        int indexToUnmark;
        try {
            indexToUnmark = Integer.parseInt(input.substring(7)) - 1;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Does that look like a number to you bruh? Enter a valid number!");
        }
        return taskManager.unmarkTask(indexToUnmark);
    }

    /**
     * Displays all current tasks in the list.
     */
    public String displayList() throws TaskException {
        assert taskList != null : "Task list should not be null";

        if (this.taskList.isEmpty()) {
            throw new TaskException("Yo! You have nothing in your task list (for now)");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("______________________________________________________________________________________\n");
        sb.append("Here are your tasks:\n");
        for (int i = 0; i < taskList.size(); i++) {
            sb.append((i + 1)).append(": ").append(taskList.get(i)).append("\n");
        }
        sb.append("______________________________________________________________________________________\n");

        return sb.toString();
    }

    /**
     * Loads all tasks from the tyrese.txt file into current user session.
     *
     * @throws IOException If the file cannot be found or does not exist.
     */
    public void loadTasks() throws IOException {
        File directory = new File(System.getProperty("user.dir") + File.separator + "savedata");
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                throw new IOException("Failed to create directory: " + directory.getAbsolutePath());
            }
        }

        File file = new File(directory, "tyrese.txt");

        if (!file.exists() && !file.createNewFile()) {
            throw new IOException("You have no saved tasks! Imma initialise a new list!");
        }

        Scanner s = new Scanner(file);
        while (s.hasNextLine()) {
            String line = s.nextLine().trim();

            if (line.isEmpty()) {
                continue;
            }

            Task task = parseTask(line);
            if (task != null) {
                // This gets only get unmarked tasks by only adding not 'null' tasks
                taskList.add(task);
            }
        }
        s.close();
    }

    /**
     * Returns one of the tasks in its original input form to be loaded into the tyrese.txt.txt file.
     *
     * @param line The line that is used to convert to its original input.
     * @return One of the 3 tasks (Todo, Deadline, Event).
     */
    private Task parseTask(String line) {
        try {
            String taskType = line.substring(1, 2);
            boolean isDone = line.charAt(4) == 'X';

            if (isDone) {
                // This is so that it is not loaded into the tasklist in loadTasks()
                return null;
            }

            // This is to allow parsing of input to allow creation of subtypes of Task objects
            String description = line.substring(line.indexOf("[ ]") + 4, line.indexOf("(Priority:")).trim();
            switch (taskType) {
            case "T": // Todo
                String todoPriority = line.substring(line.indexOf("Priority:") + 10, line.indexOf(")")).trim();
                String todoInput = "todo " + description + " /priority " + todoPriority.toUpperCase();
                return Todo.create(todoInput);
            case "D": // Deadline
                String deadlineInput = TaskDateTimeParser.deadlineParser(line);
                return Deadline.create(deadlineInput);
            case "E": // Event
                String eventInput = TaskDateTimeParser.eventParser(line);
                return Event.create(eventInput);
            default:
                return null;
            }

        } catch (TaskException e) {
            System.out.println(
                    "\t______________________________________________________________________________________\n"
                    + "\t " + e.getMessage()
                    + "\n\t______________________________________________________________________________________\n"
            );
        }

        return null;
    }

    /**
     * Saves only the unmarked tasks to the tyrese.txt.txt file.
     *
     * @throws IOException If tasks cannot be found or saved.
     */
    public void saveUnmarkedTasks() throws IOException {
        ArrayList<Task> unmarkedTasks = new ArrayList<>();

        for (Task task : taskList) {
            if (!task.isDone()) {
                unmarkedTasks.add(task);
            }
        }

        SaveFileIO.writeToSaveFile(unmarkedTasks);
    }

    /**
     * Finds any matching tasks.
     *
     * @param input The task to find in the list.
     */
    public String findTasks(String input) throws TaskException {
        if (input.length() <= 5) {
            throw new TaskException("Watchu trying to find? The lost ark? Enter a description!");
        }

        String description = input.substring(5).trim();
        if (description.isEmpty()) {
            throw new TaskException("Watchu trying to find? The lost ark? Enter a description!");
        }

        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getDescription().contains(description)) {
                foundTasks.add(task);
            }
        }

        if (foundTasks.isEmpty()) {
            throw new TaskException("Weird... It's almost as if that task doesn't exist...");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("______________________________________________________________________________________\n");
        sb.append("Here are the matching tasks:\n");
        for (int i = 0; i < foundTasks.size(); i++) {
            sb.append((i + 1)).append(": ").append(foundTasks.get(i)).append("\n");
        }
        sb.append("______________________________________________________________________________________\n");

        return sb.toString();
    }
}

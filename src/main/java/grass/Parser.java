package grass;
import java.lang.Integer;

/**
* handles logic and parsing of commands
* 
* @author gn07
* 
*/
public class Parser {

    private TaskList tasks;
    
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * <p>parse list command</p>
     * @return tasks in task list as a formatted string
     * @since 1.0
     */
    public String parseList() throws GrassException {
        if (tasks.isEmpty()) {
            throw new GrassException("Task list is empty.");
        }
    
        String list = "";
        for (int i = 0; i < tasks.getSize(); i++) {
            list += (i + 1) + ". " + tasks.getTask(i).toString() + "\n";
        }

        return list;
    }

    /**
     * <p>parse mark command</p>
     * @param command input given by user
     * @return string confirmation on operation success
     * @since 1.0
     */
    public String parseMark(String command) throws GrassException {
        if (command.length() < 6) {
            throw new GrassException("Please specify the task number to mark.");
        }

        String[] split = command.split(" ");
        int index = Integer.parseInt(split[1]);

        if (index == 0 || index > tasks.getSize()) {
            throw new GrassException("Task number out of range.");
        }

        index--;

        tasks.getTask(index).markAsDone();
        return "Nice! I've marked this task as done:\n" + tasks.getTask(index).toString();
    }

    /**
     * <p>parse unmark command</p>
     * @param command input given by user
     * @return string confirmation on operation success
     * @since 1.0
     */
    public String parseUnmark(String command) throws GrassException {
        if (command.length() < 7) {
            throw new GrassException("Please specify the task number to unmark.");
        }

        String[] split = command.split(" ");
        int index = Integer.parseInt(split[1]);

        if (index == 0 || index > tasks.getSize()) {
            throw new GrassException("Task number out of range.");
        }

        index--;

        tasks.getTask(index).markAsUndone();
        return "OK, I've marked this task as not done yet:\n" + tasks.getTask(index).toString();
    }

    /**
     * <p>parse delete command</p>
     * @param command input given by user
     * @return string confirmation on operation success
     * @since 1.0
     */
    public String parseDelete(String command) throws GrassException {
        if (command.length() < 7) {
            throw new GrassException("Please specify the task number to delete.");
        }

        String[] split = command.split(" ");
        int index = Integer.parseInt(split[1]);

        if (index == 0 || index > tasks.getSize()) {
            throw new GrassException("Task number out of range.");
        }

        index--;
        
        Task task = tasks.getTask(index);
        tasks.deleteTask(index);
        return "Noted. I've removed this task:\n" + task.toString() + "\nNow you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * <p>parse todo command</p>
     * @param command input given by user
     * @return string confirmation on operation success
     * @since 1.0
     */
    public String todoTask(String command) throws GrassException {
        if (command.length() < 5) {
            throw new GrassException("Please specify the description of a todo.");
        }
        String description = command.substring(5);
        Task task = new Todo(description);
        if (tasks.inList(task)) {
            throw new GrassException("Task already exists in the list.");
        }
        tasks.addTask(task);
        return "Got it. I've added this task:\n" + task + "\nNow you have " + tasks.getSize() + " tasks in the list.";
    }
    
    /**
     * <p>parse deadline command</p>
     * @param command input given by user
     * @return string confirmation on operation success
     * @since 1.0
     */
    public String deadlineTask(String command) throws GrassException {
        if (command.length() < 9) {
            throw new GrassException("Please specify the description and deadline of a deadline.");
        }
        String description = command.substring(9);
        if (!description.contains(" /by ")) {
            throw new GrassException("Please specify by when the deadline ends (add /by behind the description).");
        }
        String[] split = description.split(" /by ");
        assert split[0].length() > 0 : "Description cannot be empty.";
        assert split[1].length() > 0 : "Deadline cannot be empty.";
        Task task = new Deadline(split[0], split[1]);
        if (tasks.inList(task)) {
            throw new GrassException("Task already exists in the list.");
        }
        tasks.addTask(task);
        return "Got it. I've added this task:\n" + task + "\nNow you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * <p>parse event command</p>
     * @param command input given by user
     * @return string confirmation on operation success
     * @since 1.0
     */
    public String eventTask(String command) throws GrassException {
        if (command.length() < 6) {
            throw new GrassException("Please specify the description and time of an event.");
        }
        String desc = command.substring(6);
        if (!desc.contains(" /from ")) {
            throw new GrassException("Please specify the start time of an event (add /from behind the description).");
        }
        String[] split = desc.split(" /from ");
        if (!split[1].contains(" /to ")) {
            throw new GrassException("Please specify the end time of an event (add /to behind the start time).");
        }
        String[] leftovers = split[1].split(" /to ");
        assert split[0].length() > 0 : "Description cannot be empty.";
        assert leftovers[0].length() > 0 : "Start time cannot be empty.";
        assert leftovers[1].length() > 0 : "End time cannot be empty.";
        Task task = new Event(split[0], leftovers[0], leftovers[1]);
        if (tasks.inList(task)) {
            throw new GrassException("Task already exists in the list.");
        }
        tasks.addTask(task);
        return "Got it. I've added this task:\n" + task + "\nNow you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * <p>parse find command</p>
     * @param command input given by user
     * @return string confirmation on operation success
     * @since 1.0
     */
    public String findTask(String command) {
        String keyword = command.substring(5);
        String list = "";
        int count = 0;
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            if (task.getDescription().contains(keyword)) {
                list += (i + 1) + ". " + task.toString() + "\n";
                count++;
            }
        }
        if (count == 0) {
            return "No matching tasks found.";
        }
        return "Here are the matching tasks in your list:\n" + list;
    }


}
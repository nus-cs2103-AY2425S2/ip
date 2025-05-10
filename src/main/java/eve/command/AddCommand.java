package eve.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import eve.exception.EveException;
import eve.exception.InvalidCommandException;
import eve.exception.InvalidDateTimeException;
import eve.exception.InvalidDeadlineException;
import eve.exception.InvalidEventException;
import eve.task.Deadline;
import eve.task.Event;
import eve.task.ToDo;
import eve.util.DateTimeUtil;
import eve.util.Storage;
import eve.util.TaskList;


/**
 * Represents a command for adding tasks to the taskList.
 */
public class AddCommand implements Command {
    private final String command;
    private final String description;

    /**
     * Initialize add command with its command and description.
     */
    public AddCommand(String command, String description) {
        this.command = command;
        this.description = description;
    }

    /**
     * Adds the type of task to taskList based on the command and description strings.
     *
     * @param taskList ArrayList containing all the tasks.
     * @param storage  Utils for storing information to data file.
     * @throws EveException Custom exceptions with custom error messages.
     */
    public String execute(TaskList taskList, Storage storage) throws EveException {
        StringBuilder response = new StringBuilder();
        switch (command) {
        case "todo":
            taskList.add(new ToDo(description));
            break;
        case "deadline":
            try {
                String desc = description.split("/by")[0].trim();
                LocalDateTime by = DateTimeUtil.parseString(description.split("/by")[1].trim());
                taskList.add(new Deadline(desc, by));
                break;
            } catch (DateTimeParseException ex) {
                throw new InvalidDateTimeException();
            } catch (Exception ex) {
                throw new InvalidDeadlineException();
            }
        case "event":
            try {
                String desc = description.split("/from")[0].trim();
                LocalDateTime from = DateTimeUtil.parseString(description.split("/from")[1].split("/to")[0].trim());
                LocalDateTime to = DateTimeUtil.parseString(description.split("/from")[1].split("/to")[1].trim());
                taskList.add(new Event(desc, from, to));
                break;
            } catch (DateTimeParseException ex) {
                throw new InvalidDateTimeException();
            } catch (Exception ex) {
                throw new InvalidEventException();
            }
        default:
            throw new InvalidCommandException();
        }
        storage.writeToFile(taskList);
        response.append("Got it. I've added this task:\n\t").append(taskList.get(taskList.size() - 1).toString())
                .append("\nNow you have ").append(Integer.toString(taskList.size())).append(" tasks in the list.");
        return response.toString();
    }

    public boolean isExit() {
        return false;
    }

    public boolean isCloseWindow() {
        return false;
    }
}

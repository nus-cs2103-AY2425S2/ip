package wizt.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import wizt.storage.Storage;
import wizt.task.Deadline;
import wizt.task.Event;
import wizt.task.Task;
import wizt.task.TaskList;
import wizt.task.Todo;
import wizt.ui.Ui;
import wizt.ui.WizTException;


/**
 *  Represents commands that adds tasks into TasksList
 */
public class AddCommand extends Command {
    private static final String SEPARATOR = "\n -------------------------------------";
    private String input;

    public AddCommand() {
        super();
    }

    public AddCommand(String input1) {
        this.input = input1;
    }

    /**
     * Add corresponding task to tasklist based on command.
     * @param tasks
     * @param ui
     * @param storage
     * @throws WizTException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws WizTException {
        ArrayList<Task> tasklists = tasks.getTasksList();
        StringBuilder response = new StringBuilder();
        try {
            if (input.contains("todo")) {
                executeTodo(tasklists, response);
            } else if (input.contains("deadline")) {
                executeDeadline(tasklists, response);
            } else {
                if (input.contains("event")) {
                    executeEvent(tasklists, response);
                }
            }
        } catch (DateTimeParseException e) {
            response.append("Please enter a valid date in this format (dd/MM/yyyy HHmm)!");
        }
        return response.toString();
    }

    /**
     * Represents a Todo Command
     * @param al
     * @param response
     * @throws WizTException
     */
    public void executeTodo(ArrayList<Task>al, StringBuilder response) throws WizTException {
        try {
            String substr = input.substring("todo".length());
            if (substr.isEmpty()) {
                throw new WizTException("Hmm, Please enter a description!");
            }
            Task t = new Todo(substr);
            al.add(t);
            response.append("\n Got it Boss! I've added this task:")
                    .append("\n [T][ ]" + substr).append("\nNow you have " + al.size() + " tasks in the list.");
        } catch (WizTException e) {
            response.append("Hmm, Please enter a valid description!");
        }
    }

    /**
     * Represents a Deadline command
     * @param al
     * @param response
     * @throws WizTException
     */
    public void executeDeadline(ArrayList<Task>al, StringBuilder response) throws WizTException {
        try {
            String substr = input.substring("deadline".length());
            if (substr.isEmpty()) {
                throw new WizTException("Hmm, Please enter a deadline value!");
            }
            String[] as = substr.split(" /by ");
            if (as.length == 1) {
                throw new WizTException("Hmm, Please enter a deadline value!");
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime dt = LocalDateTime.parse(as[1], formatter);
            Task t = new Deadline(as[0], dt);
            al.add(t);
            response.append("\n Got it Boss! I've added this task:").append("\n [D][ ] ").append(as[0]).append(" (by: ")
                    .append(dt.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")))
                    .append(")").append("\n Now you have " + al.size() + " tasks in the list.");
        } catch (WizTException e) {
            response.append("Hmm, Please enter a valid description!");
        }
    }

    /**
     * Represents a Event command
     * @param al
     * @param response
     * @throws WizTException
     */
    public void executeEvent(ArrayList<Task> al, StringBuilder response) throws WizTException {
        try {
            String substr = input.substring("event".length()).trim();
            if (substr.isEmpty()) {
                throw new WizTException("Hmm, Please enter a time period!");
            }
            String[] as = substr.split(" /from ");
            if (as.length < 2) {
                throw new WizTException("Hmm, Please enter a valid 'from' time!");
            }
            String[] as2 = as[1].split(" /to ");
            if (as2.length < 2) {
                throw new WizTException("Hmm, Please enter a valid 'to' time!");
            }
            String eventName = as[0].trim();
            String fromStr = as2[0].trim();
            String toStr = as2[1].trim();
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
            try {
                parseDateTime(fromStr, toStr, eventName, al, inputFormatter, outputFormatter, response);
            } catch (DateTimeParseException e) {
                throw new WizTException("Hmm, Please enter a valid date format (dd/MM/yyyy HHmm)!");
            }
        } catch (WizTException e) {
            response.append("\n ").append(e.getMessage());
        }
    }

    /**
     * Represents a method to parse date time
     * @param fromStr
     * @param toStr
     * @param eventName
     * @param al
     * @param inputFormatter
     * @param outputFormatter
     * @param response
     * @throws WizTException
     */
    public void parseDateTime(String fromStr, String toStr, String eventName, ArrayList<Task>al,
                              DateTimeFormatter inputFormatter, DateTimeFormatter outputFormatter,
                              StringBuilder response) throws WizTException {
        LocalDateTime fromTime = LocalDateTime.parse(fromStr, inputFormatter);
        LocalDateTime toTime = LocalDateTime.parse(toStr, inputFormatter);
        if (fromTime.isAfter(toTime)) {
            throw new WizTException("Hmm, The 'to' date must be after the 'from' date!");
        }
        String formattedFromTime = fromTime.format(outputFormatter);
        String formattedToTime = toTime.format(outputFormatter);
        Task t = new Event(eventName + " (from: " + formattedFromTime + " to: " + formattedToTime + ")");
        al.add(t);
        response.append("\n Got it Boss! I've added this task:")
                .append("\n [E][ ] " + eventName + " (from: " + formattedFromTime)
                .append(" to: " + formattedToTime + ")")
                .append("\n Now you have " + al.size() + " tasks in the list.");
    }

}

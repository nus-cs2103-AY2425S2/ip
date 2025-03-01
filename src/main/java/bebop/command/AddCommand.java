package bebop.command;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import bebop.exception.BebopException;
import bebop.task.Deadline;
import bebop.task.Event;
import bebop.task.TaskList;
import bebop.task.Todo;
import bebop.ui.Storage;
import bebop.ui.Ui;


/**
 * Adds Todo, Deadline and Event Classes into Tasklist.
 */

public class AddCommand extends Command {
    private final String type;
    private final String command;

    /**
     * AddCommand Constructor.
     *
     * @param type type of Task added.
     * @param command command being stored.
     */
    public AddCommand(String type, String command) {
        this.type = type;
        this.command = command;
    }

    /**
     * Adds the command into TaskList.
     *
     * @param tasks Tasklist storing tasks.
     * @param ui Ui to print commands.
     * @param storage stores task into Bebop.txt.
     *
     * @return String if the program will continue or not.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String output = "";
        try {
            if (this.type.equals("t")) {
                return executeTodo(tasks, this.command);
            } else if (this.type.equals("d")) {
                return executeDeadline(tasks, this.command);
            } else {
                return executeEvent(tasks, this.command);
            }
        } catch (BebopException e) {
            return e.getMessage();
        }
    }

    /**
     * Execution of Todo.
     *
     * @param tasks task list to be added.
     * @param command command being stored.
     * @return String to be printed.
     * @throws  BebopException thrown if formatted wrongly.
     */
    public static String executeTodo(TaskList tasks, String command) throws BebopException {
        try {
            String[] todos = command.split("todo ");
            if (isFormatted(todos, "t")) {
                Todo t = new Todo(todos[1], false);
                tasks.addTask(t);
                return t.printSuccess(tasks.size());
            } else {
                throw new BebopException(stringFormat("t"));
            }
        } catch (BebopException b) {
            return b.getMessage();
        }



    }

    /**
     * execution of deadline.
     *
     * @param tasks task list to be added.
     * @param command command being stored.
     * @return String to be printed.
     * @throws  BebopException thrown if formatted wrongly.
     */
    public static String executeDeadline(TaskList tasks, String command) throws BebopException {
        String[] todos = command.split("deadline ");
        if (!isFormatted(todos, "d")) {
            throw new BebopException(stringFormat("d"));
        }
        String[] deadlines = todos[1].split(" /by ");
        if (!isFormatted(deadlines, "d")) {
            throw new BebopException(stringFormat("d"));
        }
        if (isValidLocalDateTime(deadlines[1])) {
            Deadline d = new Deadline(deadlines[0], false, deadlines[1]);
            tasks.addTask(d);
            return d.printSuccess(tasks.size());
        } else {
            throw new BebopException("Incorrect time format! Valid time format is YYYY-MM-DD HH:MM" + "\n"
                    + "Months have to be between 1 - 12" + "\n"
                    + "Days have to be between 1 - 31" + "\n"
                    + "Hours have to be between 0 - 23" + "\n"
                    + "Minutes have to be between 0 - 59" + "\n");
        }
    }

    /**
     * execution of event.
     *
     * @param tasks task list to be added.
     * @param command command being stored.
     * @return String to be printed.
     * @throws BebopException thrown if formatted wrongly.
     */
    public static String executeEvent(TaskList tasks, String command) throws BebopException {
        String[] todos = command.split("event ");
        if (!isFormatted(todos, "e")) {
            throw new BebopException(stringFormat("e"));
        }
        String[] deadlines = todos[1].split(" /from ");
        if (!isFormatted(deadlines, "e")) {
            throw new BebopException(stringFormat("e"));
        }
        String[] events = deadlines[1].split(" /to ");
        if (!isFormatted(events, "e")) {
            throw new BebopException(stringFormat("e"));
        }
        if (isValidLocalDateTime(events[0]) && isValidLocalDateTime(events[1])) {
            Event e = new Event(deadlines[0], false, events[0], events[1]);
            tasks.addTask(e);
            return e.printSuccess(tasks.size());
        } else {
            throw new BebopException("Incorrect time format! Valid time format is YYYY-MM-DD HH:MM" + "\n"
                    + "Months have to be between 1 - 12" + "\n"
                    + "Days have to be between 1 - 31" + "\n"
                    + "Hours have to be between 0 - 23" + "\n"
                    + "Minutes have to be between 0 - 59" + "\n");
        }
    }


    /**
     * return string formats for error messages.
     *
     * @param format type of task
     * @return String to be printed.
     */
    public static String stringFormat(String format) {
        if (format.equals("t")) {
            return "Please give a valid todo format: "
                    + "\"todo EVENTNAME\"";
        } else if (format.equals("d")) {
            return "Please give a valid deadline format: "
                    + "\"deadline EVENTNAME /by ENDTIME\"" + "\n"
                    + "Time format is strictly YYYY-MM-DD HH:MM";
        } else {
            return "Please give a valid event format: "
                    + "\"event EVENTNAME /from STARTTIME /to ENDTIME\"" + "\n"
                    + "Time format is strictly YYYY-MM-DD HH:MM";
        }
    }

    /**
     * checks if the command is formatted correctly.
     *
     * @param list list seperated by dates.
     * @param type type of task.
     *
     * @return boolean if it is correctly formatted.
     */
    public static boolean isFormatted(String[] list, String type) {
        try {
            checkToDo(list, type);
            return true;
        } catch (BebopException b) {
            System.out.println(b.getMessage());
            return false;
        }
    }

    /**
     * checks if the command is formatted correctly.
     *
     * @param arr list seperated by dates.
     * @param s type of task.
     * */
    public static void checkToDo(String[] arr, String s) throws BebopException {
        if (arr.length <= 1) {
            switch (s) {
            case "t":
                throw new BebopException("\tPlease give a valid todo format: "
                        + "\"todo EVENTNAME\"");
            case "d":
                throw new BebopException("\tPlease give a valid deadline format: "
                        + "\"deadline EVENTNAME /by ENDTIME\"");
            case "e":
                throw new BebopException("\tPlease give a valid event format: "
                        + "\"event EVENTNAME /from STARTTIME /to ENDTIME\"");
            default:
                break;
            }
        }
    }

    /**
     * checks if the dateTime is formatted correctly.
     *
     * @param dateTimeStr formatted dateTime.
     *
     * @return boolean if it is correctly formatted.
     */
    public static boolean isValidLocalDateTime(String dateTimeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            LocalDateTime.parse(dateTimeStr, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

}

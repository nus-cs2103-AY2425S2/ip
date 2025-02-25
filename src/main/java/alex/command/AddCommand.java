package alex.command;

import alex.Storage;
import alex.Ui;
import alex.exceptions.MissingContentException;
import alex.task.Deadline;
import alex.task.Event;
import alex.task.Task;
import alex.task.TaskList;
import alex.task.ToDo;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addItem(task, ui, storage);
    }

    /**
     * Parse a input string to a todo command
     * @param inputStr the user input
     * @return todo command
     */
    public static Command parseTodo(String inputStr) {
        return new AddCommand(new ToDo(inputStr.substring(5)));
    }

    /**
     * Parse a input string to a event command
     * @param inputStr the user input
     * @return event command
     * @throws MissingContentException when the content is empty
     */
    public static Command parseEvent(String inputStr) throws MissingContentException {
        int startIndex = inputStr.indexOf("/from");
        String content = inputStr.substring(6, startIndex);
        if (content.isEmpty()) {
            throw new MissingContentException();
        }
        int endIndex = inputStr.indexOf("/to");
        String startTime = inputStr.substring(startIndex + 6, endIndex - 1);
        String endTime = inputStr.substring(endIndex + 4);
        return new AddCommand(new Event(content, startTime, endTime));
    }

    /**
     * Parse a input string to a deadline command
     * @param inputStr the user input
     * @return deadline command
     * @throws MissingContentException when the content is empty
     */
    public static Command parseDeadline(String inputStr) throws MissingContentException {
        int timeIndex = inputStr.indexOf("/by");
        String content = inputStr.substring(9, timeIndex);
        if (content.isEmpty()) {
            throw new MissingContentException();
        }
        return new AddCommand(new Deadline(content, inputStr.substring(timeIndex + 4)));
    }
}

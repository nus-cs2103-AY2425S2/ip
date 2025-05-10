package dudu.controller;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import dudu.model.Deadline;
import dudu.model.Event;
import dudu.model.Task;
import dudu.model.TaskList;
import dudu.utils.DateTimeParser;
import dudu.utils.DuduException;

public class ScheduleCommand implements Command {

    private final String description;

    public ScheduleCommand(String description) {
        this.description = description;
    }

    private String scheduleHeader(int taskCounter, DateTimeFormatter formatter, LocalDateTime targetDate) {
        return (taskCounter == 1)
               ? "Here's what's happening on " + targetDate.format(formatter) + "!\n"
               : "";
    }

    @Override
    public String execute(TaskList tasks, File cachedTasks) {
        int taskCounter = 1;
        String outputText = "";

        try {
            ArrayList<Task> taskList = tasks.getAllTasks();
            LocalDateTime dateToSearch;
            dateToSearch = DateTimeParser.parseDateTime(description);
            DateTimeFormatter dateFormat = DateTimeParser.checkFormat(dateToSearch);

            for (Task curr: taskList) {
                if (curr instanceof Deadline) {
                    if (((Deadline) curr).isOnSameDay(dateToSearch)) {
                        outputText += scheduleHeader(taskCounter, dateFormat, dateToSearch);
                        outputText += taskCounter + ". " + curr + "\n";
                        taskCounter++;
                    }
                } else if (curr instanceof Event) {
                    if (((Event) curr).isWithinDuration(dateToSearch)) {
                        outputText += scheduleHeader(taskCounter, dateFormat, dateToSearch);
                        outputText += taskCounter + ". " + curr + "\n";
                        taskCounter++;
                    }
                }
            }

        } catch (DuduException e) {
            return e.getMessage();
        }

        return (taskCounter == 1) ? "You have no tasks on this day!" : outputText;
    }

    /**
     * Returns false as it is not an exit command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}

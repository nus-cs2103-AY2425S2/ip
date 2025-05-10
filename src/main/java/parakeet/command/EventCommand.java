package parakeet.command;


import java.time.LocalDateTime;

import parakeet.DuplicateTaskError;
import parakeet.Storage;
import parakeet.TaskList;
import parakeet.task.Event;
import parakeet.task.Task;
public class EventCommand extends AddCommand {
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public EventCommand(String description, LocalDateTime startTime, LocalDateTime endTime) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Executes the command to add an Event task to the task list.
     * This method creates a new {@link Event} task with the specified description,
     * start time, and end time, adds it to the task list, and updates the UI with
     * the details of the new task and the total number of tasks.
     *
     * @param taskList the list of tasks to add the event to.
     * @param storage  the storage used to save the tasks (not used in this method).
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DuplicateTaskError {
        Task newEvent = new Event(false, description, startTime, endTime);
        taskList.add(newEvent);

        String response = "Got it. I've added this task: \n" + newEvent.toString() + "\n"
                + "Now you have " + taskList.getSize()+ " tasks in the list";
        return response;

    }
}

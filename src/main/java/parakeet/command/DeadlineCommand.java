package parakeet.command;

import parakeet.DuplicateTaskError;
import parakeet.Storage;
import parakeet.TaskList;
import parakeet.task.Deadline;
import parakeet.task.Task;

import java.time.LocalDateTime;

public class DeadlineCommand extends AddCommand {
    private String description;
    private LocalDateTime deadline;

    public DeadlineCommand(String description, LocalDateTime deadline) {
        this.deadline = deadline;
        this.description = description;
    }

    /**
     * Executes the command to add a Deadline task to the task list.
     * This method creates a new {@link Deadline} task with the specified description,
     * deadline,  adds it to the task list, and updates the UI with
     * the details of the new task and the total number of tasks.
     *
     * @param taskList the list of tasks to add the deadline to.
     * @param storage  the storage used to save the tasks (not used in this method).
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DuplicateTaskError {
        Task newDeadline = new Deadline(false, description, deadline);
        taskList.add(newDeadline);
        String response = "Got it. I've added this task: \n" + newDeadline.toString() + "\n"
                + "Now you have " + taskList.getSize()+ " tasks in the list";

        assert !response.isEmpty():"respone can not be empty";
        
        return response;
    }
}

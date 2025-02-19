package usercommands;

import java.time.LocalDateTime;

import utilities.Command;

/**
 * Concrete class that encapsulates information about the DEADLINE command, including
 * the name of the task and the deadline of the task
 */
public class UserCommandDeadline extends UserCommand {
    private String taskName;
    private LocalDateTime deadline;

    /**
     * Constructor for the UserCommandDeadline class
     * @param taskName Name of the task in the input
     * @param deadline Deadline given in the input
     */
    public UserCommandDeadline(String taskName, LocalDateTime deadline) {
        super(Command.DEADLINE);
        this.taskName = taskName;
        this.deadline = deadline;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }
}

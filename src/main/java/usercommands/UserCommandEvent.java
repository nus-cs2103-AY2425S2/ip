package usercommands;

import java.time.LocalDateTime;

import utilities.Command;

/**
 * Concrete class that encapsulates information about the EVENT command, including
 * the name of the task and the from and to time of the task
 */
public class UserCommandEvent extends UserCommand {
    private String taskName;
    private LocalDateTime fromTime;
    private LocalDateTime toTime;

    /**
     * Constructor for the UserCommandEvent class
     * @param taskName Name of the task in the input
     * @param fromString Start time given in the input
     * @param toString End time given in the input
     */
    public UserCommandEvent(String taskName, LocalDateTime fromString, LocalDateTime toString) {
        super(Command.EVENT);
        this.taskName = taskName;
        this.fromTime = fromString;
        this.toTime = toString;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public LocalDateTime getFromTime() {
        return this.fromTime;
    }

    public LocalDateTime getToTime() {
        return this.toTime;
    }
}

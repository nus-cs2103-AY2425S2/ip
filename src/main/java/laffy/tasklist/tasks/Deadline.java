package laffy.tasklist.tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;

import laffy.tasklist.TaskDateProvider;


/**
 * Represents a task with a deadline.
 * Contains a description and a deadline.
 */
public class Deadline extends Task {
    private static final String TYPE = "D";
    private final LocalDateTime by;

    /**
     * Constructor for Deadline.
     *
     * @param desc The description of the deadline.
     * @param by The deadline of the task.
     */
    public Deadline(String desc, LocalDateTime by) {
        super(desc);
        this.by = by;
    }

    /**
     * Constructor for Deadline.
     *
     * @param desc The description of the deadline.
     * @param isDone The status of the deadline.
     * @param by The deadline of the task.
     */
    public Deadline(String desc, boolean isDone, LocalDateTime by) {
        super(desc, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[" + TYPE + "]" + super.toString()
                + " (by: " + TaskDateProvider.formatDateTime(this.by) + ")";
    }

    @Override
    public ArrayList<String> toTaskData() {
        ArrayList<String> taskData = super.toTaskData();
        taskData.set(0, TYPE);
        taskData.add(TaskDateProvider.formatForStorage(this.by));
        return taskData;
    }

    @Override
    public boolean isUpcoming() {
        return LocalDateTime.now().isBefore(this.by);
    }

    @Override
    public LocalDateTime getDeadline() {
        return this.by;
    }
}

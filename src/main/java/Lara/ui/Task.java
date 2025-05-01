/**
 * Task is an abstract class representing a generic task.
 * Subclasses include Todo, Deadline, and Event.
 *
 * Fields include:
 * - description: The task details.
 * - isDone: Status indicating if the task is completed.
 *
 * Methods include:
 * - markAsDone(): Marks the task as completed.
 * - toString(): Returns a string representation of the task.
 *
 * @author Maliha Haque
 * @version 1.0
 */

package Lara.ui;

import Lara.parser.Date;
import java.time.LocalDateTime;


public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone=true;
    }

    public void markAsUndone() {
        this.isDone=false;
    }

    @Override
    public String toString() {
        return   "[" + getStatusIcon() + "] " + description;
    }

    public LocalDateTime getComparableDate() {
        return null;
    }

    public String toFileFormat() {
        return null;
    }
}


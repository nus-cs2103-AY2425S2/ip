package universe.chores;

import java.time.LocalDateTime;

/**
 * An abstract base class representing different types of chores, including ToDo, Deadline, and Event.
 * It provides functionality to display chore details in multiple formats.
 * Additionally, it supports marking a chore as completed or not completed.
 *
 * <p>
 * This class is based on the Task class partial solution provided.
 * Subclasses should implement additional behavior specific to their type.
 * </p>
 *
 * @author yuqing-tham
 */
public abstract class Chore {
    private final String choreDescription;
    private boolean isDone;

    /**
     * Constructs a new Chore with a specified description, and sets the state isDone to the default false.
     *
     * @param choreDescription A String description of the chore.
     */
    public Chore(String choreDescription) {
        this.choreDescription = choreDescription;
        this.isDone = false;
    }

    /**
     * Returns the private field choreDescription.
     */
    public String getChoreDescription() {
        return choreDescription;
    }

    /**
     * Returns the date and time details.
     * Subclasses ToDo, Deadline and Event should override this method.
     *
     * @return Date and time details of the Chore.
     */
    public abstract LocalDateTime getDateTime();

    /**
     * Returns true if it is a chore with date and time details.
     *
     * @return <code>true</code> if it is a Deadline or Event instance.
     */
    public boolean isChoreWithTime() {
        return false;
    }

    /**
     * Marks the Chore as Done by changing its isDone state to true from the default false.
     */
    public void markAsDone() {
        if (isDone) {
            System.out.println("This Chore has already been completed originally!");
        } else {
            isDone = true;
            System.out.println("Yay " + choreDescription + " successfully completed!");
            System.out.println(this.toString() + "\n");
        }
    }

    /**
     * Marks the Chore as Not Done by changing its isDone state to false.
     */
    public void markAsNotDone() {
        if (!isDone) {
            System.out.println("This Chore has not been completed before. No need to uncheck again :))");
        } else {
            isDone = false;
            System.out.println(choreDescription + " marked as not done yet :(");
            System.out.println(this.toString() + "\n");
        }
    }

    /**
     * Overrides the Object.toString() method to print the choreDescription.
     *
     * @return A formatted String intended to show Chore description and status.
     */
    @Override
    public String toString() {
        String status = isDone ? "X" : " ";
        return "[" + status + "] " + choreDescription;
    }

    /**
     * Prints the choreDescription in the format to be written into the file.
     *
     * @return A formatted String to be written into the Checklist file.
     */
    public String toFileString() {
        String status = isDone ? "1" : "0";
        return " | " + status + " | " + choreDescription;
    }
}

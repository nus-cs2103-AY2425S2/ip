package universe.chores;

import java.time.LocalDateTime;

/**
 * Inherits from the abstract Chore class and provides implementation of methods specific to the ToDo chore type.
 *
 * @author yuqing-tham
 */
public class ToDo extends Chore {
    /**
     * Constructs a new ToDo chore with a specified description.
     *
     * @param choreDescription String description of the ToDo chore.
     */
    public ToDo(String choreDescription) {
        super(choreDescription);
    }

    /**
     * Returns the date and time details.
     * Overrides the parent class Chore's abstract method.
     *
     * @return null Since ToDo has no date and time details.
     */
    @Override
    public LocalDateTime getDateTime() {
        return null;
    }

    /**
     * Overrides the method in the parent class Chore to provide details specific to the chore type.
     *
     * @return A formatted String to show additional details specific to ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Overrides the method in the parent class Chore to provide details specific to the chore type.
     *
     * @return A formatted String to be written into the Checklist file.
     */
    @Override
    public String toFileString() {
        return "T" + super.toFileString();
    }
}

package partillay.task;

/**
 * Represents a To-Do task with a description.
 * This class extends the Task class and provides functionality specific to a To-Do task.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo task with the given description.
     * The task is initially marked as not done.
     *
     * @param description the description of the ToDo task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a new ToDo task with the given description and status.
     * The status is represented by a binary number, where "1" means done and "0" means not done.
     *
     * @param description the description of the ToDo task
     * @param statusBinaryNumber the status of the ToDo task in binary format ("1" for done, "0" for not done)
     */
    public ToDo(String description, String statusBinaryNumber) {
        super(description, statusBinaryNumber);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the ToDo task in a format suitable for saving to a file.
     * The format is: "T | statusBinaryNumber | description".
     *
     * @return a string representation of the ToDo task in a format suitable for file storage
     */
    public String getTxtFormat() {
        return "T"
                + " | "
                + getStatusBinaryNumber()
                + " | "
                + description;
    }

    /**
     * Checks if two ToDo objects are equal based on their description.
     *
     * @param other the object to compare with
     * @return true if the ToDo objects have the same description; false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof ToDo) {
            return description.equals(((ToDo) other).description);
        }
        return false;
    }
}

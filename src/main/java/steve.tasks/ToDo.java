package steve.tasks;

/**
 * Represents a Todo task.
 * This class is used to handle tasks of type "Todo" with a description and their status.
 */
public class ToDo extends Task {
    private final String description;
    private boolean isValid = false;

    /**
     * Constructs a ToDo task with the specified description.
     * If the description is empty, sets a default error message and decreases the task count.
     *
     * @param userDescription the description of the ToDo task
     */
    public ToDo(String userDescription) {
        super(validateDescription(userDescription));
        this.description = validateDescription(userDescription);
        this.isValid = !userDescription.trim().isEmpty();
        validateTask(this.isValid);
    }

    /**
     * Returns the description of empty description error
     */
    private static String emptyInputMessage() {
        return "Description cannot be empty. Usage: todo <description>";
    }

    /**
     * Returns string description of task based on validity of description
     */
    private static String validateDescription(String description) {
        return (description == null || description.trim().isEmpty())
                ? emptyInputMessage()
                : description;
    }

    /**
     * Decreases task count if not valid task
     */
    private void validateTask(boolean isValid) {
        if (!isValid) {
            super.decreaseTaskCount();
        }
    }

    /**
     * Returns the description of the ToDo task.
     *
     * @return the description of the ToDo task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns description of adding a task in
     */
    public String addTaskMessage() {
        return "_________________________\n"
                + "     Got it. I've added this task:\n"
                + "       [T][ ] " + description + "\n"
                + "     Now you have " + TaskManager.getTaskSize() + " tasks in the list.\n"
                + "_________________________\n";
    }
    /**
     * Prints a message indicating that the ToDo task has been added successfully,
     * or prints an error message if the description is empty.
     */
    public void message() {
        String result = !this.description.equals(emptyInputMessage())
                ? addTaskMessage()
                : emptyInputMessage();
        System.out.println(result);
    }

    /**
     * Returns a string message indicating that the ToDo task has been added successfully,
     * or prints an error message if the description is empty.
     */
    public String messageString() {
        return !this.description.equals(emptyInputMessage())
                ? addTaskMessage()
                : emptyInputMessage();
    }

    /**
     * Returns a string representation of the ToDo task, including the task's
     * description and its current status.
     *
     * @return a string representation of the ToDo task
     */
    @Override
    public String toString() {
        return messageString();
    }

    /**
     * Returns the code representing this type of task.
     * For a ToDo task, it returns "T".
     *
     * @return the code for a ToDo task
     */
    @Override
    public String code() {
        return "T";
    }

    /**
     * Returns whether the task is valid. A ToDo task is valid if the description
     * is not empty.
     *
     * @return true if the task is valid, false otherwise
     */
    @Override
    public boolean isValid() {

        return isValid;
    }

    /**
     * Returns the type of task as a string, which is "Todo".
     *
     * @return the type of task
     */
    @Override
    public String type() {
        return "Todo: ";
    }

    /**
     * Returns the original description of the ToDo task.
     *
     * @return the original description of the ToDo task
     */
    @Override
    public String getOriginalDescription() {
        return this.description;
    }
}


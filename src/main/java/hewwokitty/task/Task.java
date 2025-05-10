package hewwokitty.task;

/**
 * Represents the task that user wants to keep track of, as an Object
 */
public abstract class Task {
    private boolean isDone;
    private String description;

    /**
     * Returns a Task object containing the description
     * @param description A String describing what the task is for
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the Task
     * @return A String containing the task description
     */
    public String getDescription() {
        return this.description;
    }
    /**
     * Marks a Task as completed when loading from file
     * Does not print the Task completion message
     */
    public void markAsDoneFile() {
        this.isDone = true;
    }

    /**
     * Marks a Task as completed based on user input
     * Prints a completion message
     */
    public void markAsDone() {
        this.isDone = true;
        System.out.println("i am is so pwoud of youw fow compweting this task <3");
        System.out.println("[X]" + this.description);
    }

    /**
     * Unmarks a Task as completed
     * Prints an incompletion message
     */
    public void unmarkAsDone() {
        this.isDone = false;
        System.out.println("this task has been mawked as not done :(");
        System.out.println("[ ]" + this.description);
    }

    private String printIsDone() {
        if (this.isDone) {
            return "[X] ";
        }
        return "[ ] ";
    }

    /**
     * Formats a string into the format meant to save to file
     * The string displays isDone as 0 or 1 if it has been completed
     * @return A String that has been formatted with isDone and the Task description
     */
    public String writeToFile() {
        return this.getMarkAsInt() + "|" + this.description;
    }

    private int getMarkAsInt() {
        if (this.isDone) {
            return 1;
        }
        return 0;
    }

    /**
     * Formats a string into the format that is displayed to the user
     * The String displays isDone and the Task description
     * @return A String to display the Task information to the user
     */
    @Override
    public String toString() {
        return this.printIsDone() + this.description;
    }
}

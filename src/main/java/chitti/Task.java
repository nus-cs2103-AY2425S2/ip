package chitti;
/**
 * Represents a task with a name and completion status.
 * This class is the base class for different types of tasks (ToDo, Deadline, Event).
 * It supports marking tasks as done or undone and provides methods for
 * saving and loading tasks from files.
 */
public class Task {
    private boolean isDone;
    private String name;

    /**
     * Constructs a new Task with the specified name.
     * Initially, the task is marked as not done.
     *
     * @param name The name of the task.
     */
    public Task(String name){
        this.name = name;
        this.isDone = false;
    }

    /**
     * Creates a Task object from a file format string.
     * This method parses a line from the file and returns the appropriate task
     * (ToDo, Deadline, or Event) based on the file's content.
     *
     * @param line The line from the file representing a task.
     * @return A Task object created from the file format string.
     */
    public static Task fromFileFormat(String line) {
        Task task;
        String[] arr = line.split("\\|");
        if (arr[0].equals("T")){
            task = new ToDoTask(arr[2]);
        } else if (arr[0].equals("D")){
            return new DeadlineTask(arr[2], arr[3]);
        } else {
            return new EventTask(arr[2], arr[3], arr[4]);
        }
        if (arr[1].equals("1")){
            task.doTask();
        }
        return task;
    }

    /**
     * Marks the task as completed.
     * This method sets the task's completion status to true.
     */
    public void doTask(){
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     * This method sets the task's completion status to false.
     */
    public void undoTask(){
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     * The string includes the task's completion status and name.
     *
     * @return A string representation of the task, including its status and name.
     */
    @Override
    public String toString(){
        if (this.isDone){
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }

    /**
     * Returns the task's file format representation.
     * This method is used when saving the task to a file.
     *
     * @return A string representing the task in a format suitable for saving to a file.
     */
    public String toFileFormat() {
        if (this.isDone){
            return "1|" + this.name;
        } else {
            return "0|" + this.name;
        }
    }
}

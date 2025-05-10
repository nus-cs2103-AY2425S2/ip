package dudu.model;

public class Todo extends Task {

    /** Creates Todo object. **/
    public Todo(String name) {
        super(name);
    }

    /**
     * Returns Todo task in String format for data.txt.
     *
     * @param taskType Task type to be displayed.
     * @param isDone Completion of task to be displayed.
     * @return String format of task for data.txt.
     */
    public String toStringFileFormat(String taskType, String isDone) {
        return String.format("%s | %s | %s", taskType, isDone, this.getName());
    }

    /**
     * Returns todo task in String format.
     *
     * @return Line with status, title of task.
     */
    @Override
    public String toString() {
            return "[T]" + super.toString();

    }
}

package alex.task;

public class ToDo extends Task {
    /**
     * Constructor for creating new ToDo
     * @param content
     */
    public ToDo(String content) {
        super(content);
    }

    /**
     * Constructor for loading data from storage
     * @param content
     * @param status
     */
    public ToDo(String content, String status) {
        super(content, status);
    }

    @Override
    public String getSavedDataFormat() {
        return "T | " + super.getSavedDataFormat() + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

package seb.ui;
public class Todo extends Task {

    public Todo(String desc, boolean isDone) {
        super(desc, isDone);
    }

    /**
     * Converts Todo object to String to be stored in data file
     *
     * @return String of event details
     */
    @Override
    public String getDate() {
        return "";
    }

    /**
     * Update description of todo task
     *
     * @param detail the attribute of Todo to be edited
     * @param value the new attribute
     * @throws SebException for invalid input
     */
    @Override
    public void update(String detail, String value) throws SebException {
        if (detail.contains("desc")) {
            this.description = value;
        } else {
            throw new SebException("You can only edit the description of a Todo");
        }
    }

    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

/**
 * The Todo class represents a task without any date/time attached to it.
 * It is a subclass of Task.
 *
 * Example: "Read a book"
 *
 * @author Maliha Haque
 * @version 1.0
 */

package Lara.ui;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

     @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}

package zea.task;

import java.util.ArrayList;

/**
 * A task of type Todo
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Converts the Todo into a String formatted for saving in a file
     * @return the formatted string
     */
    public String toFileFormattedString() {
        ArrayList<String> tags = this.getTags();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tags.size(); i++) {
            if (i != tags.size() - 1) {
                sb.append(tags.get(i)).append(',');
            } else {
                sb.append(tags.get(i));
            }
        }
        return "T|" + (this.isDone ? "1" : "0") + "|" + this.description + "|" + sb;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        return super.equals(o);
    }
}

package zea.task;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * An abstract Task class
 */
public abstract class Task {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    protected String description;
    protected boolean isDone;
    private ArrayList<String> tags;

    /**
     * Constructor for Task
     * @param description - a description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tags = new ArrayList<>();
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void done() {
        this.isDone = true;
    }

    public void undone() {
        this.isDone = false;
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }

    public abstract String toFileFormattedString();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tags.size(); i++) {
            sb.append("#").append(tags.get(i));
            if (i < tags.size() - 1) {
                sb.append(", ");
            }
        }
        return "[" + this.getStatusIcon() + "] " + this.description + "\n tags: " + sb + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        return description.equals(((Task) o).description) && isDone == ((Task) o).isDone;
    }
}

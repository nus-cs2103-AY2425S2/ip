package bun.ui;

public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Converts a task in its stored (String) form to a Task instance
     */
    public static Task stringToTask(String storedTask) {
        if (storedTask == null || storedTask.isEmpty()) {
            return null;
        }
        String[] content = storedTask.split("\\s\\|\\s");

        Task taskToReturn = null;
        try {
            if (storedTask.charAt(0) == 'T') {
                taskToReturn = new ToDo(content[2]);
                if (content[1].equals("1")) {
                    taskToReturn.markAsDone();
                }
            } else if (storedTask.charAt(0) == 'D') {
                taskToReturn = new Deadline(content[2], content[3]);
                if (content[1].equals("1")) {
                    taskToReturn.markAsDone();
                }
            } else {
                assert (storedTask.charAt(0) == 'E');
                taskToReturn = new Event(content[2], content[3], content[4]);
                if (content[1].equals("1")) {
                    taskToReturn.markAsDone();
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid task: " + storedTask);
        }
        return taskToReturn;
    }

    protected String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns whether the task description contains the keyword in the parameter.
     *
     * @param keyword Keyword to search.
     * @return Whether the keyword is part of the description.
     */
    public boolean containsKeyword(String keyword) {
        return this.description.contains(keyword);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }

    public String getStoredString() {
        if (isDone) {
            return "1 | " + description;
        } else {
            return "0 | " + description;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task other) {
            return this.description.equals(other.description) && this.isDone == other.isDone;
        } else {
            return false;
        }

    }
}

package doot;

/**
 * the class for storing information on individual tasks.
 * contains state, description, type. This is an abstract class so that the more specific task classes can inherit
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String tag;

    protected Type type;

    public Task(String description) {
        assert description != null : "Task description should not be null";
        assert !description.isBlank() : "Task description should not be empty";
        this.description = description.strip();
        this.isDone = false;
    }

    /**
     * static class used to make a new class, then returns said class
     * @param str the userInput
     * @return a new task
     * @throws InvalidFormatException for if some of the formatting is invalid
     */
    public static Task makeTask(String str) throws InvalidFormatException {
        assert str != null : "makeTask string should never be null";

        String[] parts = str.split(" ", 2); // Split at first space
        assert parts.length > 0 : "Splitting should always return at least one element";

        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new InvalidFormatException("Tasks must have a description.\n Fix it.");
        }

        String type = parts[0];
        String details = parts[1].trim();


        String[] keywords = {"/tag", "/to", "/from", "/by"};

        for (String keyword : keywords) {
            if (Parser.keywordChecker(str, keyword)) {
                throw new InvalidFormatException("There cannot be multiple " + keyword + " in the input.");
            }
        }

        assert !details.startsWith("/tag") : "exception should have been thrown instead";

        return switch (type) {
            case "todo" -> Parser.parseTodo(details);
            case "deadline" -> Parser.parseDeadline(details);
            case "event" -> Parser.parseEvent(details);
            default -> throw new InvalidFormatException("I do not understand this!");
        };
    }

    /**
     * checks if the str is a substring of the description
     * @param str the substring
     * @return a boolean representing if the substring is in this Task's description
     */
    public boolean isSubstring(String str) {
        assert str != null : "isSubstring string should never be null";
        return this.description.contains(str);
    }

    public String getType() {
        return "[" + type.name() + "]";
    }

    /**
     * The method used to determine what was the string needed to create this task
     * @return the string needed to recreate this task
     */
    public abstract String creationString();

    //returns a string in the format to be passed to the UI to be printed for the user to see
    public String getDetails() {
        return this.getStatusIcon() + " " + this.getDescription();
    }

    /**
     * Returns the formatted status of the task for display to the user.
     *
     * @return A string representing the task's status.
     */

    public String getStatusIcon() {
        return "[" + (isDone ? "X" : " ") + "]"; // mark done task with X
    }

    /**
     * Returns the description of the task to the user
     *
     * @return A string representing the tak's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the task as complete
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Sets the task as not complete
     */
    public void setUndone() {
        this.isDone = false;
    }

    /**
     * Returns the completion of the task
     *
     * @return a boolean representing the completion of the task
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets the tag of the tasj
     * @param tag the tag to be set for the task
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * Gets the tag of the task
     * @return a string representing the tag of the task
     */
    public String getTag() {
        return this.tag;
    }
}

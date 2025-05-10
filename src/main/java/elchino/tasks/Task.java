package elchino.tasks;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents an abstract task that can be done.
 */
public abstract class Task {
    protected final String description;
    protected boolean isDone;
    /* Inspired by chatGPT to use DateTimeFormatter constants */
    protected static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    protected static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy, h:mma");

    /**
     * Constructor for a task.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void setNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the status of the task.
     * @return The status of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Parses a date string into a LocalDateTime object.
     * @param date The date string to parse.
     *             Format: dd/MM/yyyy HHmm
     * @return The LocalDateTime object representing the date.
     * throws IllegalArgumentException If the date string is invalid.
     */
    protected static LocalDateTime parseDate(String date) {
        try {
            return LocalDateTime.parse(date, INPUT_DATE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Fecha inválida. Formato esperado: dd/MM/yyyy HHmm");
        }
    }

    /**
     * Retrieves the task type identifier.
     * @return The task type identifier.
     */
    public abstract String getTaskType();

    /**
     * Converts the task into a string format for storage.
     * @return The string format for storage.
     */
    public abstract String storeTask();

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", description);
    }
}

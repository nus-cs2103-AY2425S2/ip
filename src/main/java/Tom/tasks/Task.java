package Tom.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
//task commit
/**
 * This is an abstract class that serves as the base for specific task types like Todo, Deadline, and Event.
 */
public class Task {
    private String description;
    private boolean status;
    private TaskType taskType;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Constructs a Task with a description and task type.
     *
     * @param description The description of the task.
     * @param taskType The type of task (TODO, DEADLINE, or EVENT).
     */
    public Task(String description, TaskType taskType) {
        this.description = description;
        this.status = false;
        this.taskType = taskType;
    }

    /**
     * Constructs a Task with a description, task type, and completion status.
     *
     * @param description The description of the task.
     * @param taskType The type of task (TODO, DEADLINE, or EVENT).
     * @param status If the task is completed or not
     */
    public Task(String description, TaskType taskType, boolean status) {
        this.description = description;
        this.status = false;
        this.taskType = taskType;
    }

    /**
     * Returns the status of the task.
     *
     * @return "X" if the task is completed, otherwise a space " ".
     */
    public String getStatus() {
        return (status ? "X" : " ");
    }

    /**
     * Returns the String representation of status of the task.
     *
     * @return true if the task is completed, otherwise false.
     */
    public boolean getStringStatus() { return this.status; }

    /**
     * Returns the description of the task.
     *
     * @return String representation of description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done or undone.
     */
    public void markTask() {
        this.status = true;
    }

    /**
     * Marks the task as done or undone.
     */
    public void unmarkTask() {
        this.status = false;
    }

    /**
     * Marks the task as done or undone.
     */
    public TaskType getTaskType() { return this.taskType;  }


    /**
     * Takes in a task from storage and changes it into a task
     *
     * @param line The input that will be converted to a task
     * @return A newly created task with details such as description, deadline and status
     */

    public static Task parseTask(String line) {
        String[] parts = line.split("\\[|\\]"); // Splits using "[" or "]"

        if (parts.length < 5) { // Ensuring that at least type, status, and description exist
            System.out.println("Invalid task format: " + line);
            return null;
        }

        String type = parts[1];
        boolean isDone = parts[3].equals("X");
        String description = parts[4];

        try {
            if (type.equals("T")) {
                return new ToDo(description, isDone);
            } else if (type.equals("D")) {
                if (!description.contains("(by: ")) {
                    System.out.println("Invalid deadline format: " + line);
                }
                String[] descParts = description.split("\\(by: ");
                String desc = descParts[0];
                String deadlineStr = descParts[1].replace(")", "");

                String newDeadlineStr = convertDate(deadlineStr);
                return new Deadlines(desc, isDone, newDeadlineStr);

            } else if (type.equals("E")) {
                if (!description.contains("(From:") || !description.contains("To:")) {
                    System.out.println("Invalid event format: " + line);
                    return null;
                }

                String[] descParts = description.split("\\(From: | To: ");
                String desc = descParts[0];
                String eventFromStr = descParts[1];
                String eventToStr = descParts[2].replace(")", "");
                String newEventFromStr = convertDate(eventFromStr);
                String newEventToStr = convertDate(eventToStr);

                return new Events(desc, isDone, newEventFromStr, newEventToStr);
            } else {
                System.out.println("Unknown task type: " + line);
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error parsing task: " + line);
            return null;
        }
    }

    public static String convertDate(String dateStr) {
        try {
            // Input format: "MMM dd yyyy" (e.g., "Dec 12 2022")
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

            // Output format: "yyyy-MM-dd" (e.g., "2022-12-12")
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            // Parse the date
            LocalDate date = LocalDate.parse(dateStr, inputFormatter);

            // Convert to desired format
            return date.format(outputFormatter);
        } catch (Exception e) {
            System.out.println("Error: Invalid date format - " + dateStr);
            return null; // Return null if the format is incorrect
        }
    }

    /**
     * Returns a string representation of the task.
     *
     * @return The formatted string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatus() + "] " + this.description;
    }

}


package g.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
   protected LocalDate dueDate;

   /**
    * Constructs a Deadline task.
    *
    * @param description The description of the task.
    * @param dueDate The deadline date in `yyyy-MM-dd` format.
    */
   public Deadline(String description, String dueDate) {
      super(description);
      this.dueDate = LocalDate.parse(dueDate.trim());
   }

   /**
    * Constructs a Deadline task with completion status.
    *
    * @param description The description of the task.
    * @param dueDate The deadline date in `yyyy-MM-dd` format.
    * @param isDone Indicates whether the task is completed.
    */
   public Deadline(String description, String dueDate, boolean isDone) {
      super(description, isDone);
      this.dueDate = LocalDate.parse(dueDate.trim());
   }

   @Override
   public String toString() {
      return "[D][" + (isDone ? "X" : " ") + "] " + description + " (by: " + 
             dueDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
   }

   @Override
   public String toFileString() {
      return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + dueDate;
   }
}

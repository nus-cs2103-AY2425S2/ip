package Aquadem;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Class to encapsulate the behaviour of task in the chatbots tasklist
 */
public class Task implements Serializable {

    protected String description;
    protected boolean isDone;

    protected LocalDateTime taskDate;

    protected String statusIcon = " ";

    /**
     * Constructor for class type of Task
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskDate = LocalDateTime.now().plusWeeks(1);
    }

    /**
     * Marks a task as done
     */

    public void markAsDone() {
        this.isDone = true;
        this.statusIcon = "X";
    }

    /**
     * Marks a task as undone
     */
    public void markAsUndone() {
        this.isDone = false;
        this.statusIcon = " ";
    }

    /**
     * Sets the date of a task to a given date
     * @param date
     */
    public void setDate(LocalDateTime date) {
        this.taskDate = date;
    }

    /**
     * Restrives the date of the class
     * @return
     */
    public LocalDateTime getDate(){
        return this.taskDate;
    }

    /**
     * Overriding Object class toString method
     * @return
     */
    @Override
    public String toString() {
        String str = String.format("[%s] %s", this.statusIcon, this.description);
        return str;
    }

}
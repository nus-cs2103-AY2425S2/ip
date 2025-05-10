package task;

import java.util.Objects;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone(){
        this.isDone = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void markAsIncomplete(){
        this.isDone = false;
    }

    /**
     * Returns a string of the task description
     */
    public String getDescription(){
        return description;
    }

    public boolean getDone(){
        return isDone;
    }

    public void updateDes(String des){
         this.description = des;
    }

    public abstract Task clone();

    /**
     * Returns a string representation of the task, including its completion status.
     *
     * @return A string indicating whether the task is done ("[X]") or not ("[ ]"), followed by the task description.
     */
    @Override
    public String toString(){
        String s = isDone ? "[X]" : "[ ]";
        return s + " " + description;
    }

}

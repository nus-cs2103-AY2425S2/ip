package Aquadem;

import java.time.LocalDateTime;

public class Task {

    protected String description;
    protected boolean isDone;

    protected LocalDateTime taskDate;

    protected String statusIcon = " ";
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskDate = LocalDateTime.now().plusWeeks(1);
    }


    public void markAsDone() {
        this.isDone = true;
        this.statusIcon = "X";
    }

    public void markAsUndone() {
        this.isDone = false;
        this.statusIcon = " ";
    }

    public void setDate(LocalDateTime date) {
        this.taskDate = date;
    }

    public String set() {
        return "hello";
    }
    public LocalDateTime getDate(){
        return this.taskDate;
    }

    @Override
    public String toString() {
        String str = String.format("[%s] %s", this.statusIcon, this.description);
        return str;
    }

}
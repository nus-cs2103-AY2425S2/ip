package mars.task;

public class Deadline extends Task {
    public Deadline(String description, boolean isDone){
        super(description, isDone);
    }

    @Override
    public String toString(){
        String str = super.getStatusIcon();
        return str.equals("X") ? "[D][X] " + this.description : "[D][ ] " + this.description;
    }
}

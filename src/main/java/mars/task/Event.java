package mars.task;

public class Event extends Task {
   public Event(String description, boolean isDone){
       super(description, isDone);
   }

    @Override
    public String toString(){
        String str = super.getStatusIcon();
        return str.equals("X") ? "[E][X] " + this.description : "[E][ ] " + this.description;
    }
}

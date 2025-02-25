package mars.task;

public class Todo extends Task {
    public Todo(String description, boolean isDone){
        super(description, isDone);
    }

    @Override
    public String toString(){
        String str = super.getStatusIcon();
        return str.equals("X") ? "[T][X] " + this.description : "[T][ ] " + this.description;
    }


}

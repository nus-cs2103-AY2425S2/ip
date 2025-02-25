package mars.task;

public class DoWithin extends Task {
    public DoWithin(String description, boolean isDone){
        super(description, isDone);
    }

    @Override
    public String toString(){
        String str = super.getStatusIcon();
        return str.equals("X") ? "[DW][X] " + this.description : "[DW][ ] " + this.description;
    }
}

package task;

public class ToDos extends Task {

    public ToDos(String description){
        super(description);
    }

    @Override
    public ToDos clone() {
        if (this.getDone()){
            ToDos t = new ToDos(this.description);
            t.markAsDone();
            return t;
        } else {
            return new ToDos(this.description);
        }
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

}

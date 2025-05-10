package parakeet.task;


public class Todo extends Task {
    public Todo(boolean isDone, String description) {
        super(isDone, description, TaskType.TODO);
    }

    @Override
    public String convertToFileFormat() {
        String str = "T | " + (super.isDone? "1": "0") + " | " + super.description +" | 0 | 0 ";
        return str;
    }
    @Override
    public String toString() {
       String str = "[T]";
       str = str + super.toString();
       return str;
    }

}

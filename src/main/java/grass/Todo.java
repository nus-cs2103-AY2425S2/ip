package grass;
//  todo class for grass

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Todo) {
            Todo other = (Todo) obj;
            return this.description.equals(other.description);
        }
        return false;
    }
}
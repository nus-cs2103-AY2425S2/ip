import java.util.ArrayList;

class TaskList {
    private ArrayList<String> tasks = new ArrayList<>();

    void add(String task) {
        tasks.add(task);
    }

    @Override
    public String toString() {
        int size = tasks.size();
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < size; i++) {
            output.append((i + 1) + ". " + tasks.get(i));

            if (i != size - 1) {
                output.append('\n');
            }
        }

        return output.toString();
    }
}

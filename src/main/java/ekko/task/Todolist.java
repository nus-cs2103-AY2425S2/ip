package ekko.task;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A collection of all the possible activities (todos).
 */
public class Todolist {
    private int count;
    private List<Todo> list;

    /**
     * Instantiates a todolist with number of tasks and an arraylist
     */
    public Todolist() {
        this.count = 0;
        this.list = new ArrayList<>();
    }

    /**
     * Adding a Todo to the list.
     * @param task a todo
     * @return String notation for completing the task
     */
    public String add(Todo task) {
        assert task != null : "Task cannot be null!";
        this.count += 1;
        list.add(task);
        return String.format("Got it! I've added this task: \n%s\nNow you have %d task(s) in the list.",
                task.toString(), count);
    }

    /**
     * Convert the todolist to a long String, separated by new lines.
     * @return long String separated by new lines
     */
    public String toString() {
        return IntStream.range(0, list.size())
                .mapToObj(i -> (i + 1) + ". " + list.get(i).toString())
                .collect(Collectors.joining("\n"));
    }

    /**
     * Convert todolist to a storable string
     */
    public String toStore() {
        return list.stream()
                .map(item -> item.toStore())
                .collect(Collectors.joining("\n"));
    }

    /**
     * Unmark a Todo on the list.
     * @param index the index number of the item
     * @return completion message
     */
    public String unmark(int index) {
        if (index <= 0 || index > list.size()) {
            return "Beware! You can't unmark a task that's not on the list meow!";
        }
        Todo target = list.get(index - 1);
        assert target.isDone() : "The selected task hasn't been marked meow!";
        target.unDo();
        return String.format("Meow, please remember to do it still...\n%s", target);
    }

    /**
     * Mark a Todo as done.
     * @param index the index number of the item
     * @return completion message
     */
    public String mark(int index) {
        assert index > 0 && index <= count : "Index out of bounds for marking task!";
        if (index <= 0 || index > list.size()) {
            return "Beware! You can't mark a task that's not on the list meow!";
        }
        Todo target = list.get(index - 1);
        target.markDone();
        return String.format("Yippee! One task off the list: \n%s", target);
    }

    /**
     * Delete a Todo from the list.
     * @param index the index number of the item
     * @return completion message
     */
    public String delete(int index) {
        assert index > 0 && index <= count : "Selected task not in your list!";
        if (index <= 0 || index > list.size()) {
            return "You can't delete a task that's not on the list meow!";
        }
        Todo target = list.get(index - 1);
        list.remove(index - 1);
        count -= 1;
        return String.format("Ekko has eaten your task!\n%s\nOnly %d tasks left! Jiayouu",
            target.toString(), count);
    }

    /**
     * Find the task with keyword search
     * @param keyword that the task description contain
     * @return the task(s) containing the keyword(s)
     */
    public String filter(String keyword) {
        List<Todo> filteredItems = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            if (list.get(i).printDes().contains(keyword)) {
                filteredItems.add(list.get(i));
            }
        }
        return filteredItems.stream().map(Todo::toString).collect(Collectors.joining("\n"));
    }
}

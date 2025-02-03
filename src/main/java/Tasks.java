import java.util.ArrayList;

public class Tasks {
    final private ArrayList<Task> tasks;

    public Tasks() {
        this.tasks = new ArrayList<>();
    }

    // checks for todos, events and deadlines, initialise to appropriate task type
    // taskType T represents todos, E represents events, D represents deadlines
    public void add(String task, char taskType) {
        Task t;

        try {
            if (taskType == 'T') {
                t = new ToDo(task);
            } else if (taskType == 'E') {
                String[] taskAndDates = task.split(" /from | /to ");
                if (taskAndDates.length < 3) {
                    throw new DukeException("Would you mind double confirming your date format? /from xxx /to xxx");
                }
                t = new Event(taskAndDates[0], taskAndDates[1], taskAndDates[2]);
            } else if (taskType == 'D') {
                String[] taskAndDeadline = task.split(" /by ");
                if (taskAndDeadline.length < 2) {
                    throw new DukeException("Would you mind double confirming your date format? /by xxx");
                }
                t = new Deadline(taskAndDeadline[0], taskAndDeadline[1]);
            } else {
                t = new Task(task);
            }

            tasks.add(t);
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + t + "\nNow you have " + tasks.size() + " tasks in the list.");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateTask(int taskNumber, boolean toBeMarked) {
        Task t = tasks.get(taskNumber - 1);

        if (toBeMarked == !t.getStatus()) {
            boolean isDone = t.updateStatus();
            if (isDone) {
                System.out.println("Nice! I've marked this task as done:\n" + t);
            } else {
                System.out.println("OK, I've marked this task as not done:\n" + t);
            }
        } else {
            // in case user tries to mark/unmarked an already marked/unmarked item
            System.out.println("It seems like it's already been done");
        }
    }

    public void deleteTask(int taskNumber) {
        Task task = tasks.get(taskNumber);
        tasks.remove(taskNumber - 1);
        System.out.println("Noted. I've removed this task:\n" + task + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    // returns all the items in list
    // when list is empty, BROWNIE will tell user
    public void list() {
        if (!tasks.isEmpty()) {
            System.out.println("Here are the tasks in your list:");
            tasks.forEach(task -> System.out.println((tasks.indexOf(task) + 1) + ". " + task));
        } else {
            System.out.println("Yay! You've clear all your task!");
        }
    }
}

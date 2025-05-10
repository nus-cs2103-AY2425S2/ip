package ui;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import task.Task;
import task.Tasklist;

public class Ui {

    private final String name = "Elmacho";

    private String latestResponse;

    public Ui() {
        this.latestResponse = "";
    }

    public String getLatestResponse() {
        assert latestResponse != null : "Latest response should not be null";
        return latestResponse;
    }

    public String userGuide() {
        String userGuide = "QUICK USER GUIDE TO ELMACHO:\n"
                + "\nTo add a ToDo:\n   'todo {task name}'"
                + "\nTo add a Deadline:\n   'deadline {task name} /by yyyy-MM-dd HHmm'"
                + "\nTo add an Event:\n   'event {task name} /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm'\n";

        String userCommands = "\nLIST OF USER COMMANDS:\n"
                + "\n1.  list  <This shows the list of ACTIVE tasks>"
                + "\n2. delete  {task number}"
                + "\n3. mark  {task number}"
                + "\n4. unmark  {task number}"
                + "\n5. find  {keyword}"
                + "\n6. archive  {task number}"
                + "\n7. unarchive  {task number}"
                + "\n8. archive list   <This shows the list of ARCHIVED tasks>"
                + "\n9. motivate me   <You get a motivational line>";
        latestResponse = userGuide + userCommands;
        return latestResponse;
    }

    public String start() {
        return "What. I'm " + name + "\nWhat you want?";
    }

    public void help() {
        latestResponse = "Speak a language I can understand.";
    }

    public void printAddMessage(Tasklist tasklist, Task task) {
        assert task != null : "Task should not be null.";
        latestResponse = "Added task:\n  " + task + "\nNow you have " + tasklist.getNumberOfTasks()
                + " tasks in the list";
    }

    public void printDeleteMessage(Tasklist tasklist, Task task) {
        assert task != null : "Task should not be null.";
        latestResponse = "Deleted task:\n  " + task + "\nNow you have " + tasklist.getNumberOfTasks()
                + " tasks in the list";
    }

    public void printTaskList(Tasklist tasklist) {
        latestResponse = tasklist.getTasks().isEmpty()
                ? "List is empty"
                : "Your never ending list:\n\n" + IntStream.range(0, tasklist.getNumberOfTasks())
                .mapToObj(i -> (i + 1) + ". " + tasklist.getTasks().get(i))
                .collect(Collectors.joining("\n"));
    }

    public void printMarked(Task task) {
        assert task != null : "Task should not be null.";
        latestResponse = "Finally. Marked this task as done:\n  ["
                + task.getStatusIcon() + "] " + task.getDescription();
    }

    public void printUnmarked(Task task) {
        assert task != null : "Task should not be null.";
        latestResponse = "Another task not done...:\n  ["
                + task.getStatusIcon() + "] " + task.getDescription();
    }

    public void printFilteredTasklist(Tasklist tasklist, String keyword) {
        ArrayList<Task> tasks = tasklist.getTasks();
        if (tasks.isEmpty()) {
            latestResponse = "Nothing matched your keyword aw so sad";
        } else {
            latestResponse = IntStream.range(0, tasklist.getNumberOfTasks())
                    .filter(i -> tasklist.getTasks().get(i).getDescription().contains(keyword))
                    .mapToObj(i -> (i + 1) + ". " + tasklist.getTasks().get(i))
                    .collect(Collectors.joining("\n"));
        }
    }

    public void printArchivedTask(Tasklist tasklist, Tasklist archivedTasklist, Task task) {
        assert task != null : "Task should not be null.";
        latestResponse = "Archived task:\n  " + task + "\nNow you have " + archivedTasklist.getNumberOfTasks()
                + " tasks in the archived list,"
                + "\n"  + tasklist.getNumberOfTasks() + " tasks in the list.";
    }

    public void printUnarchivedTask(Tasklist tasklist, Task task) {
        assert task != null : "Task should not be null.";
        latestResponse = "Unarchived task:\n  " + task + "\nNow you have " + tasklist.getNumberOfTasks()
                + " tasks in the list";
    }
    public void printArchivedList(Tasklist tasklist) {
        latestResponse = tasklist.getTasks().isEmpty()
                ? "List is empty"
                : "You avoiding your problems by archiving tasks:\n\n" + IntStream.range(0, tasklist.getNumberOfTasks())
                .mapToObj(i -> (i + 1) + ". " + tasklist.getTasks().get(i))
                .collect(Collectors.joining("\n"));
    }
    public void printMotivation() {
        String[] messages = {"Queen Never Cry",
                "You define your own life. Don't let other people write your script~",
                "Life is tough...but so are you~",
                "Don't let yesterday take up too much of today~",
                "Queen Never Cry." };
        Random random = new Random();
        latestResponse = messages[random.nextInt(messages.length)];
    }
}

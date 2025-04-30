package nikingoda.Ui;

import nikingoda.NikingodaException.NikingodaException;
import nikingoda.Task.Task;
import nikingoda.TaskList.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }


    public String read() throws NikingodaException {
        return this.sc.nextLine();
    }

    public void find(TaskList taskList, String keyword) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the matching tasks: \n");
        ArrayList<Task> tmpTasks = taskList.taskContainsKeyword(keyword);
        int id = 1;
        for (Task task : tmpTasks) {
            System.out.println(id + ". " + task.getDescription());
            id++;
        }
        System.out.println("____________________________________________________________");
    }

    public void greet() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm nikingoda\n" +
                "What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public void exit() {
        sc.close();
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public void list(TaskList taskList) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        taskList.listTasks();
        System.out.println("____________________________________________________________");
    }

    public void mark(TaskList taskList, int id) {
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:\n" +
                taskList.mark(id));
        System.out.println("____________________________________________________________");
    }

    public void unmark(TaskList taskList, int id) {
        System.out.println("____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:\n" +
                taskList.unmark(id));
        System.out.println("____________________________________________________________");
    }

    public void add(TaskList taskList, Task task) {
        taskList.add(task);
        System.out.println("____________________________________________________________\n" +
                "Got it, I've added this task: \n" + task + "\n" +
                "Now you have " + taskList.getSize() + " task(s) in the list.\n" +
                "____________________________________________________________");
    }

    public void delete(TaskList taskList, int id) {
        Task task = taskList.delete(id);
        System.out.println("____________________________________________________________\n" +
                "Noted. I've removed this task: \n" + task + "\n" +
                "Now you have " + taskList.getSize() + " task(s) in the list.\n" +
                "____________________________________________________________");
    }

    public void updateTask(Task task) {
        System.out.println("____________________________________________________________\n" +
                "Noted. I've updated this task: \n" + task + "\n" +
                "____________________________________________________________");
    }

    public void showError(NikingodaException e) {
        System.out.println(e.getMessage());
    }
}

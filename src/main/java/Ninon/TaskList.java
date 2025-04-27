package Ninon;

import Ninon.Task.*;

import java.util.ArrayList;
import java.util.Objects;

public class TaskList {
    private final ArrayList<Task> list = new ArrayList<>(100);
    public TaskList() {

    }

    public TaskList(ArrayList<String> input) {
        for (String data : input) {
            Task task;
            if (Objects.equals(data.split("/")[0], "T ")) {
                task = new Todo(data.split("/")[2].replace(" ", ""));
            } else if (Objects.equals(data.split("/")[0], "D ")) {
                task = new Deadline(data.split("/")[2].replace(" ", ""),
                        data.split("/")[3].replace(" ", ""));
            } else if (Objects.equals(data.split("/")[0], "E ")) {
                task = new Event(data.split("/")[2].replace(" ", ""),
                        data.split("/")[3].replace(" ", ""),
                        data.split("/")[4].replace(" ", ""));
            } else if (Objects.equals(data.split("/")[0], "A ")) {
                if (data.split("/").length == 5) {
                    task = new DoAfter(data.split("/")[2].replace(" ", ""),
                            new Task(data.split("/")[4].replace(" ", "")));
                } else {
                    task = new DoAfter(data.split("/")[2].replace(" ", ""),
                            data.split("/")[3].replace(" ", ""));
                }
            }
            else { continue; }
            if (Objects.equals(data.split("/")[0],"1 ")) {
                task.mark_As_Done();
            }
            this.list.add(task);
        }
    }

    public String add_List(Task task) {
        this.list.add(task);
        String message = "Got it. I've added this task: \n";
        message += task.toString() + "\n";
        message += "Now you have " + list.size() + " tasks in the list.";
        return message;
    }

    public String delete(int i) {
        String message = "Noted. I've removed this task:\n";
        message += list.get(i-1).toString() + "\n";
        this.list.remove(i-1);
        message += "Now you have " + list.size() + " tasks in the list.";
        return message;
    }

    public String mark(int i) {
        Task task = this.list.get(i-1);
        task.mark_As_Done();
        return "Nice! I've marked this task as done:\n" + task.toString() + "\n";
    }

    public String unmark(int i) {
        Task task = this.list.get(i-1);
        task.mark_As_Not_Done();
        return "Nice! I've marked this task as not done yet:\n" + task.toString() + "\n";
    }

    public String find(String description) {
        String message = "";
        assert !(this.list.isEmpty()) : "list is empty";
        for (Task task : this.list) {
            if (task.toString().contains(description)) {
                message += task.toString();
                message += "\n";
            }
        }
        return "Here are the matching tasks in your list:\n" + message + "\n";
    }
    public ArrayList<Task> getList() {
        return this.list;
    }

    public String toString() {
        String message = "Here are the tasks in your list:";
        for (int i = 0; i < list.size(); i++) {
            message += "\n";
            message += (i + 1);
            message += "." + list.get(i).toString();
        }
        return message;
    }
}

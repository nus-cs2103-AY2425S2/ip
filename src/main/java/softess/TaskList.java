package softess;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public String listTasks() {
        StringBuilder taskList = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            taskList.append(String.format("%d. %s%n", (i + 1), this.tasks.get(i).toString()));
        }
        return taskList.toString();
    }


    public String markTaskAsDone(int index) {
        return this.tasks.get(index - 1).markAsDone();
    }

    public String markTaskAsUndone(int index) {
        return this.tasks.get(index - 1).markAsUnDone();
    }

    public String addTask(Task task) {
        this.tasks.add(task);
        String res = "Got it. I've added this task:\n" + task.toString() + "\n Now you have " + tasks.size() + " tasks in the list";
        return res;
    }

    public String removeTask(int index) {
        String res = "Got it. I've deleted this task:\n" + this.tasks.get(index - 1).toString() + "\n Now you have " + (tasks.size() - 1) + " tasks in the list";
        tasks.remove(index - 1);
        return res;
    }

    public String findTasks(String query) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : this.tasks) {
            if (task.toString().toLowerCase().contains(query.toLowerCase())) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            return "No matching tasks found.";
        }

        StringBuilder result = new StringBuilder("Here are the tasks that contained the word you searched for:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            result.append(String.format("%d. %s%n", (i + 1), matchingTasks.get(i).toString()));
        }

        return result.toString();
    }



}

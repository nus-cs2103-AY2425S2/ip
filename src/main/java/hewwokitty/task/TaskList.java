package hewwokitty.task;

import java.util.ArrayList;

import hewwokitty.ui.Ui;

/**
 * A list of Task objects
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Returns an empty TaskList
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Get the number of Tasks in a TaskList
     * @return An int with the number of Tasks in the TaskList
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Marks Task as done at a given order in the TaskList
     * @param i An int representing the order of the Task in TaskList
     */
    public void markDone(int i) {
        try {
            this.tasks.get(i - 1).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("");
        }
    }

    /**
     * Unmarks Task as done at a given order in the TaskList
     * @param i An int representing the order of the Task in TaskList
     */
    public void unmarkDone(int i) {
        try {
            this.tasks.get(i - 1).unmarkAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("");
        }
    }

    /**
     * Adds a Task to the TaskList based on the user specifications
     * Checks user input and creates a Todo, Deadline or Event object accordingly
     * @param userString A String that consists of the user input
     */
    public void addTask(String userString) {
        assert userString != null;
        Task t = null;
        String message;
        String command = userString.split(" ")[0];
        String temp;

        switch (command) {
        case "todo":
            temp = userString.split(" " , 2)[1];
            if (!temp.equals("")) {
                t = new Todo(temp);
            } else {
                Ui.printInvalidDescription();
            }
            break;
        case "deadline":
            temp = userString.split(" " , 2)[1];
            message = temp.split("/")[0];
            String date = temp.split("/")[1].split(" " , 2)[1];
            t = new Deadline(message, date);
            break;
        case "event":
            temp = userString.split(" " , 2)[1];
            message = temp.split("/")[0];
            String fromDate = temp.split("/")[1].split(" " , 2)[1];
            String toDate = temp.split("/")[2].split(" " , 2)[1];
            t = new Event(message, fromDate, toDate);
            break;
        default:
            t = null;
            break;
        }
        if (t != null) {
            this.tasks.add(t);
            System.out.println("i've added youw new task: \n" + t);
            System.out.println("you now hawve " + this.tasks.size() + " tasks, ganbatte");
        }
    }

    /**
     * Removes a Task from TaskList
     * @param i An integer representing the order of the Task in the TaskList
     */
    public void removeTask(int i) {
        System.out.println("i've wemoved youw task: \n" + this.tasks.get(i - 1).toString());
        this.tasks.remove(i - 1);
        System.out.println("you now hawve " + this.tasks.size() + " tasks, ganbatte");
    }

    /**
     * Adds each Task in the data/hewwoTasks.txt file to a TaskList
     * @param task A Task read from the data file
     */
    public void addTaskFromFile(Task task) {
        this.tasks.add(task);
    }

    /**
     * Formats all the Tasks in the TaskList to be written to an output file
     * @return All the Tasks in TaskList as a String
     */
    public String writeToFile() {
        String s = "";
        for (Task t : tasks) {
            s = s + t.writeToFile();
        }
        return s;
    }

    /**
     * Formats all the Tasks in the TaskList to be displayed to the user
     * @return All the Tasks in TaskList as a String
     */
    @Override
    public String toString() {
        if (this.getSize() == 0) {
            return "you hawve no tasks fow me to wist!";
        }
        String s = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            int currTask = i + 1;
            s +=  currTask + ". " + this.tasks.get(i).toString() + "\n";
        }
        return "hewe awe aww youw tasks ono\n" + s;
    }

    /**
     * Finds a Task in TaskList that contains the keyword entered by the user
     * @param userString A String representing user input
     */
    public void findTasks(String userString) {
        String title;
        String s = "";
        String keyword = userString.split(" " , 2)[1];
        int count = 1;
        for (Task t : this.tasks) {
            if (t.getDescription().toLowerCase().contains(keyword)) {
                s = s + count + "." + t.toString() + "\n";
                count++;
            }
        }
        if (count == 1) {
            title = "sowwy pookie, i couwdn't fiwnd any matches :3\n";
        } else {
            title = "hewe awe the matching tasks in youw wist!\n";
        }
        System.out.print(title + s);
    }
}

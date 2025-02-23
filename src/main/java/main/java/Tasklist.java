package main.java;

import Darartole.exception.EmptyBotException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Tasklist {
    /* The list that contains all the task objects */
    protected static ArrayList<Task> list;

    public Tasklist(ArrayList<Task> list) {
        this.list = list;
    }

    public ArrayList<Task> getter() {
        return list;
    }

    /**
     * Returns that number of tasks in the list
     * 
     * @return the number of tasks
     */
    public int size() {
        return list.size();
    }

    /**
     * Adds the task to the list and makes sure there are no duplicates
     * 
     * @param task the task the user wants to add
     */
    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Removes the task from the list
     * 
     * @param number the number of the task that the user want to remove.
     */

    public void removeTask(int number) throws EmptyBotException {
        int size = this.list.size();
        if (number >= size) {
            throw new EmptyBotException("The number of the task you want to delete exceeds the size of the tasklist.");
        } else {
            list.remove(number);
        }
    }

    /**
     * Prints out the content of the tasklist
     */
    public String list() {
        if (this.list.size() == 0) {
            return "Opps. No tasks in the list right now. Add more tasks~~";
        }
        StringBuilder res = new StringBuilder();
        for (int i = 1; i <= list.size(); i++) {
            Task curr = list.get(i - 1);
            res.append(i).append(". ").append(curr.toString()).append("\n");
        }
        return res.toString();
    }

    /**
     * Marks the specific task as finished
     *
     * @param no the number of the task that the user want to mark
     */

    public String mark(int no) {
        StringBuilder res = new StringBuilder();
        if (no >= this.list.size()) {
            return "ILLEGAL INPUT! The number of the task you want to mark exceeds the size of the tasklist.";
        }
        Task target = list.get(no);
        try {
            target.markTask();
        } catch (EmptyBotException e) {
            return e.getMessage();
        }
        res.append("Good job. You have just finished one task.").append("\n")
                        .append("[" + target.getStatusIcon() + "] " + target.getDescription());
        return res.toString();
    }

    /**
     * Unmarks the task that has previously been marked
     *
     * @param no the number of the task that the user wants to unmark
     */
    public String unmark(int no) {
        StringBuilder res = new StringBuilder();
        if (no >= this.list.size()) {
            return "ILLEGAL INPUT! The number of the task you want to unmark exceeds the size of the tasklist.";
        }
        Task target = list.get(no);
        try {
            target.unmarkTask();
        } catch (EmptyBotException e) {
            return e.getMessage();
        }
        res.append("I have helped you unmark the task.").append("\n")
                        .append("[" + target.getStatusIcon() + "] " + target.getDescription());
        return res.toString();
    }

    /**
     * Prints out the specific task in the list
     *
     * @param no the number of the task in the list
     */
    public void printTask(int no) {
        Task task = list.get(no);
        System.out.println(task.toString());
    }

    /**
     * Finds the tasks whose description contains the query string s
     * 
     * @param s the query string that I want to find
     */
    public String findTask(String s) {
        List<Task> matchingList = list.stream()
                .filter(task -> task.getDescription().contains(s))
                .toList();

        if (matchingList.isEmpty()) {
            return "There is no matching task.";
        }
        return IntStream.range(0, matchingList.size())
                .mapToObj(i -> (i + 1) + ". " + matchingList.get(i))
                .collect(Collectors.joining("\n"));
    }


}

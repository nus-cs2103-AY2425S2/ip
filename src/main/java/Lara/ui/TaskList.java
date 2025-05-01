package Lara.ui;

import Lara.exception.LaraException;
import Lara.parser.Date;
import Lara.storage.Storage;
import java.util.Comparator;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void printList() {
        if (tasks.isEmpty()) {
            System.out.println("Your task list is empty.");
            return;
        }

        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public String addTask(String command) throws LaraException {
        String[] words = command.split(" ", 2);
        if (words.length < 2) {
            switch (words[0]){
                case "todo":
                    throw new LaraException("Todo has a format of todo <description> ");
                case "deadline":
                    throw new LaraException("Deadline has a format of <description> /by DD/MM/YYYY HHMM ");
                case "events":
                    throw new LaraException("Event has a format of <description> /from DD/MM/YYYY HHMM  /to DD/MM/YYYY HHMM ");
            }
        }

        String type = words[0];
        String details = words[1];

        for (Task task : tasks) {
            if (task.getDescription().equalsIgnoreCase(details)) {
                throw new LaraException("Duplicate task detected! This task already exists.");
            }
        }

        Task newTask;

        if (type.equals("todo")) {
            newTask = new Todo(details);
        } else if (type.equals("deadline")) {
            String[] by = details.split(" /by ", 2);
            int flag = 0;

            if(by.length < 2) {
                flag = 1;
            }
            if (by.length >= 2) {
                if(!Date.isValidDateTime(by[1])){
                    flag = 2;
                }
            }

            switch (flag){
                case 1:
                    throw new LaraException("Please add a deadline description and specify the deadline with /by, the date and time format has to be DD/MM/YYYY HHMM");
                case 2:
                    throw new LaraException("Invalid deadline date and time format! Please specify the deadline with /by, the date and time format has to be DD/MM/YYYY HHMM");

            }
            newTask = new Deadline(by[0], by[1]);
        } else {
            String[] parts = details.split(" /from | /to ", 3);

            int flag = 0;
            if (parts.length != 3) {
                flag = 1;
            } else {
                int counter = 0;
                if (!Date.isValidDateTime(parts[1])) {
                    flag = 2;
                    counter++;
                }
                if (!Date.isValidDateTime(parts[2])) {
                    flag = 3;
                    counter++;
                }
                flag = counter == 2 ? 4 : flag;
            }

            switch (flag){
                case 1:
                    throw new LaraException("Please check if you have added an event description and specified the event with /from and /to, the date and time format has to be DD/MM/YYYY HHMM");
                case 2:
                    throw new LaraException("/from date or time is invalid, the date and time format has to be DD/MM/YYYY HHMM");
                case 3:
                    throw new LaraException("/to date or time is invalid, the date and time format has to be DD/MM/YYYY HHMM");
                case 4:
                    throw new LaraException("both of the dates and times are invalid, the date and time format has to be DD/MM/YYYY HHMM");
            }


            newTask = new Event(parts[0], parts[1], parts[2]);
        }

        tasks.add(newTask);
        return "Got it. I've added this task:\n" + newTask +
                "\nNow you have " + tasks.size() + " task" + (tasks.size() == 1 ? "" : "s") + " in the list.";
    }

    public String deleteTask(String indexStr) throws LaraException {
        try {
            int index = Integer.parseInt(indexStr) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new LaraException("Please input a valid task number!");
            }
            Task removedTask = tasks.remove(index);
            return "Noted. I've removed this task:\n" + removedTask +
                    "\nNow you have " + tasks.size() + " task" + (tasks.size() == 1 ? "" : "s") + " in the list.";
        } catch (NumberFormatException e) {
            throw new LaraException("Please input a valid task number!");
        }
    }

    public String markTask(String indexStr) throws LaraException {
        try {
            int index = Integer.parseInt(indexStr) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new LaraException("Please input a valid task number!");
            }
            tasks.get(index).markAsDone();
            return "Well done! I've marked this task as done:\n" + tasks.get(index);
        } catch (NumberFormatException e) {
            throw new LaraException("Please input a valid task number!");
        }
    }

    public String unmarkTask(String indexStr) throws LaraException {
        try {
            int index = Integer.parseInt(indexStr) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new LaraException("Please input a valid task number!");
            }
            tasks.get(index).markAsUndone();
            return "OK, I've marked this task as not done yet:\n" + tasks.get(index);

        } catch (NumberFormatException e) {
            throw new LaraException("Please input a valid task number!");
        }
    }

    public String getTaskList(TaskList tasks) {
        if (tasks.getTasks().isEmpty()) {
            return "Your task list is empty.";
        }
        StringBuilder response = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.getTasks().size(); i++) {
            response.append(i + 1).append(". ").append(tasks.getTasks().get(i)).append("\n");
        }
        return response.toString();
    }

    public String findTasks(TaskList tasks, String keyword) {
        StringBuilder response = new StringBuilder("Here are the matching tasks:\n");
        int count = 1;
        for (Task task : tasks.getTasks()) {
            if (task.getDescription().contains(keyword)) {
                response.append(count).append(". ").append(task).append("\n");
                count++;
            }
        }
        if (count == 1) {
            response.append("No matching tasks found.");
        }
        return response.toString();
    }

    public String sortTasks() {
        tasks.sort(Comparator.comparing(Task::getComparableDate, Comparator.nullsLast(Comparator.naturalOrder())));
        return "Your task list has been sorted by deadline or start time.";
    }

}







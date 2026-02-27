package seb.ui;
import java.util.ArrayList;
import java.util.Random;

public class Ui {

    private Random random = new Random();
    private String[] responses = {
        "Damn, girl, is your name Wifi? Because I’m feeling a connection!",
        "Do you have a map? I just got lost in your eyes",
        "Are you French, girl? Because Eiffel for you.",
        "If beauty were a crime, you’d be serving a life sentence.",
        "Are you sure you’re not tired? You’ve been running through my mind all day.",
        "I thought happiness started with an H. Why does mine start with U?",
        "I’m not good at holding conversations. Can I hold your hand instead?",
        "I must be in a museum, because you truly are a work of art.",
        "Did it hurt? When you fell from heaven?",
        "I seem to have lost my phone number. Can I have yours?",
        "Are you a time traveler? Because I see you in my future."
    };
    public Ui() {}

    /**
     * Welcome message printed when Sebastian runs
     */
    public void welcome() {
        System.out.println("Hey there my queen! My name is Sebastian~\nHow can I help you? :)");
    }

    /**
     * Exit message printed for command-line input "bye"
     */
    public void bye() {
        System.out.println("Bye queen! I'll miss u pooks. Seeya :D");
    }

    /**
     * Prints all tasks stored in ArrayList
     *
     * @param tasks list of all tasks
     */
    public void list(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Babes your list is empty. Please add some tasks!");
        } else {
            System.out.println("Queen here is your list:");
            int counter = 1;
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(counter + ". " + tasks.get(i).toString());
                counter++;
            }
        }
    }

    /**
     * Prints error message from exceptions thrown
     *
     * @param message String message from exceptions
     */
    public void showError(String message) {
        System.out.println("Girl.. " + message);
    }

    public void showSuccess(String message) {
        System.out.println(message);
    }

    /**
     * Prints all tasks on given date
     *
     * @param dateInput String input of date, no time
     * @throws SebException for incorrect date format
     */
    public void showDates(ArrayList<Task> tasks, String dateInput) throws SebException {
        // in E, dd MMM yyyy format
        ArrayList<Task> matchingDates = new ArrayList<>();
        for (Task task : tasks) {
            // cut out time to compare date in E
            if (task.getDate().isEmpty()) {
                continue;
            }
            String date = task.getDate().substring(0, task.getDate().lastIndexOf(" "));
            if (dateInput.equals(date)) {
                matchingDates.add(task);
            }
        }
        if (matchingDates.isEmpty()) {
            System.out.println("Yay! You have no tasks on " + dateInput);
        } else {
            System.out.println("Here queen. You have " + matchingDates.size() + " tasks on " + dateInput);
            int counter = 1;
            for (int i = 0; i < matchingDates.size(); i++) {
                System.out.println(counter + ". " + matchingDates.get(i).toString());
                counter++;
            }
        }
    }

    /**
     * Prints all tasks with matching description with input String
     *
     * @param tasks ArrayList tasks
     * @param search String of keyword to match
     */
    public void find(ArrayList<Task> tasks, String search) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(search)) {
                matchingTasks.add(tasks.get(i));
            }
        }
        if (matchingTasks.isEmpty()) {
            System.out.println("Oh! There are no matching tasks queen");
        } else {
            System.out.println("Here queen. These are the matching tasks in your list:");
            int counter = 1;
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println(counter + ". " + matchingTasks.get(i).toString());
                counter++;
            }
        }
    }

    public void sebby() {
        int index = random.nextInt(11);
        System.out.println(responses[index]);
    }
}

package scooby.ui;

import scooby.tasks.Task;

public class Ui {
    private final String name;
    private static final Line LINE = new Line();

    /**
     * Constructs a Ui object for the given chatbot name.
     *
     * @param name the name of the chatbot.
     */
    public Ui(String name) {
        this.name = name;
    }

    /**
     * Greets the user and introduces its given name.
     */
    public void greet() {
        LINE.print();
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        LINE.print();
    }

    /**
     * Exits the dialogue and closes the bot
     */
    public String exitDialogue() {
        // LINE.print();
        return "Bye. Hope to see you again soon!";
        // LINE.print();
    }

    /**
     * Echoes the input of the name
     *
     * @param input the name of the chatbot.
     */
    public void echo(String input) {
        LINE.print();
        System.out.println(input);
        LINE.print();
    }

    /**
     * Prints a message indicating a task has been marked as done.
     *
     * @param task the task that was marked as done.
     */
    public void printTaskMarked(Task task) {
        // LINE.print();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
        // LINE.print();
    }

    /**
     * Prints a message indicating a task has been marked as not done.
     *
     * @param task the task that was marked as not done.
     */
    public void printTaskUnmarked(Task task) {
        // LINE.print();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
        // LINE.print();
    }

    public String returnMarkedString(Task task) {
        return "Nice! I've marked this task as done: \n" + task;
    }

    public String returnUnmarkedString(Task task) {
        return "OK, I've marked this task as not done yet: \n" + task;
    }
}

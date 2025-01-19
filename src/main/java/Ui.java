public class Ui {
    private final String name;

    /**
     * Constructs a Ui object for the given chatbot name.
     *
     * @param name the name of the chatbot.
     */
    Ui(String name) {
        this.name = name;
    }

    /**
     * Greets the user and introduces its given name.
     */
    public void greet() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Exits the dialogue and closes the bot
     */
    public void exitDialogue() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Echoes the input of the name
     *
     * @param input the name of the chatbot.
     */
    public void echo(String input) {
        System.out.println("____________________________________________________________");
        System.out.println(input);
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a message indicating a task has been marked as done.
     *
     * @param task the task that was marked as done.
     */
    public void printTaskMarked(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a message indicating a task has been marked as not done.
     *
     * @param task the task that was marked as not done.
     */
    public void printTaskUnmarked(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
        System.out.println("____________________________________________________________");
    }
}

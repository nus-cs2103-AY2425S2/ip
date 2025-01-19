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
}

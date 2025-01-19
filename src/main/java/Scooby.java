public class Scooby {
    // greet() greets the user by introducing its name
    private static void greet() {
        System.out.println("Hello! I'm Scooby");
        System.out.println("What can I do for you?");
    }

    // exit_dialogue() exits current dialogue, and closes the bot
    private static void exitDialogue() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        greet();
        exitDialogue();
    }
}

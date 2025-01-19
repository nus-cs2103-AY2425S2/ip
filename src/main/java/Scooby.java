import java.util.Scanner;

public class Scooby {
    private TaskList taskList = new TaskList(); // TaskList instance to manage tasks

    /**
     * Adds task to the task list.
     *
     * @param task is  the name of the task to be added into the task list.
     */
    public void addTask(String task) {
        this.taskList.addTask(task);
    }


    /**
     * Starts up the chatbot.
     *
     * @param ui is the user interface defined in the Ui class
     * @param scooby is the chatbot.
     */

    public static void start(Ui ui, Scooby scooby) {
        ui.greet();
        Scanner scanner = new java.util.Scanner(System.in);

        while (true) {
            Parser parser = new Parser(scooby.taskList, ui);
            String userInput = scanner.nextLine().trim();
            if (!parser.parseCommand(userInput)) {
                break; // Exit the loop if the parser returns false
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        Ui ui = new Ui("Scooby");
        Scooby scooby = new Scooby();
        start(ui, scooby);
    }
}

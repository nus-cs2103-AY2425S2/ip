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
            String userInput = scanner.nextLine().trim();
            if (userInput.equalsIgnoreCase("Bye")) {
                ui.exitDialogue();
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                scooby.taskList.listTasks();
            } else {
                scooby.addTask(userInput); // Use the Scooby class's addTask method
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

import java.util.Scanner;
public class UI {
    private Scanner scanner;

    public UI() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        drawLine();
        System.out.println("Hello! I'm Julie.\nWhat can I do for you?");
        drawLine();
    }

    public void drawLine() {
        System.out.println("______________________________________________");
    }

    public void showError(String message) {
        drawLine();
        System.out.println(message);
        drawLine();
    }

    public void showTaskList(TaskList tasks) {
        drawLine();
        System.out.println("Here are you tasks:");
        tasks.showTasks();
        drawLine();
    }

    public void ackMessage(Task task, int size) {
        drawLine();
        System.out.printf("Got it! I've added this task to the list:%n%s%nNow you have %d tasks in the list.%n",
                task.toString(), size);
        drawLine();
    }

    public void deleteMessage(Task task, int size) {
        drawLine();
        System.out.printf(
                "Noted, the following task has been removed:%n%s%nNow you have %d tasks in the list.%n",
                task.toString(), size
        );
        drawLine();
    }

    public void markMessage(Task task) {
        drawLine();
        System.out.printf("Nice! I've marked this task as done!%n%s%n", task.toString());
        drawLine();
    }

    public void unmarkMessage(Task task) {
        drawLine();
        System.out.printf("Okay, I have marked this task as undone!%n%s%n", task.toString());
        drawLine();
    }
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showGoodbye() {
        System.out.println("Goodbye. See you later!");
    }

}

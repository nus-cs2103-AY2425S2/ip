import java.util.Scanner;
public class UI {
    private Scanner scanner;

    public UI() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Julie.\nWhat can I do for you?");
    }

    public void drawLine() {
        System.out.println("______________________________________________");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showTaskList(TaskList tasks) {
        System.out.println("Here are you tasks:");
        tasks.showTasks(); //will add to TaskList later
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showGoodbye() {
        System.out.println("Goodbye. See you later!");
    }

}

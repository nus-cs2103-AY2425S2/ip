import java.util.Scanner;
import java.util.ArrayList;

public class Julie {
    private static final String BREAK = "______________________________________________\n";
    private static final String INTRO = "Hello! I'm Julie\nWhat can I do for you?\n";
    private static final String EXIT = "Bye. See you later!\n";
    private static final ArrayList<Task> taskList = new ArrayList<>();

    public static void addTask(String input) {
        Task task = new Task(input);
        taskList.add(task);
        System.out.println(BREAK + "added: " + input + "\n" + BREAK);
    }

    public static void showTask(ArrayList<Task> list) {
        System.out.println("Here are your tasks:");
        for (int i = 0; i < list.size(); i++) {
            int label = i + 1;
            Task item = list.get(i);
            System.out.println(label + ". " + item.statusIcon() + " " + item.description);
        }
    }

    public static void toggleMarked(int label) {
        int index = label - 1;
        Task currTask = taskList.get(index);
        currTask.markDone();
        System.out.println(BREAK + "Nice! I've marked this task as done!\n" + currTask.statusIcon() + " " +
                currTask.description + "\n" + BREAK);
    }

    public static void toggleUnmarked(int label) {
        int index = label - 1;
        Task currTask = taskList.get(index);
        currTask.markUndone();
        System.out.println(BREAK + "Okay, I have marked this task as undone!\n" + currTask.statusIcon() + " " +
                currTask.description + "\n" + BREAK);
    }

    public static void main(String[] args) {
        System.out.println(BREAK + INTRO + BREAK);

        Scanner scanner = new Scanner(System.in);

        while(true) {
            String input = scanner.nextLine();
            if(input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.print(BREAK);
                showTask(taskList);
                System.out.print(BREAK);
            } else {
                String[] parts = input.split(" ");
                if (parts.length == 2 && parts[0].equals("mark")) {
                    int label = Integer.parseInt(parts[1]);
                    toggleMarked(label);
                } else if (parts.length == 2 && parts[0].equals("unmark")) {
                    int label = Integer.parseInt(parts[1]);
                    toggleUnmarked(label);
                } else {
                    addTask(input);
                }
            }
        }
        System.out.println(BREAK + EXIT + BREAK);
    }
}

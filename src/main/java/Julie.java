import java.util.Scanner;
import java.util.ArrayList;

public class Julie {
    private static final String BREAK = "______________________________________________\n";
    private static final String INTRO = "Hello! I'm Julie\nWhat can I do for you?\n";
    private static final String EXIT = "Bye. See you later!\n";
    private static final ArrayList<String> taskList = new ArrayList<>();

    public static void addTask(String input) {
        taskList.add(input);
        System.out.println(BREAK + "added: " + input + "\n" + BREAK);
    }

    public static void showTask(ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++) {
            int label = i + 1;
            String item = list.get(i);
            System.out.println(label + ". " + item);
        }
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
                addTask(input);
            }
        }
        System.out.println(BREAK + EXIT + BREAK);
    }
}

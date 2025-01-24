import java.util.Scanner;
import java.util.ArrayList;

public class Julie {
    private static final String BREAK = "______________________________________________\n";
    private static final String INTRO = "Hello! I'm Julie\nWhat can I do for you?\n";
    private static final String EXIT = "Bye. See you later!\n";
    private static final ArrayList<Task> taskList = new ArrayList<>();

    public static void showTask(ArrayList<Task> list) {
        System.out.println("Here are your tasks:");
        for (int i = 0; i < list.size(); i++) {
            int label = i + 1;
            Task item = list.get(i);
            System.out.println(label + ". " + item.toString());
        }
    }

    public static void toggleMarked(int label) {
        int index = label - 1;
        Task currTask = taskList.get(index);
        currTask.markDone();
        System.out.println(BREAK + "Nice! I've marked this task as done!");
        System.out.println(currTask.toString() + "\n" + BREAK);
    }

    public static void toggleUnmarked(int label) {
        int index = label - 1;
        Task currTask = taskList.get(index);
        currTask.markUndone();
        System.out.println(BREAK + "Okay, I have marked this task as undone!");
        System.out.println(currTask.toString() + "\n" + BREAK);
    }

    public static String mergeToString(String[] arr, int start, int stop) {
        return String.join(" ", java.util.Arrays.copyOfRange(arr, start, stop));
    }

    public static void ackMessage(Task task) {
        System.out.print(BREAK + "Got it. I've added this task to the list:\n" + task.toString() + "\n" +
                "Now you have " + taskList.size() + " tasks in the list.\n" + BREAK );
    }

    public static void addToDo(String[] input) {
        Task todo = new ToDo(mergeToString(input, 1, input.length));
        taskList.add(todo);
        ackMessage(todo);
    }

    public static void addDeadline(String input) {
        String[] getDeadline = input.split("/by ");
        String desc = getDeadline[0].substring(9, getDeadline[0].length());
        Task deadline = new Deadline(desc, getDeadline[1]);
        taskList.add(deadline);
        ackMessage(deadline);
    }

    public static void addEvent(String input) {
        String[] getEvent = input.split("/from ");
        String desc = getEvent[0].substring(6, getEvent[0].length());
        String[] getTime = getEvent[1].split("/to ");
        Task event = new Event(desc, getTime[0], getTime[1]);
        taskList.add(event);
        ackMessage(event);
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
                } else if (parts[0].equals("todo")) {
                    addToDo(parts);
                } else if (parts[0].equals("deadline")) {
                    addDeadline(input);
                } else if (parts[0].equals("event")) {
                    addEvent(input);
                }
            }
        }
        System.out.println(BREAK + EXIT + BREAK);
    }
}

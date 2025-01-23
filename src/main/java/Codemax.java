import java.util.Scanner;

public class Codemax {
    public static void main(String[] args) {
        Mascot mascot = new Mascot();
        Task[] tasks = new Task[100];
        int count = 0;

        String line = "-".repeat(100) + "\n";
        String intro = "Greetings! I'm Codemax, Your personal coding companion.\n"
                     + "What can I refactor for you today?\n";
        System.out.println(line + intro + line + mascot + line);

        Scanner scan = new Scanner(System.in);
        String input;

        while (true) {
            input = scan.nextLine();
            if (input.equals("bye")) {   //bye input
                System.out.println(line
                        + "\nI hope that you are satisfied with your service.\n"
                        + "See you again soon!\n"
                        + line);
                break;
            } else if (input.equals("list")) {  //list input
                System.out.println(line
                        + "\nHere are the tasks in your list:\n"
                        + line);
                for (int i = 0; i < count; i++) {
                    int num = i + 1;
                    System.out.println(num + "." + tasks[i].toString());
                }
                System.out.println("\n" + line);
            } else if (input.startsWith("mark ")) {   //mark input as done
                int num = Integer.parseInt(input.substring(5)) - 1;
                    if(num >= 0 && num < count) {
                        tasks[num].markAsDone();
                        System.out.println("Nice! I've marked your task as done.\n"
                                            + "Keep up the good work!\n");
                    }
            } else if (input.startsWith("unmark ")) {   //mark input as undone input
                int num = Integer.parseInt(input.substring(7)) - 1;
                if(num >= 0 && num < count) {
                    tasks[num].markAsNotDone();
                    System.out.println("I've unmarked your task.\n"
                            + "Don't give up on it yet!");
                    }
                } else if (count <= 100) {
                tasks[count] = new Task(input);
                count++;
                System.out.println(line + "\n added: " + input + "\n" + line);
            }
        }
        scan.close();
    }
}

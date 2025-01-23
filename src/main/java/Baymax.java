import java.util.Scanner;

public class Baymax {
    public static void main(String[] args) {
        String line = "-".repeat(60) + "\n";
        String intro = "Hello! I'm Baymax, your personal healthcare companion.\n"
                     + "What can I do for you?\n";
        System.out.println(line + intro + line);

        Scanner scan = new Scanner(System.in);
        String input;

        while (true) {
            input = scan.nextLine();
            if (input.equals("bye")) {
                System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);
                break;
            } else {
                System.out.println(line + "\n" + input + "\n" + line);
            }
        }
        scan.close();
    }
}

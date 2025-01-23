import java.util.Scanner;

public class Julie{

    private static final String lineBreak = "______________________________________________\n";
    private static final String introMessage = "Hello! I'm Julie\nWhat can I do for you?\n";
    private static final String goodbyeMessage = "Bye. See you later!\n";

    public static void main(String[] args) {
        System.out.println(lineBreak + introMessage + lineBreak);


        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while(!input.equals("bye")) {
            System.out.println(lineBreak + input + "\n" + lineBreak);
            input = scanner.nextLine();
        }

        System.out.println(lineBreak + goodbyeMessage + lineBreak);
    }
}

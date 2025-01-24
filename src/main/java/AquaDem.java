import java.util.Objects;
import java.util.Scanner;
public class AquaDem {
    public AquaDem(){

    }
    private static final String bar = "____________________________________________________________";
    private String intro() {
        String str = bar + "\n" +  "Hello! I'm AquaDem \n" +
                "What can I do for you?\n" + bar + "\n";
        return str;
    }

    private void running() {

        Scanner inputter = new Scanner(System.in);
        String input = inputter.nextLine();
        while (!Objects.equals(input, "bye")) {
            System.out.println(bar);
            System.out.println(input + "\n");
            System.out.println(bar);
            input = inputter.nextLine();
        }
        System.out.println(bar);
        System.out.println("Cya again soon ;)" + "\n");
        System.out.println(bar);
    }

    public static void main(String[] args) {
        AquaDem chatbot = new AquaDem();
        System.out.print((chatbot.intro()));
        chatbot.running();
    }
}

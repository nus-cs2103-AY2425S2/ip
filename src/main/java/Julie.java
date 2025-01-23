public class Julie{

    private static String lineBreak = "______________________________________________";
    private static String introduction() {
        String introMessage = "Hello! I'm Julie\nWhat can I do for you?";
        return introMessage;
    }

    private static String exit() {
        String goodbyeMessage = "Bye. See you later!";
        return goodbyeMessage;
    }
    public static void main(String[] args) {
        System.out.println(lineBreak);
        System.out.println(introduction());
        System.out.println(lineBreak);
        System.out.println(exit());
        System.out.println(lineBreak);
    }
}

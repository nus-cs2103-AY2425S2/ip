package hewwokitty.ui;

/**
 * Consists of all the interface text displayed to the user
 */
public class Ui {
    /**
     * Displays the starting message upon launching the chatbot
     */
    @SuppressWarnings("checkstyle:Regexp")
    public static void printStart() {
        String logo = "⠀⠀⠀⠀⢀⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⣠⠾⠛⠶⣄⢀⣠⣤⠴⢦⡀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⢠⡿⠉⠉⠉⠛⠶⠶⠖⠒⠒⣾⠋⠀⢀⣀⣙⣯⡁⠀⠀⠀⣿⠀⠀⠀⠀\n"
                + "⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⢸⡏⠀⠀⢯⣼⠋⠉⠙⢶⠞⠛⠻⣆⠀⠀⠀\n"
                + "⠀⠀⠀⢸⣧⠆⠀⠀⠀⠀⠀⠀⠀⠀⠻⣦⣤⡤⢿⡀⠀⢀⣼⣷⠀⠀⣽⠀⠀⠀\n"
                + "⠀⠀⠀⣼⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠙⢏⡉⠁⣠⡾⣇⠀⠀⠀\n"
                + "⠀⠀⢰⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠋⠉⠀⢻⡀⠀⠀\n"
                + "⣀⣠⣼⣧⣤⠀⠀⠀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡀⠀⠀⠐⠖⢻⡟⠓⠒\n"
                + "⠀⠀⠈⣷⣀⡀⠀⠘⠿⠇⠀⠀⠀⢀⣀⣀⠀⠀⠀⠀⠿⠟⠀⠀⠀⠲⣾⠦⢤⠀\n"
                + "⠀⠀⠋⠙⣧⣀⡀⠀⠀⠀⠀⠀⠀⠘⠦⠼⠃⠀⠀⠀⠀⠀⠀⠀⢤⣼⣏⠀⠀⠀\n"
                + "⠀⠀⢀⠴⠚⠻⢧⣄⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⠞⠉⠉⠓⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠈⠉⠛⠛⠶⠶⠶⣶⣤⣴⡶⠶⠶⠟⠛⠉⠀⠀⠀⠀⠀⠀⠀";

        System.out.println(logo);
        System.out.println("hewwo! i'm hewwo kitty!");
        System.out.println("i'm just a chatbot uwuwuuwuwuwuwuwuwuwuwuwuuwuwuw");
    }

    /**
     * Displays the closing message when user ends the chatbot
     */
    public static void printBye() {
        System.out.println("goodbwye! uwu");
    }

    /**
     * Displays a message to the user when their command is not recognised
     */
    public static void printInvalidCommand() {
        System.out.println("i hawve no ideaw what youwe twying to say :( twy again <3");
    }

    /**
     * Displays a message to the user telling them that they have entered an invalid
     * description for a Todo object
     */
    public static void printInvalidDescription() {
        System.out.println("descwiptwion of a todo cawn't be empty! twy again :3");
    }

    /**
     * Displays a message to the user telling them that they have entered a date
     * in an invalid format for a Deadline object
     */
    public static void printInvalidDate() {
        System.out.println("date and time entewed shouwd be in yyyy-MM-dd </3");
    }

    /**
     * Displays a message to the user when they have entered an invalid task number
     */
    public static void printInvalidTaskNumber() {
        System.out.println("the task numbew you hawve entewed is invawid :(");
    }

}

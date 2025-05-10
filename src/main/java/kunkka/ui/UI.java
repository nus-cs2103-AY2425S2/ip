package kunkka.ui;

/**
 * UI class handles the user interface of the application.
 */
public class UI {
    protected String logo = " _______  ______   _______ _________ _______  _______  _          _                 _        _        _        _______ \n"
                    + "(  ___  )(  __  \\ (       )\\__   __/(  ____ )(  ___  )( \\        | \\    /\\|\\     /|( (    /|| \\    /\\| \\    /\\(  ___  )\n"
                    + "| (   ) || (  \\  )| () () |   ) (   | (    )|| (   ) || (        |  \\  / /| )   ( ||  \\  ( ||  \\  / /|  \\  / /| (   ) |\n"
                    + "| (___) || |   ) || || || |   | |   | (____)|| (___) || |        |  (_/ / | |   | ||   \\ | ||  (_/ / |  (_/ / | (___) |\n"
                    + "|  ___  || |   | || |(_)| |   | |   |     __)|  ___  || |        |   _ (  | |   | || (\\ \\) ||   _ (  |   _ (  |  ___  |\n"
                    + "| (   ) || |   ) || |   | |   | |   | (\\ (   | (   ) || |        |  ( \\ \\ | |   | || | \\   ||  ( \\ \\ |  ( \\ \\ | (   ) |\n"
                    + "| )   ( || (__/  )| )   ( |___) (___| ) \\ \\__| )   ( || (____/\\  |  /  \\ \\| (___) || )  \\  ||  /  \\ \\|  /  \\ \\| )   ( |\n"
                    + "|/     \\|(______/ |/     \\|\\_______/|/   \\__/|/     \\|(_______/  |_/    \\/(_______)|/    )_)|_/    \\/|_/    \\/|/     \\|\n";
    protected String horizontalLine = "____________________________________________________________";
    protected String botName = "Kunkka";
    protected String greeting = "Ahoy! I'm " + botName + "\n" + "What can I do for you?";
    protected String farewell = "Farewell, matey! May the wind be at your back!";

    public void printLogo() {
        System.out.println(logo);
    }

    public void printGreeting() {
        System.out.println(horizontalLine + "\n" + greeting + "\n" + horizontalLine + "\n");
    }

    public void printFarewell() {
        System.out.println(horizontalLine + "\n" + farewell + "\n" + horizontalLine);
    }

    public void printHorizontalLine() {
        System.out.println(horizontalLine);
    }

    public void printNewLine() {
        System.out.println();
    }

}

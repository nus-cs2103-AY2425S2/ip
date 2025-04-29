package doot;
import javafx.scene.text.Font;

import static doot.Logo.LOGO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


/**
 * Main Class for the Doot program
 */
public class Doot {
    public TaskList list;
    Parser parser;
    Scanner scanner;


    public Doot() {
        scanner = new Scanner(System.in);
        TaskList list;
        list = new TaskList();
        parser = new Parser(list);
    }

    public static Doot createDoot() throws FileNotFoundException, InvalidFormatException {
        Doot doot = new Doot();
        String filePath = "src" + File.separator + "main" + File.separator + "data" + File.separator + "list.txt";
        doot.list = Storage.loadList(filePath);
        doot.parser.list = doot.list;
        return doot;
    }




    public static void main(String[] args) throws FileNotFoundException, InvalidFormatException {
        System.out.println(Font.getFamilies());
        Scanner scanner = new Scanner(System.in);
        System.out.println("________________________________________________________________________________________________________________________\n"
                + "hello im \n"
                + LOGO + "\n________________________________________________________________________________________________________________________\n");
        String userInput;
        TaskList list;
        list = Storage.loadList("data/list.txt");

        Parser parser = new Parser(list);


        while (!(userInput = scanner.nextLine()).equals("bye")) {
            try {
                parser.handleCommand(userInput);
            } catch (IOException | InvalidFormatException e) {
                System.out.println(e);
            }
        }

        System.out.println("""
                ________________________________________________________________________________________________________________________
                thank mr skeltal
                ________________________________________________________________________________________________________________________
                """);

    }

    public String getResponse(String input) throws IOException, InvalidFormatException {
        return parser.handleCommand(input);
    }

    public String getIntro() {
        return "Take it easy!";
    }
}

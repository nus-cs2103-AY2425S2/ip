package kunkka.mainChatBot;
import java.util.Scanner;

import kunkka.command.Command;
import kunkka.parser.Parser;
import kunkka.storage.Storage;
import kunkka.tasklist.Tasklist;
import kunkka.ui.UI;

public class Kunkka {
    public static void main(String[] args) {

        //Print logo and greeting
        UI ui = new UI();
        ui.printLogo();
        ui.printGreeting();

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        Storage storage = new Storage("./data/kunkka.txt");
        Tasklist tasks = storage.load();

        //Processing commands (MAIN LOOP)
        while (!command.equals("bye")) {
            ui.printHorizontalLine();

            try {
                Command c = Parser.parseCommand(command.trim());
                c.execute(tasks);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            ui.printHorizontalLine();
            ui.printNewLine();
            command = sc.nextLine();
        }

        //Handle bye command
        ui.printFarewell();
        sc.close();
        
        //Save tasks to file
        storage.save(tasks);
    }
}
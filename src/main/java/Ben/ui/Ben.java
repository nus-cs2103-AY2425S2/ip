package ben.ui;

import commands.Commands;
import storage.Storage;
import parser.Parser;
import taskList.TaskList;
import ui.Ui;

import java.util.Scanner;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Ben {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Ben(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream tempOut = new PrintStream(outputStream);
        System.setOut(tempOut);

        try {
            Scanner scanner = new Scanner(input);
            boolean isExit = false;

            while (!isExit && scanner.hasNextLine()) {
                String command = scanner.nextLine();
                Commands c = Parser.executeCommand(command);
                if (c != null) {
                    c.execute(tasks, ui, storage);
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Invalid Input, try again!");
        } finally {
            System.setOut(originalOut);
        }

        return outputStream.toString().trim();

    }


    public void run() {

        ui.introMsg();
        boolean isExit = false;
        Scanner scanner = new Scanner(System.in);
        
        while (!isExit && scanner.hasNextLine()) {
            try {
                String response = scanner.nextLine();
                Commands c = Parser.executeCommand(response);

                if (c != null) {
                    c.execute(tasks, ui, storage);
                    isExit = c.isExit(); //Boolean methods should be named to sound like booleans
                }
            } catch (Exception e) {
                System.out.println("Invalid Input, try again!");
            }
        }

        scanner.close();
    }



    public static void main(String[] args) {
        Ben ben = new Ben("data/ben.txt");
        ben.run();
    }
}

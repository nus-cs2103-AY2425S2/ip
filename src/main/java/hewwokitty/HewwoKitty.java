package hewwokitty;

import java.util.Scanner;

import hewwokitty.parser.Parser;
import hewwokitty.storage.Storage;
import hewwokitty.task.TaskList;
import hewwokitty.ui.Ui;

/**
 * Main chatbot class
 */
public class HewwoKitty {
    public static void main(String[] args) {
        boolean isRunning = true;
        TaskList taskList = Storage.readFromFile();
        Parser parser = new Parser();
        Scanner sc = new Scanner(System.in);

        Ui.printStart();
        while (isRunning) {
            String userInput = sc.nextLine();
            isRunning = parser.processUserCommands(userInput, taskList);
        }
        Ui.printBye();
    }
}

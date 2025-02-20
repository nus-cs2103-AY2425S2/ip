package frontend;

import backend.exceptions.IllegalStartAndEndDateException;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static frontend.Format.HORIZONTAL_LINE;

public class Parser {
    Scanner scanner = new Scanner(System.in);

    SirDuke chatbot;

    public Parser(SirDuke chatbot) {
        this.chatbot = chatbot;
    }



    public void parse() throws IllegalStartAndEndDateException, DateTimeParseException {
        String command = scanner.nextLine();
        String regex = "/";
        String[] parsedCommand = command.split(regex);
        switch (parsedCommand[0]) { //first word of the command
            case "bye":
                chatbot.sayBye();
                break;
            case "list":
                chatbot.showList();
                break;
            case "mark":
                try {
                    chatbot.markTaskAsDone(Integer.parseInt(parsedCommand[1]) - 1);
                } catch (ArrayIndexOutOfBoundsException e) {
                    chatbot.informThatTaskIndexIsMissing(); //missing task index
                } catch (NumberFormatException e) {
                    chatbot.informThatTaskIndexIsNotInteger();
                }
                break;
            case "unmark":
                try {
                    chatbot.unmarkTaskAsDone(Integer.parseInt(parsedCommand[1]) - 1);
                } catch (ArrayIndexOutOfBoundsException e) {
                    chatbot.informThatTaskIndexIsMissing(); //missing task index
                } catch (NumberFormatException e) {
                    chatbot.informThatTaskIndexIsNotInteger();
                }
                break;
            case "todo":
                try {
                    chatbot.createToDoTask(parsedCommand[1]);
                } catch (ArrayIndexOutOfBoundsException e) { //command incomplete
                    chatbot.informThatCommandIsIncomplete();
                }
                break;
            case "deadline":
                try {
                    chatbot.createDeadlineTask(parsedCommand[1], parsedCommand[2]);
                } catch (ArrayIndexOutOfBoundsException e) { //command incomplete
                    chatbot.informThatCommandIsIncomplete();
                }
                break;
            case "event":
                try {
                    chatbot.createEventTask(parsedCommand[1], parsedCommand[2], parsedCommand[3]);;
                } catch (ArrayIndexOutOfBoundsException e) { //command incomplete
                    chatbot.informThatCommandIsIncomplete();
                }
                break;
            case "delete":
                try {
                    chatbot.deleteTask(Integer.parseInt(parsedCommand[1]) - 1);
                } catch (ArrayIndexOutOfBoundsException e) {
                    chatbot.informThatTaskIndexIsMissing(); //missing task index
                } catch (NumberFormatException e) {
                    chatbot.informThatTaskIndexIsNotInteger();
                }
                break;
            case "find":
                try {
                    chatbot.findTask(parsedCommand[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    chatbot.informThatCommandIsIncomplete(); //missing keyword
                }
                break;
            default:
                chatbot.informThatCommandIsInvalid();
                break;
        }
    }
}

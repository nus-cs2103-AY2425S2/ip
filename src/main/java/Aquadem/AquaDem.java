package Aquadem;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.Serializable;

/**
 * The main class through which the chatbot is initiated and run.
 */
public class AquaDem implements Serializable{
    protected TaskList tasks;
    protected Storage storage;

    /**
     * Constructs an instance of AquaDem.
     * @param storage of type <code>Storage</code> to load saved tasks.
     */
    public AquaDem(Storage storage){
        this.storage = storage;
        this.tasks = this.storage.loadTasks();
    }

    /**
     * Prints introductory message on console.
     */
    public void intro() {
        Ui.intro();
    }

    /**
     * Runs the chatbot.
     */
    public void running() {
        while(true){
            try {
                Ui.printBar();
                String input = Ui.generateInput();
                Parser parser = new Parser();
                Pair parsed = parser.encodeCommand(input, tasks.size());
                if (parsed.getHead() == 8) {
                    Execution.execute(parsed, tasks, storage);
                    break;
                } else {
                    Execution.execute(parsed,tasks,storage);
                }
                Ui.printBar();
            } catch (DetailException e) {
                System.out.println(e.getMessage());
                Ui.printBar();
            }

        }
    }

    /**
     * Gets the response for javafx to use and display, Printstream is redirected
     * from console toa special ByteArrayOutputStream.
     * @param input
     * @return
     */
    public String getResponse(String input) {
        String output;
        ByteArrayOutputStream redirect = new ByteArrayOutputStream();
        PrintStream holder = new PrintStream(redirect);
        PrintStream current = System.out;
        System.setOut(holder);
        Parser parser = new Parser();
        try {
            Pair parsed = parser.encodeCommand(input, tasks.size());
            if (parsed.getHead() == 8) {
                Execution.execute(parsed, tasks, storage);
            } else {
                Execution.execute(parsed,tasks,storage);
            }

        } catch (DetailException e) {
            System.out.println(e.getMessage());
            Ui.printBar();
        }

        System.out.flush();
        System.setOut(current);
        output = redirect.toString();
        return output;
    }

    public static void main(String[] args) {
        AquaDem chatbot = new AquaDem(new Storage());
        chatbot.intro();
        chatbot.running();

    }
}

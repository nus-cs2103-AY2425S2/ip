package Aquadem;

import java.io.Serializable;

/**
 * The main class through which the chatbot is initiated and run
 */
public class AquaDem implements Serializable{
    protected TaskList tasks;
    protected Storage storage;

    /**
     * Constructor for the class to initialise the tasks into the current run
     * @param storage of type <code>Storage</code> to load saved tasks
     */
    public AquaDem(Storage storage){
        this.storage = storage;
        this.tasks = this.storage.loadTasks();
    }

    /**
     * Prints introductory message on console
     */
    public void intro() {
        Ui.intro();
    }

    /**
     * Runs the chatbot
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


    public static void main(String[] args) {
        AquaDem chatbot = new AquaDem(new Storage());
        chatbot.intro();
        chatbot.running();

    }
}

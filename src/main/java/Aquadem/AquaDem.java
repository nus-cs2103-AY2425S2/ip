package Aquadem;

import java.io.Serializable;

public class AquaDem implements Serializable{
    protected TaskList tasks;
    protected Storage storage;
    public AquaDem(Storage storage){
        this.storage = storage;
        this.tasks = this.storage.loadTasks();
    }

    public void intro() {
        Ui.intro();
    }
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

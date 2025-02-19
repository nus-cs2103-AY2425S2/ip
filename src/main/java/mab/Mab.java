package mab;

import mab.util.MabStorage;
import mab.util.TaskList;
import mab.util.Ui;
import mab.util.Parser;

import javafx.application.Platform;
import java.io.IOException;

public class Mab {
    MabStorage ms;
    TaskList list;
    Ui ui;

    public Mab() {
        ms = new MabStorage();
        list = new TaskList(ms.read());
        ui = new Ui();
    }

    private void run() {
        ui.showGreeting();
        execute();
        ui.showGoodbye();
    }

    public void execute(){
        String text = "";
        while (true) {
            text = ui.readCommand();
            if (text.equals("bye")) break;
            try{
                Parser.parse(text).execute(list.getTasks());
            } catch (MabException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String getResponse(String input) {
        assert input != null : "input should not be null";
        try{
            if (input.equals("bye")) {
                Platform.exit();
            }
            String output = Parser.parse(input).execute(list.getTasks());
            return output;
        } catch (MabException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) throws IOException{
        Mab mab = new Mab();
        mab.run();
    }
}

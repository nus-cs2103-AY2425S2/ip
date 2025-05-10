package nickiminaj.command;

import nickiminaj.NickiMinajException;
import nickiminaj.Storage;
import nickiminaj.TaskList;
import nickiminaj.Ui;

public class WelcomeCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NickiMinajException {
        System.out.println(" Red Ruby Da Sleeze, Chinese on my sleeve\n" +
                "These wannabe Chun-Li's, Anyway, \n Ni Hao! ");
    }

}

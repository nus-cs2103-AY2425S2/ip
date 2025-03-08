package wizt.command;



import wizt.storage.Storage;
import wizt.task.TaskList;
import wizt.ui.Ui;
import wizt.ui.WizTException;



/**
 *  Represents a base class for commands that users input into the program.
 *  Subclasses will implement specific commands like AddCommand, ExitCommand, etc.
 */
public class Command {
    public Command() {

    }

    /**
     *  Represent a execution of a command
     * @param tasks
     * @param ui
     * @param storage
     * @return a response
     * @throws WizTException
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws WizTException {
        return "Please enter a valid command! \n 1.list \n 2.mark [no.] \n 3.unmark[ no.]"
                + "\n 4.delete [no.] \n 5.find [item] \n 6.bye \n 7.update [no.] \n 8.todo [task]"
                + " \n 9.deadline [task][/by dd/mm/yyyy HHmm]" + "\n (e.g.deadline return book /by 19/02/2019 1800)"
                + "\n 10.event[/from dd/mm/yyyy HHmm /to dd/mm/yyyy HHmm]"
                + "\n (e.g event project meeting /from 19/02/2019 1800 /to 21/02/2019 1800)";
    }

    public boolean isExit() {
        return false;
    }
}

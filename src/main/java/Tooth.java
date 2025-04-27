import tooth.command.Command;
import tooth.exception.ToothException;
import tooth.stuff.Parser;
import tooth.stuff.Storage;
import tooth.stuff.TaskList;
import tooth.stuff.UI;

/**
 * Tooth main class
 */
public class Tooth {
    private final Storage storage = new Storage();
    private final TaskList tasks = new TaskList();
    private final UI ui = new UI();
    private final Parser parser = new Parser();
    /**
     * Taking in a String input, reply a string
     */
    public String respondToUser(String input) {
        try {
            Command c = parser.parse(input);
            c.execute(tasks, ui, storage);
        } catch (ToothException e) {
            ui.complain(e.getMessage());
        }
        return ui.getResponse();
    }
}

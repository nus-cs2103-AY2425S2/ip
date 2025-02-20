package pixel;

import pixel.command.Command;
import pixel.task.TaskList;
import pixel.util.Parser;
import pixel.util.PixelException;
import pixel.util.Storage;
import pixel.util.Ui;

public class Pixel {
    private final TaskList taskList;
    private final Storage storage;

    public Pixel() {
        this.storage = new Storage();
        this.taskList = new TaskList();
    }

    public String init() {
        try {
            this.storage.load(this.taskList);
        } catch (PixelException e) {
            return e.getMessage();
        }
        return Ui.GREET;
    }

    public String getResponse(String input) throws PixelException {
        Command c = Parser.parseFullCommand(input);
        return c.execute(taskList, storage);
    }

}

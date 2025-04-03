package ziyang;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

public class Ziyang {
    private UI ui = new UI();
    private Storage storage = new Storage("database.data");
    private LinkedList<Task> items = storage.read();
    private Parser parser = new Parser(items);

    public String getResponse(String input) {
        return parser.getResponse(input);
    }
}


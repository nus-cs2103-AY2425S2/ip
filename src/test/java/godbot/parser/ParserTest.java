package godbot.parser;

import godbot.task.TaskList;
import godbot.storage.Storage;
import godbot.ui.Ui;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void testInvalidCommand() {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("dummy.txt");
        Ui ui = new Ui();
        String result = Parser.processCommand("random nonsense", taskList, storage, ui);
        assertEquals("Speak properly, mortal. I do not understand you.", result, "Parser should return error");
   }
}


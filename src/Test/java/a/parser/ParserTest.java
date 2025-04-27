package a.parser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import a.tasklist.*;
import a.ui.*;
import java.util.ArrayList;
public class ParserTest {
    @Test
    void testParseBye() {
        Ui ui = new Ui();
        TaskList list = new TaskList(new ArrayList<>());

        boolean[] exitCalled = {false};

        ui = new Ui() {
            @Override
            public void bye() {
                exitCalled[0] = true;
            }
        };
        Parser parser = new Parser();
        parser.parse("bye", ui, list);

        assertTrue(exitCalled[0], "The bye command should set exitCalled to true.");
    }
}
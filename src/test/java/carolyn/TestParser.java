package carolyn;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestParser {
    @Test
    void testParse() {
        Parser parser = new Parser();
        String s = "todo";
        Exception exception = assertThrows(CarolynException.class, () -> {
            parser.parse(s);
        });
        assertEquals("no description for todo", exception.getMessage());
    }
}
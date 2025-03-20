package ujin.helper;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void testParseInfo() {
        String[] expected = {"borrow book", "2024/12/07", "2025/03/08"};
        String[] actual = Parser.parseInfo("borrow book /from 2024/12/07 /to 2025/03/08");

        assertArrayEquals(expected, actual);
    }
}

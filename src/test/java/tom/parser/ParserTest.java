package tom.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import tom.exception.InvalidDateException;

public class ParserTest {
    @Test
    public void stringToDate_validStringFormat_success() throws Exception {
        // parsing date in "yyyy-MM-dd" format
        LocalDate testDate = Parser.stringToDate("2000-01-01");
        assertEquals(LocalDate.of(2000, 1, 1), testDate);
    }

    @Test
    public void stringToDate_invalidStringFormat_exceptionThrown() {
        // parsing date with invalid "yyyy-MM-dd" format
        assertThrows(InvalidDateException.class, () -> {
            Parser.stringToDate("2000-Jan-01");
        });

        // parsing date with out of range month
        assertThrows(InvalidDateException.class, () -> {
            Parser.stringToDate("2000-13-01");
        });
    }
}

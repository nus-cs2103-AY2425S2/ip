package viktor.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class DateParserTest {

    @Test
    public void testParseDateTime_validDateTime() {
        String dateTimeStr = "5/2/2025 1530";
        LocalDateTime expected = LocalDateTime.of(2025, 2, 5, 15, 30);
        LocalDateTime actual = DateParser.parseDateTime(dateTimeStr);
        assertEquals(expected, actual);
    }

    @Test
    public void testParseDateTime_alternativeFormat() {
        String dateTimeStr = "Feb. 5 2025, 15:30";
        LocalDateTime expected = LocalDateTime.of(2025, 2, 5, 15, 30);
        LocalDateTime actual = DateParser.parseDateTime(dateTimeStr);
        assertEquals(expected, actual);
    }

    @Test
    public void testParseDateTime_invalidDateTime() {
        String dateTimeStr = "2025-02-05T15:30"; // Invalid format
        assertThrows(DateTimeParseException.class, () -> DateParser.parseDateTime(dateTimeStr));
    }

    @Test
    public void testFormatDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(2025, 2, 5, 15, 30);
        String expected = "Feb. 5 2025, 15:30";
        String actual = DateParser.formatDateTime(dateTime);
        assertEquals(expected, actual);
    }

    @Test
    public void testParseDateOnly_validDate() {
        String dateInput = "5/2/2025";
        LocalDate expected = LocalDate.of(2025, 2, 5);
        LocalDate actual = DateParser.parseDateOnly(dateInput);
        assertEquals(expected, actual);
    }

    @Test
    public void testParseDateOnly_invalidDate() {
        String dateInput = "2025-02-05"; // Invalid format
        assertThrows(DateTimeParseException.class, () -> DateParser.parseDateOnly(dateInput));
    }

    @Test
    public void testFormatDate() {
        LocalDate date = LocalDate.of(2025, 2, 5);
        String expected = "Feb. 5 2025";
        String actual = DateParser.formatDate(date);
        assertEquals(expected, actual);
    }
}

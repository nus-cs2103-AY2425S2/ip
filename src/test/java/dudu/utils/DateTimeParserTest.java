package dudu.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class DateTimeParserTest {

    @Test
    void testParseDateTimeWithDateTime() throws DuduException {
        String input = "29/1/2025 1800";
        LocalDateTime expected = LocalDateTime.of(2025, 1, 29, 18, 0);
        assertEquals(expected, DateTimeParser.parseDateTime(input));
    }

    @Test
    void testParseDateTimeWithDate() throws DuduException {
        String input = "29/1/2025";
        LocalDateTime expected = LocalDateTime.of(2025, 1, 29, 0, 0);
        assertEquals(expected, DateTimeParser.parseDateTime(input));
    }

    @Test
    void testParseDateTimeWithInvalidFormatThrowsException() {
        Exception exception = assertThrows(DuduException.class, () -> {
            DateTimeParser.parseDateTime("2025-1-29T18:00");
        });
        assertEquals(exception.getMessage(), "Invalid date format. Please use d/M/yyyy or d/M/yyyy HHmm.");
    }

    @Test
    void testParseDateTimeWithEmptyString() {
        Exception exception = assertThrows(DuduException.class, () -> {
            DateTimeParser.parseDateTime("");
        });
        assertEquals(exception.getMessage(), "Invalid date format. Please use d/M/yyyy or d/M/yyyy HHmm.");
    }

    @Test
    void testParseFromFileWithIsoFormat() {
        String input = "2025-01-29T18:00";
        LocalDateTime expected = LocalDateTime.of(2025, 1, 29, 18, 0);
        assertEquals(expected, DateTimeParser.parseFromFile(input));
    }

    @Test
    void testParseFromFileWithInvalidFormatThrowsException() {
        Exception exception = assertThrows(DuduException.class, () -> {
            DateTimeParser.parseDateTime("2025-1-29 18:00");
        });
        assertTrue(exception.getMessage().contains("Invalid date format."));
    }

    @Test
    void testCheckFormatForDateOnly() {
        LocalDateTime dateTime = LocalDateTime.of(2025, 1, 29, 0, 0);
        DateTimeFormatter expectedFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        assertEquals(expectedFormatter.toString(), DateTimeParser.checkFormat(dateTime).toString());
    }

    @Test
    void testCheckFormatForDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(2025, 1, 29, 18, 00);
        DateTimeFormatter expectedFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        assertEquals(expectedFormatter.toString(), DateTimeParser.checkFormat(dateTime).toString());
    }

}

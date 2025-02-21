package nguyen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;



class DateParserTest {

    @Test
    void testValidDates() throws NguyenException {
        assertEquals(LocalDate.of(2024, 1, 1), DateParser.parseDate("2024-01-01"));
        assertEquals(LocalDate.of(2024, 1, 1), DateParser.parseDate("2024/01/01"));
        assertEquals(LocalDate.of(2024, 1, 1), DateParser.parseDate("01/01/2024"));
        assertEquals(LocalDate.of(2024, 1, 1), DateParser.parseDate("01-01-2024"));
        assertEquals(LocalDate.of(2024, 2, 1), DateParser.parseDate("1 Feb 2024"));
        assertEquals(LocalDate.of(2024, 2, 1), DateParser.parseDate("Feb 1 2024"));
        assertEquals(LocalDate.of(2024, 2, 1), DateParser.parseDate("Feb 01 2024"));
        assertEquals(LocalDate.of(2024, 2, 1), DateParser.parseDate("Feb 01, 2024"));
        assertEquals(LocalDate.of(2024, 3, 1), DateParser.parseDate("2024-03-01"));
        assertEquals(LocalDate.of(2024, 3, 1), DateParser.parseDate("2024/03/01"));
        assertEquals(LocalDate.of(2024, 3, 1), DateParser.parseDate("01/03/2024"));
        assertEquals(LocalDate.of(2024, 3, 1), DateParser.parseDate("03-01-2024"));
    }

    @Test
    void testInvalidDates() {
        NguyenException exception1 = assertThrows(NguyenException.class, () -> DateParser.parseDate("2024-13-01"));
        assertEquals("Invalid Date", exception1.getMessage());
        NguyenException exception2 = assertThrows(NguyenException.class, () -> DateParser.parseDate("Hello World"));
        assertEquals("Invalid Date", exception2.getMessage());
        NguyenException exception3 = assertThrows(NguyenException.class, () -> DateParser.parseDate(""));
        assertEquals("Invalid Date", exception3.getMessage());
        NguyenException exception4 = assertThrows(NguyenException.class, () -> DateParser.parseDate("2024.02.01"));
        assertEquals("Invalid Date", exception4.getMessage());
        NguyenException exception5 = assertThrows(NguyenException.class, () -> DateParser.parseDate("2/31"));
        assertEquals("Invalid Date", exception5.getMessage());
    }

}

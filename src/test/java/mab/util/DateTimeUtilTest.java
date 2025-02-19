package mab.util;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import static org.junit.jupiter.api.Assertions.*;

class DateTimeUtilSimpleTest {

    @Test
    void parseDateWithSlashFormat() {
        LocalDateTime result = DateTimeUtil.parseDateTime("01/12/2023 14:30");
        assertEquals(LocalDateTime.of(2023, 12, 1, 14, 30), result);
    }

    @Test
    void parseDateWithHyphenFormat() {
        LocalDateTime result = DateTimeUtil.parseDateTime("2023-12-01 08:15:45");
        assertEquals(LocalDateTime.of(2023, 12, 1, 8, 15, 45), result);
    }

    @Test
    void parseDateWithoutTime() {
        LocalDateTime result = DateTimeUtil.parseDateTime("01-12-2023");
        assertEquals(LocalDateTime.of(2023, 12, 1, 0, 0), result);
    }

    @Test
    void rejectInvalidDate() {
        assertThrows(DateTimeParseException.class, () -> {
            DateTimeUtil.parseDateTime("32/13/2023");  // Invalid day and month
        });
    }

    @Test
    void formatDateTimeCorrectly() {
        LocalDateTime input = LocalDateTime.of(2023, 12, 1, 9, 30, 15);
        String result = DateTimeUtil.localDateTimeToString(input);
        assertEquals("01/12/2023 09:30:15", result);
    }

    @Test
    void handleTimeDefaults() {
        LocalDateTime result = DateTimeUtil.parseDateTime("2023/12/01");
        assertAll(
            () -> assertEquals(0, result.getHour()),
            () -> assertEquals(0, result.getMinute()),
            () -> assertEquals(0, result.getSecond())
        );
    }

    @Test
    void rejectInvalidTime() {
        assertThrows(DateTimeParseException.class, () -> {
            DateTimeUtil.parseDateTime("01-12-2023 25:61");  // Invalid hour and minute
        });
    }

    @Test
    void formatMidnightTime() {
        LocalDateTime input = LocalDateTime.of(2023, 12, 1, 0, 0);
        assertEquals("01/12/2023 00:00:00", DateTimeUtil.localDateTimeToString(input));
    }
}

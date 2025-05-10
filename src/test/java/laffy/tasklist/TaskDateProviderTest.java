package laffy.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class TaskDateProviderTest {

    @Test
    public void parseDateTime_validDate_success() {
        assertEquals("2048-01-24T00:00", TaskDateProvider.parseDateTime("24-01-48").toString());
    }

    @Test
    public void parseDateTime_validDateTime_success() {
        assertEquals("2048-01-24T12:34", TaskDateProvider.parseDateTime("24-01-48 1234").toString());
    }

    @Test
    public void formatDateTime_validDateTime_success() {
        LocalDateTime dateTime = LocalDateTime.parse("2048-01-24T12:34");
        assertEquals("Friday, 24/01/2048, 12:34pm", TaskDateProvider.formatDateTime(dateTime));
    }

    @Test
    public void isValidDateTime_validDateTime_success() {
        assertTrue(TaskDateProvider.isValidDateTime("24-01-48 1234"));
        assertTrue(TaskDateProvider.isValidDateTime("24-01-48"));
    }

    @Test
    public void isValidDateTime_invalidDateTime_success() {
        assertFalse(TaskDateProvider.isValidDateTime("24-01-48 123"));
        assertFalse(TaskDateProvider.isValidDateTime("24-01-48 12345"));
        assertFalse(TaskDateProvider.isValidDateTime("24-01-48 12345a"));
    }

    @Test
    public void formatForStorage_validDateTime_success() {
        LocalDateTime dateTime = LocalDateTime.parse("2048-01-24T12:34");
        assertEquals("2048-01-24T12:34:00", TaskDateProvider.formatForStorage(dateTime));
    }

    @Test
    public void parseFromStorage_validDateTime_success() {
        assertEquals("2048-01-24T12:34", TaskDateProvider.parseFromStorage("2048-01-24T12:34:00").toString());
    }

    @Test
    public void formatDateTime_validDateTimeWithYear_success() {
        LocalDateTime dateTime = LocalDateTime.parse("2048-01-24T12:34");
        assertEquals("Friday, 24/01/2048, 12:34pm", TaskDateProvider.formatDateTime(dateTime));
    }

    @Test
    public void formatDateTime_validDateTimeWithoutYear_success() {
        String currentYear = String.valueOf(LocalDateTime.now().getYear());
        LocalDateTime dateTime = LocalDateTime.parse(currentYear + "-01-24T12:34");
        String day = dateTime.format(DateTimeFormatter.ofPattern("EEEE"));
        assertEquals(day + ", 24/01, 12:34pm", TaskDateProvider.formatDateTime(dateTime));
    }
}

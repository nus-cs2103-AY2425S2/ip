package seb.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;

public class ParserTest {

    private DateTimeFormatter formatOutput;

    @BeforeEach
    void setup() {
        formatOutput = DateTimeFormatter.ofPattern("E, dd MMM yyyy HH:mm");
    }

    @Test
    public void testParseTodo() throws SebException {
        String input = "Buy milk";
        assertEquals("Buy milk", Parser.parseTodo(input));
    }

    @Test
    public void testParseDeadline() throws SebException {
        String input = "Submit report/ 2025-02-10";
        String[] result = Parser.parseDeadline(input);
        assertEquals("Submit report", result[0]);
        assertEquals("2025-02-10", result[1]);
    }

    @Test
    public void testParseEvent() throws SebException {
        String input = "Meeting/ 10am / 12pm";
        String[] result = Parser.parseEvent(input);
        assertEquals("Meeting", result[0]);
        assertEquals("10am", result[1]);
        assertEquals("12pm", result[2]);
    }

    @Test
    void checkDateValidity_endDateBeforeStart_shouldThrowException() {
        LocalDateTime futureStart = LocalDateTime.now().plusDays(5);
        LocalDateTime futureEnd = LocalDateTime.now().plusDays(1);
        Exception exception = assertThrows(SebException.class, () -> Parser.checkDateValidity(futureStart, futureEnd));
        assertEquals("End date must be after start date!", exception.getMessage());
    }

    @Test
    void checkDateValidity_pastDate_shouldThrowException() {
        LocalDateTime pastDate = LocalDateTime.now().minusDays(1);
        Exception exception = assertThrows(SebException.class, () -> Parser.checkDateValidity(pastDate));
        assertEquals("Date entered cannot be in the past!", exception.getMessage());
    }
}


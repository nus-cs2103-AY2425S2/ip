package Aquadem;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ExecutionTest {
    @Test
    public void dateConvertTest1() {
        try {
            DateTimeFormatter acceptedFormat = new DateTimeFormatterBuilder()
                    .append(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")).toFormatter();
            assertEquals(Execution.dateConvert("2025-12-12 00:00"), LocalDateTime.parse("2025-12-12 00:00",acceptedFormat));
        } catch (DateTimeParseException e) {
            //
        }
    }

    @Test
    public void dateConvertTest2() {
        try {
            DateTimeFormatter acceptedFormat = new DateTimeFormatterBuilder()
                    .append(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")).toFormatter();
            System.out.println(Execution.dateConvert("2025-12-12"));
            System.out.println(LocalDateTime.now().plusWeeks(1));
            assertEquals(Execution.dateConvert("2025-12-12").truncatedTo(ChronoUnit.MINUTES), LocalDateTime.now().plusWeeks(1).truncatedTo(ChronoUnit.MINUTES));
        } catch (DateTimeParseException e) {
            //
        }
    }
}

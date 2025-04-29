package lemon;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void DeadlineTest() {
        assertEquals("[D][ ] doing research/15 FEBRUARY 2025",
                new Deadline("doing research", LocalDate.parse("2025-02-15")).toString());
    }


}

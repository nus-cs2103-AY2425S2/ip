package stonks.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StonksExceptionTest {
    @Test
    public void testExceptionMessage() {
        Exception exception = assertThrows(StonksException.class, () -> {
            throw new StonksException("Test error message");
        });

        assertEquals("Test error message", exception.getMessage(), "Exception message should match");
    }

}

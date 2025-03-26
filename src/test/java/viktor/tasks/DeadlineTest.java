package viktor.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void testDeadlineConstructor() {
        DeadlineTask deadline = new DeadlineTask("return book", "1/2/2023 1800");
        assertNotNull(deadline);
    }

    @Test
    public void testGetType() {
        DeadlineTask deadline = new DeadlineTask("return book", "1/2/2023 1800");
        assertEquals("D", deadline.getType());
    }
    @Test
    public void testGetDescription() {
        DeadlineTask deadline = new DeadlineTask("return book", "1/2/2023 1800");
        assertEquals("return book", deadline.getDescription());
    }
    @Test
    public void testMatchesDate() {
        DeadlineTask deadline = new DeadlineTask("return book", "1/2/2023 1800");
        assertTrue(deadline.matchesDate(LocalDate.of(2023, 2, 1)));
    }
    @Test
    public void testToString() {
        DeadlineTask deadline = new DeadlineTask("return book", "1/2/2023 1800");
        assertEquals("[D][ ] return book (by: Feb. 1 2023, 18:00)", deadline.toString());
    }
    @Test
    public void testBeDone() {
        DeadlineTask deadline = new DeadlineTask("return book", "1/2/2023 1800");
        deadline.beDone();
        assertEquals("[D][X] return book (by: Feb. 1 2023, 18:00)", deadline.toString());
    }
    @Test
    public void testBeUndone() {
        DeadlineTask deadline = new DeadlineTask("return book", "1/2/2023 1800");
        deadline.beDone();
        deadline.beUndone();
        assertEquals("[D][ ] return book (by: Feb. 1 2023, 18:00)", deadline.toString());
    }
    @Test
    public void testToSave() {
        DeadlineTask deadline = new DeadlineTask("return book", "1/2/2023 1800");
        assertEquals("D |   | return book | Feb. 1 2023, 18:00", deadline.toSave());
    }
}

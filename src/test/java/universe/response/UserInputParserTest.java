package universe.response;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

/**
 * Unit test class for UserInputParser.
 * This class verifies the correct parsing of different user inputs, such as todos, deadlines, events,
 * and other commands, ensuring the expected output is returned.
 */
public class UserInputParserTest {
    private final String response = "todo finish watching lecture 2";
    private UserInputParser userInputParser = new UserInputParser(response);
    private final String response1 = "deadline CA2 by 14 Apr 2025 2359";
    private UserInputParser userInputParser1 = new UserInputParser(response1);
    private final String response2 = "event concert from 5 Mar 2025 1800 to 5 Mar 2025 2000";
    private UserInputParser userInputParser2 = new UserInputParser(response2);

    /**
     * Tests the getDescription() method.
     * Ensures that the correct description is extracted from a "todo" command.
     */
    @Test
    public void getDescriptionTest() {
        assertEquals("finish watching lecture 2", userInputParser.getDescription());
    }

    /**
     * Tests the getDeadlineDescription() method.
     * Ensures that the correct description is extracted from a "deadline" command.
     */
    @Test
    public void getDeadlineDescriptionTest() {
        assertEquals("CA2", userInputParser1.getDeadlineDescription());
    }

    /**
     * Tests the getDeadlineDateDate() method.
     * Ensures that the correct date is extracted from a "deadline" command.
     */
    @Test
    public void getDeadlineDateTest() {
        assertEquals(LocalDateTime.parse("2025-04-14T23:59"), userInputParser1.getDeadlineDate());
    }

    /**
     * Tests the getEventDescription() method.
     * Ensures that the correct description is extracted from an "event" command.
     */
    @Test
    public void getEventDescriptionTest() {
        assertEquals("concert", userInputParser2.getEventDescription());
    }

    /**
     * Tests the getStartTime() method.
     * Ensures that the correct start date and time is extracted from an "event" command.
     */
    @Test
    public void getStartTimeTest() {
        assertEquals(LocalDateTime.parse("2025-03-05T18:00"), userInputParser2.getStartTime());
    }

    /**
     * Tests the getEndTime() method.
     * Ensures that the correct end date and time is extracted from an "event" command.
     */
    @Test
    public void getEndTimeTest() {
        assertEquals(LocalDateTime.parse("2025-03-05T20:00"), userInputParser2.getEndTime());
    }

    /**
     * Tests the getChoreNumber() method.
     * Ensures that the chore number is correctly extracted from a "remove" command.
     */
    @Test
    public void getChoreNumberTest() {
        String response = "remove 3";
        UserInputParser userInputParser = new UserInputParser(response);
        assertEquals(3, userInputParser.getChoreNumber());
    }

    /**
     * Tests the getFilterDateString() method.
     * Ensures that the correct date string is extracted from a "filter" command.
     */
    @Test
    public void getFilterDateStringTest() {
        String response = "filter 23 Feb 2025";
        UserInputParser userInputParser = new UserInputParser(response);
        assertEquals("23 Feb 2025", userInputParser.getFilterDateString());
    }

    /**
     * Tests the getFilterDate() method.
     * Ensures that the correct date is parsed from a "filter" command.
     */
    @Test
    public void getFilterDateTest() {
        String response = "filter 23 Feb 2025";
        UserInputParser userInputParser = new UserInputParser(response);
        assertEquals(LocalDateTime.parse("2025-02-23T00:00"), userInputParser.getFilterDate());
    }

    /**
     * Tests the getFindKeyword() method.
     * Ensures that the correct keyword is extracted from a "find" command.
     */
    @Test
    public void getKeywordTest() {
        String response = "find book";
        UserInputParser userInputParser = new UserInputParser(response);
        assertEquals("book", userInputParser.getFindKeyword());
    }
}

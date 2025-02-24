package miki.parse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import miki.exception.MikiException;

public class DateParserTest {
    @Test
    public void testParseDate_format1() {
        try {
            // Test Task
            LocalDateTime deadline = DateParser.parseDate("2021-08-21 20:00");

            assertEquals(deadline, LocalDateTime.parse("2021-08-21T20:00"));
        } catch (Exception e) {
            fail();
        } catch (Error e) {
            fail();
        }
    }

    @Test
    public void testParseDate_format2() {
        try {
            // Test Task
            LocalDateTime deadline = DateParser.parseDate("08-21-2021 20:00");

            assertEquals(deadline, LocalDateTime.parse("2021-08-21T20:00"));
        } catch (Exception e) {
            fail();
        } catch (Error e) {
            fail();
        }
    }

    @Test
    public void testParseDate_format3() {
        try {
            // Test Task
            LocalDateTime deadline = DateParser.parseDate("21-08-2021 20:00");

            assertEquals(deadline, LocalDateTime.parse("2021-08-21T20:00"));
        } catch (Exception e) {
            fail();
        } catch (Error e) {
            fail();
        }
    }

    @Test
    public void testParseDate_format4() {
        try {
            // Test Task
            LocalDateTime deadline = DateParser.parseDate("20:00 2021-08-21");

            assertEquals(deadline, LocalDateTime.parse("2021-08-21T20:00"));
        } catch (Exception e) {
            fail();
        } catch (Error e) {
            fail();
        }
    }

    @Test
    public void testParseDate_format5() {
        try {
            // Test Task
            LocalDateTime deadline = DateParser.parseDate("20:00 08-21-2021");

            assertEquals(deadline, LocalDateTime.parse("2021-08-21T20:00"));
        } catch (Exception e) {
            fail();
        } catch (Error e) {
            fail();
        }
    }

    @Test
    public void testParseDate_format6() {
        try {
            // Test Task
            LocalDateTime deadline = DateParser.parseDate("20:00 21-08-2021");

            assertEquals(deadline, LocalDateTime.parse("2021-08-21T20:00"));
        } catch (Exception e) {
            fail();
        } catch (Error e) {
            fail();
        }
    }

    @Test
    public void testParseDate_fail() {
        try {
            // Test Task
            LocalDateTime deadline = DateParser.parseDate("0");

            fail();
        } catch (MikiException e) {
            assertEquals(e.getMessage(), "The date format is invalid.");
        } catch (Error e) {
            fail();
        }
    }
}

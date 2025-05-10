package dynamis;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void stringDateTest(){
        assertEquals("[D][ ] Test (by: today)", new Deadline("Test"
                , "today").toString());
        assertEquals("[E][ ] Test 2 (from: today to: tmr)"
                , new Event("Test 2", "today", "tmr").toString());
    }

    @Test
    public void objDateTest(){
        assertEquals("[D][ ] Test (by: Jan 1 2025)", new Deadline("Test"
                , "2025-01-01").toString());
        assertEquals("[E][ ] Test 2 (from: Jan 1 2025 to: Jan 2 2026)"
                , new Event("Test 2", "2025-01-01", "2026-01-02").toString());
    }

    @Test
    public void mixedDateTest(){
        assertEquals("[E][ ] Test 2 (from: Today to: Jan 2 2026)"
                , new Event("Test 2", "Today", "2026-01-02").toString());
    }
}

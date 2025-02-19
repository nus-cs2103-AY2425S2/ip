package mab.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlinesTest {
    @Test
    public void testSaveString() {
      Deadlines d1 = new Deadlines("test", false ,"12-10-2021");
      Deadlines d2 = new Deadlines("test", false ,"12/10/2021");
      Deadlines d3 = new Deadlines("test", false ,"04/12/2020 16:40");
      Deadlines d4 = new Deadlines("test", false ,"04-12-2020 16:40");

      assertEquals("D | false | test | 12/10/2021 00:00:00", d1.getSaveString());
      assertEquals("D | false | test | 12/10/2021 00:00:00", d2.getSaveString());
      assertEquals("D | false | test | 04/12/2020 16:40:00", d3.getSaveString());
      assertEquals("D | false | test | 04/12/2020 16:40:00", d4.getSaveString());

      d1.setDone(true);
      assertEquals("D | true | test | 12/10/2021 00:00:00", d1.getSaveString());
    }
}

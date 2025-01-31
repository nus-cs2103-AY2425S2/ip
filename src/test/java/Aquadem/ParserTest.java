package Aquadem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ParserTest {
    @Test
    public void Test1() {
        String[] details = {""};
        Parser parser = new Parser();
        try {
            assertEquals(new Pair(0, details), parser.encodeCommand("list", 0));
        } catch (DetailException e) {
            //
        }
    }

    @Test
    public void Test2() {
        String[] details = {"holiday ", " today ", " tommorow"};

        Parser parser = new Parser();


        try {
//            Pair temp  = parser.encodeCommand("event holiday /from today /to tommorow", 0);
//            System.out.println(temp.getContents()[2]);
            assertEquals(new Pair(2, details), parser.encodeCommand("event holiday /from today /to tommorow", 0));
        } catch (DetailException e) {
            //
        }
    }
    @Test
    public void Test3() {
        String[] details = {"unknown"};
        Parser parser = new Parser();

        try {
//            Pair temp  = parser.encodeCommand("event holiday /from today /to tommorow", 0);
//            System.out.println(temp.getContents()[2]);
            assertEquals(new Pair(-1, details), parser.encodeCommand("niceEvent holiday /from today /to tommorow", 0));
        } catch (DetailException e) {
            //
        }
    }



}

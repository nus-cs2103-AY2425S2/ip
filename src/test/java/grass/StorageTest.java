package grass;  //same package as the class being tested

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void loadFromTxtTest(){
        Storage storage = new Storage("data/test.txt");
        try {
            assertEquals(3, storage.loadFromTxt().size());
        } catch (GrassException e) {
            assertEquals("File not found.", e.getMessage());
        }
    }

    @Test
    public void writeToTxtTest(){
        Storage storage = new Storage("data/test.txt");
        TaskList tasks = new TaskList();
        tasks.addTask(new Event("project meeting", "Aug 6th", "4pm"));
        tasks.addTask(new Todo("join sports club"));
        tasks.addTask(new Deadline("finish homework", "7pm"));
        try {
            storage.writeToTxt(tasks.getTasks());
        } catch (GrassException e) {
            assertEquals("An error ocurred.", e.getMessage());
        }
    }
}
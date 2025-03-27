package yapper.datastorage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import yapper.tasktypes.TaskList;

class DataStorageTest {
    private DataStorage dataStorage;
    private final String testFilePath = "test_data/YapperTest.txt";

    @BeforeEach
    void setUp() {
        dataStorage = new DataStorage(testFilePath);
    }

    @Test
    void testLoadDataEmptyFile() throws IOException {
        // Ensure test file is empty
        File file = new File(testFilePath);
        file.getParentFile().mkdirs(); // Ensure directory exists
        file.createNewFile();
        new FileWriter(file, false).close(); // Clear file contents

        TaskList taskList = dataStorage.loadData();
        assertNotNull(taskList, "TaskList should not be null");
        assertEquals(0, taskList.getList().size(), "TaskList should be empty for an empty file");
    }

    @Test
    void testLoadDataWithTasks() throws IOException {
        // Prepare test file with mock task data
        File file = new File(testFilePath);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("todo Read a book\n");
            writer.write("deadline Submit assignment /by 2024/02/05 0000\n");
        }

        TaskList taskList = dataStorage.loadData();
        assertEquals(2, taskList.getList().size(), "TaskList should contain 2 tasks");
        assertEquals("todo Read a book", taskList.getList().get(0).getUserInput(), "First task should match");
        assertEquals("deadline Submit assignment /by 2024/02/05 0000", taskList.getList().get(1).getUserInput(),
                "Second task should match");
    }
}

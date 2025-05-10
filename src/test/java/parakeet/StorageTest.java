package parakeet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parakeet.task.Deadline;
import parakeet.task.Event;
import parakeet.task.Task;
import parakeet.task.Todo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {
    private TaskList taskList;
    private Storage storage;
    private Path tempFilePath;
    @BeforeEach
    void setUp() throws IOException {
        // Create a temporary file for testing
        tempFilePath = Files.createTempFile("parakeet_test", ".txt");
        storage = new Storage(tempFilePath);
        taskList = new TaskList();
    }
    @Test
    void writeToFile_taskSet_writeCorrectFormat() throws IOException {
        // Arrange: Add tasks
        Task todo = new Todo(false, "Read book");
        Task deadline = new Deadline(true, "Submit assignment"
                , LocalDateTime.of(2024, 1, 30, 23, 59));
        Task event = new Event(false, "Team meeting",
                LocalDateTime.of(2024, 2, 1, 14, 0),
                LocalDateTime.of(2024, 2, 1, 16, 0));
        try {
            taskList.add(todo);
            taskList.add(deadline);
            taskList.add(event);
        } catch (DuplicateTaskError e) {
            assert false;
        }


        storage.writeToFile(taskList);


        String expectedContent = "T | 0 | Read book | 0 | 0 \n" +
                "D | 1 | Submit assignment | Jan 30 2024 23:59 | 0 \n" +
                "E | 0 | Team meeting | Feb 01 2024 14:00 | Feb 01 2024 16:00\n";

        String fileContent = Files.readString(tempFilePath);
        assertEquals(expectedContent, fileContent, "File content should match the expected format.");
    }

    @Test
    void writeToFile_todoTasks_writeCorrectFormat() throws IOException {
        // Arrange: Add tasks
        Task todoOne = new Todo(false, "Read book");
        Task todoTwo = new Todo(true, "go shopping");
        Task todoThree = new Todo(false, "UPPERCASE TASK");
        try {
            taskList.add(todoOne);
            taskList.add(todoTwo);
            taskList.add(todoThree);
        } catch (DuplicateTaskError e) {
            assert false;
        }

        storage.writeToFile(taskList);


        String expectedContent = "T | 0 | Read book | 0 | 0 \n"
                + "T | 1 | go shopping | 0 | 0 \n"
                + "T | 0 | UPPERCASE TASK | 0 | 0 \n";

        String fileContent = Files.readString(tempFilePath);
        assertEquals(expectedContent, fileContent, "File content should match the expected format.");
    }

    @Test
    void writeToFile_deadlineTasks_writeCorrectFormat() throws IOException {
        // Arrange: Add tasks
        Task deadlineOne = new Deadline(true, "Submit assignment"
                , LocalDateTime.of(2024, 1, 30, 23, 59));
        Task deadlineTwo = new Deadline(false, "project meeting"
                , LocalDateTime.of(2025, 2, 12, 9, 59));
        Task deadlineThree = new Deadline(true, "UPPERCASE DEADLINE"
                , LocalDateTime.of(1999, 12, 01, 8, 00));

        try {
            taskList.add(deadlineOne);
            taskList.add(deadlineTwo);
            taskList.add(deadlineThree);

        } catch (DuplicateTaskError e) {
            assert false;
        }

        storage.writeToFile(taskList);


        String expectedContent = "D | 1 | Submit assignment | Jan 30 2024 23:59 | 0 \n"
                + "D | 0 | project meeting | Feb 12 2025 09:59 | 0 \n"
                + "D | 1 | UPPERCASE DEADLINE | Dec 01 1999 08:00 | 0 \n";


        String fileContent = Files.readString(tempFilePath);
        assertEquals(expectedContent, fileContent, "File content should match the expected format.");
    }

    @Test
    void writeToFile_eventTasks_writeCorrectFormat() throws IOException {
        // Arrange: Add tasks
        Task eventOne = new Event(false, "Team meeting",
                LocalDateTime.of(2024, 2, 1, 14, 0),
                LocalDateTime.of(2024, 2, 1, 16, 0));
        Task eventTwo = new Event(true, "video interview",
                LocalDateTime.of(1999, 3, 20, 9, 0),
                LocalDateTime.of(1999, 12,21, 16, 0));
        Task eventThree = new Event(false, "INTERN",
                LocalDateTime.of(2020, 2, 1, 1, 0),
                LocalDateTime.of(2024, 11, 30, 23, 59));


        try {
           taskList.add(eventOne);
           taskList.add(eventTwo);
           taskList.add(eventThree);
        } catch (DuplicateTaskError e) {
            assert false;
        }

        storage.writeToFile(taskList);


        String expectedContent = "E | 0 | Team meeting | Feb 01 2024 14:00 | Feb 01 2024 16:00\n"
                +"E | 1 | video interview | Mar 20 1999 09:00 | Dec 21 1999 16:00\n"
                +"E | 0 | INTERN | Feb 01 2020 01:00 | Nov 30 2024 23:59\n";

        String fileContent = Files.readString(tempFilePath);
        assertEquals(expectedContent, fileContent, "File content should match the expected format.");
    }


    @Test
    void writeToFile_emptyTaskList_shouldWriteNothing() throws IOException {
        // Act: Write an empty task list
        storage.writeToFile(taskList);

        // Assert: File should be empty
        String fileContent = Files.readString(tempFilePath);
        assertTrue(fileContent.isEmpty(), "File should be empty when no tasks are written.");
    }

    }

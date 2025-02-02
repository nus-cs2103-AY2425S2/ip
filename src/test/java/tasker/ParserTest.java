package tasker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import tasker.command.AddCommand;
import tasker.command.ByeCommand;
import tasker.command.Command;
import tasker.command.CommandType;
import tasker.command.DeleteCommand;
import tasker.command.FindCommand;
import tasker.command.ListCommand;
import tasker.command.MarkCommand;
import tasker.command.UnmarkCommand;
import tasker.exception.TaskerException;
import tasker.task.DateTimeTask;
import tasker.task.Deadline;
import tasker.task.Event;
import tasker.task.Task;
import tasker.task.TaskType;
import tasker.task.Todo;

class ParserTest {
    @Nested
    @DisplayName("parseCommand Tests")
    class ParseCommandTests {
        private String todo = CommandType.TODO.toString();
        private String deadline = CommandType.DEADLINE.toString();
        private String by = " /by 26/1/2025 2359";
        private String event = CommandType.EVENT.toString();
        private String from = " /from 26/1/2025 1000";
        private String to = " /to 26/01/2025 1200";
        private String delete = CommandType.DELETE.toString();
        private String mark = CommandType.MARK.toString();
        private String unmark = CommandType.UNMARK.toString();
        private String find = CommandType.FIND.toString();
        private String description = " read book";
        private String deadlineDescription = deadline + description;
        private String eventDescription = event + description;
        private String eventDescriptionFrom = eventDescription + from;

        @Test
        @DisplayName("Parse todo add command successfully")
        void parseCommand_validTodo_success() throws TaskerException {
            String input = todo + description;
            Command expected = new AddCommand(new Todo("read book"));
            Command actual = Parser.parseCommand(input);
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Parse deadline add command successfully")
        void parseCommand_validDeadline_success() throws TaskerException {
            String input = deadlineDescription + by;
            Deadline deadline = new Deadline("read book",
                    LocalDateTime.parse("2025-01-26T23:59"));
            Command expected = new AddCommand(deadline);
            Command actual = Parser.parseCommand(input);
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Parse event add command successfully")
        void parseCommand_validEvent_success() throws TaskerException {
            String input = eventDescriptionFrom + to;
            Event event = new Event("read book",
                    LocalDateTime.parse("2025-01-26T10:00"),
                    LocalDateTime.parse("2025-01-26T12:00"));
            Command expected = new AddCommand(event);
            Command actual = Parser.parseCommand(input);
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Parse delete command successfully")
        void parseCommand_validDelete_success() throws TaskerException {
            String input = delete + " 1";
            Command expected = new DeleteCommand(0);
            Command actual = Parser.parseCommand(input);
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Parse mark command successfully")
        void parseCommand_validMark_success() throws TaskerException {
            String input = mark + " 2";
            Command expected = new MarkCommand(1);
            Command actual = Parser.parseCommand(input);
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Parse unmark command successfully")
        void parseCommand_validUnmark_success() throws TaskerException {
            String input = unmark + " 3";
            Command expected = new UnmarkCommand(2);
            Command actual = Parser.parseCommand(input);
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Parse find command successfully")
        void parseCommand_validFind_success() throws TaskerException {
            String input = find + description;
            Command expected = new FindCommand("read book");
            Command actual = Parser.parseCommand(input);
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Parse list command successfully")
        void parseCommand_validList_success() throws TaskerException {
            String input = CommandType.LIST.toString();
            Command expected = new ListCommand();
            Command actual = Parser.parseCommand(input);
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Parse bye command successfully")
        void parseCommand_validBye_success() throws TaskerException {
            String input = CommandType.BYE.toString();
            Command expected = new ByeCommand();
            Command actual = Parser.parseCommand(input);
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Throw exception for unknown command")
        void parseCommand_unknownCommand_exceptionThrown() {
            String input = "unknowncmd";
            TaskerException exception = assertThrows(TaskerException.class, () -> {
                Parser.parseCommand(input);
            });
            assertTrue(exception.getMessage().contains("Unknown command: unknowncmd"));
        }

        @Test
        @DisplayName("Throw exception when description is missing")
        void parseCommand_missingDescription_exceptionThrown() {
            String input1 = todo;
            TaskerException exception1 = assertThrows(TaskerException.class, () -> {
                Parser.parseCommand(input1);
            });
            String input2 = deadline;
            TaskerException exception2 = assertThrows(TaskerException.class, () -> {
                Parser.parseCommand(input2);
            });
            String input3 = deadline + by;
            TaskerException exception3 = assertThrows(TaskerException.class, () -> {
                Parser.parseCommand(input3);
            });
            String input4 = event;
            TaskerException exception4 = assertThrows(TaskerException.class, () -> {
                Parser.parseCommand(input4);
            });
            String input5 = event + from + to;
            TaskerException exception5 = assertThrows(TaskerException.class, () -> {
                Parser.parseCommand(input5);
            });
            String errMsg = "Please provide a description for the task.";
            assertEquals(errMsg,
                    exception1.getMessage());
            assertEquals(errMsg,
                    exception2.getMessage());
            assertEquals(errMsg,
                    exception3.getMessage());
            assertEquals(errMsg,
                    exception4.getMessage());
            assertEquals(errMsg,
                    exception5.getMessage());
        }

        @Test
        @DisplayName("Throw exception when delete, mark and unmark command does not get a number")
        void parseCommand_notNumberIndex_exceptionThrown() {
            String invalid = " invalid";
            String input1 = delete + invalid;
            TaskerException exception1 = assertThrows(TaskerException.class, () -> {
                Parser.parseCommand(input1);
            });
            String input2 = mark + invalid;
            TaskerException exception2 = assertThrows(TaskerException.class, () -> {
                Parser.parseCommand(input2);
            });
            String input3 = unmark + invalid;
            TaskerException exception3 = assertThrows(TaskerException.class, () -> {
                Parser.parseCommand(input3);
            });
            String errMsg = "Please provide a valid task number.";
            assertEquals(errMsg, exception1.getMessage());
            assertEquals(errMsg, exception2.getMessage());
            assertEquals(errMsg, exception3.getMessage());
        }

        @Test
        @DisplayName("Throw exception when delete, mark and unmark command is missing index")
        void parseCommand_missingIndex_exceptionThrown() {
            String inpu1 = delete;
            TaskerException exception1 = assertThrows(TaskerException.class, () -> {
                Parser.parseCommand(inpu1);
            });
            String input2 = mark;
            TaskerException exception2 = assertThrows(TaskerException.class, () -> {
                Parser.parseCommand(input2);
            });
            String input3 = unmark;
            TaskerException exception3 = assertThrows(TaskerException.class, () -> {
                Parser.parseCommand(input3);
            });
            String errMsg = "Please provide a task number.";
            assertEquals(errMsg, exception1.getMessage());
            assertEquals(errMsg, exception2.getMessage());
            assertEquals(errMsg, exception3.getMessage());
        }

        @Test
        @DisplayName("Throw exception for deadline with invalid date")
        void parseCommand_deadlineInvalidDate_exceptionThrown() {
            String input1 = deadlineDescription;
            TaskerException exception1 = assertThrows(TaskerException.class, () -> {
                Parser.parseCommand(input1);
            });
            String input2 = deadlineDescription + " /by invalid";
            TaskerException exception2 = assertThrows(TaskerException.class, () -> {
                Parser.parseCommand(input2);
            });
            String input3 = deadlineDescription + " /invalid 26/1/2025 2359";
            TaskerException exception3 = assertThrows(TaskerException.class, () -> {
                Parser.parseCommand(input3);
            });
            String errMsg = String.format("Please provide a deadline with: \"/by %s\".",
                    DateTimeTask.INPUT_FORMAT);
            assertEquals(exception1.getMessage(), errMsg);
            assertEquals(exception2.getMessage(), errMsg);
            assertEquals(exception3.getMessage(), errMsg);
        }

        @Test
        @DisplayName("Throw exception for event with invalid dates")
        void parseCommand_eventInvalidDate_exceptionThrown() {
            String input1 = eventDescription;
            TaskerException exception1 = assertThrows(TaskerException.class, () -> {
                Parser.parseCommand(input1);
            });
            String input2 = eventDescriptionFrom;
            TaskerException exception2 = assertThrows(TaskerException.class, () -> {
                Parser.parseCommand(input2);
            });
            String input3 = eventDescriptionFrom + " /to invalid";
            TaskerException exception3 = assertThrows(TaskerException.class, () -> {
                Parser.parseCommand(input3);
            });
            String input4 = eventDescriptionFrom + " /invalid 26/01/2025 1200";
            TaskerException exception4 = assertThrows(TaskerException.class, () -> {
                Parser.parseCommand(input4);
            });
            String input5 = eventDescription + to;
            TaskerException exception5 = assertThrows(TaskerException.class, () -> {
                Parser.parseCommand(input5);
            });
            String input6 = eventDescription + " /from invalid" + to;
            TaskerException exception6 = assertThrows(TaskerException.class, () -> {
                Parser.parseCommand(input6);
            });
            String input7 = eventDescription + " /invalid 26/1/2025 1000" + to;
            TaskerException exception7 = assertThrows(TaskerException.class, () -> {
                Parser.parseCommand(input7);
            });
            String errMsg = String.format("Please provide a start and end time with: \"/from %s /to %s\".",
                    DateTimeTask.INPUT_FORMAT, DateTimeTask.INPUT_FORMAT);
            assertEquals(exception1.getMessage(), errMsg);
            assertEquals(exception2.getMessage(), errMsg);
            assertEquals(exception3.getMessage(), errMsg);
            assertEquals(exception4.getMessage(), errMsg);
            assertEquals(exception5.getMessage(), errMsg);
            assertEquals(exception6.getMessage(), errMsg);
            assertEquals(exception7.getMessage(), errMsg);
        }

        @Test
        @DisplayName("Throw exception when find search term is missing")
        void parseCommand_missingSearch_exceptionThrown() {
            String input = find;
            TaskerException exception = assertThrows(TaskerException.class, () -> {
                Parser.parseCommand(input);
            });
            assertEquals(exception.getMessage(), "Please provide a search term.");
        }
    }

    @Nested
    @DisplayName("parseStorage Tests")
    class ParseStorageTests {
        private String todoType = TaskType.T.toString();
        private String deadlineType = TaskType.D.toString();
        private String by = "|2025-01-26T23:59";
        private String eventType = TaskType.E.toString();
        private String from = "|2025-01-26T10:00";
        private String to = "|2025-01-26T12:00";
        private String marked = "|true";
        private String unmarked = "|false";
        private String description = "|read book";
        private String markedDescription = marked + description;
        private String unmarkedDescription = unmarked + description;

        @Test
        @DisplayName("Parse valid todo from storage")
        void parseStorage_validTodo_success() throws TaskerException {
            String input1 = todoType + markedDescription;
            Task expected1 = new Todo("read book", true);
            Task actual1 = Parser.parseStorage(input1);
            String input2 = todoType + unmarkedDescription;
            Task expected2 = new Todo("read book", false);
            Task actual2 = Parser.parseStorage(input2);
            assertEquals(expected1, actual1);
            assertEquals(expected2, actual2);
        }

        @Test
        @DisplayName("Parse valid deadline from storage")
        void parseStorage_validDeadline_success() throws TaskerException {
            String input1 = deadlineType + markedDescription + by;
            Deadline expected1 = new Deadline("read book", true,
                    LocalDateTime.parse("2025-01-26T23:59"));
            Task actual1 = Parser.parseStorage(input1);
            String input2 = deadlineType + unmarkedDescription + by;
            Deadline expected2 = new Deadline("read book", false,
                    LocalDateTime.parse("2025-01-26T23:59"));
            Task actual2 = Parser.parseStorage(input2);
            assertEquals(expected1, actual1);
            assertEquals(expected2, actual2);
        }

        @Test
        @DisplayName("Parse valid event from storage")
        void parseStorage_validEvent_success() throws TaskerException {
            String input1 = eventType + unmarkedDescription + from + to;
            Event expected1 = new Event("read book", false,
                    LocalDateTime.parse("2025-01-26T10:00"),
                    LocalDateTime.parse("2025-01-26T12:00"));
            Task actual1 = Parser.parseStorage(input1);
            String input2 = eventType + markedDescription + from + to;
            Event expected2 = new Event("read book", true,
                    LocalDateTime.parse("2025-01-26T10:00"),
                    LocalDateTime.parse("2025-01-26T12:00"));
            Task actual2 = Parser.parseStorage(input2);
            assertEquals(expected1, actual1);
            assertEquals(expected2, actual2);
        }

        @Test
        @DisplayName("Throw exception for with invalid parts")
        void parseStorage_insufficientParts_exceptionThrown() {
            String input = "T|false";
            TaskerException exception = assertThrows(TaskerException.class, () -> {
                Parser.parseStorage(input);
            });
            assertEquals("Incorrect storage format", exception.getMessage());
        }
    }
}

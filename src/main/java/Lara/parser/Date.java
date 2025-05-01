package Lara.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import Lara.ui.Task;
import Lara.ui.Deadline;
import Lara.ui.Event;


public class Date {

    public static Task Date(String input) {
        if (input.startsWith("deadline")) {
            String[] parts = input.split(" /by ");
            String description = parts[0].substring(9).trim();
            String dateTime = parts[1].trim();
            return new Deadline(description, dateTime);
        } else if (input.startsWith("event")) {
            String[] parts = input.split(" /from | /to ");
            String description = parts[0].substring(6).trim();
            String fromDateTime = parts[1].trim();
            String toDateTime = parts[2].trim();
            return new Event(description, fromDateTime, toDateTime);
        }
        return null;
    }
    public static boolean isValidDateTime(String dateTime) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HHmm"); //valid date formatting
        format.setLenient(false);
        try {
            format.parse(dateTime);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}


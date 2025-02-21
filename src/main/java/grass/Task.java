package grass;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = formatDateTime(description);
        this.isDone = false;
    }

    public String formatDateTime(String description) {
        Pattern pattern = Pattern.compile("(\\d{1,2}/\\d{1,2}/\\d{4}) (\\d{4})");
        Matcher matcher = pattern.matcher(description);

        if (matcher.find()) {
            String datePart = matcher.group(1);
            String timePart = matcher.group(2);
            String formattedTime = timePart.substring(0, 2) + ":" + timePart.substring(2);

            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");

            LocalDateTime dateTime = LocalDateTime.parse(datePart + " " + formattedTime, inputFormatter);

            dateTime = dateTime.plusDays(0);

            String newDatePart = dateTime.format(outputFormatter);
            String newTimePart = dateTime.format(timeFormatter);

            // Replace the original date and time in the string
            String updatedString = matcher.replaceFirst(newDatePart + " " + newTimePart);
            return updatedString;
        }

        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            Task t = (Task) obj;
            return this.description.equals(t.description);
        }
        return false;
    }

}
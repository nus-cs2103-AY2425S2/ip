package zea.task;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * A task of type event that has both a start and end datetime
 */
public class Event extends Task {

    protected LocalDateTime to;
    protected LocalDateTime from;

    /**
     * Constructor of an Event
     * @param description - A description of the event
     * @param from - the start datetime of the event
     * @param to - the end datetime of the event
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toFileFormattedString() {
        ArrayList<String> tags = this.getTags();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tags.size(); i++) {
            if (i != tags.size() - 1) {
                sb.append(tags.get(i)).append(',');
            } else {
                sb.append(tags.get(i));
            }
        }
        return "E|" + (this.isDone ? "1" : "0") + "|" + description + "|"
                + from.format(FORMATTER) + "|" + to.format(FORMATTER) + "|" + sb;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + " to: " + to + ")\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Event event = (Event) o;
        return event.description.equals(this.description) && event.to.equals(this.to) && event.from.equals(this.from);
    }
}

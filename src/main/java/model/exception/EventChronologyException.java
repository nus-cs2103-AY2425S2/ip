package model.exception;

import model.response.MessageGenerator;

/**
 * Represents an exception thrown when a event chronology is invalid. That is,
 * an event ends before it starts.
 */
public class EventChronologyException extends AliceException {

    /**
     * Constructs a new TaskFormatException
     */
    public EventChronologyException() {
        super();
    }

    @Override
    public String getMessage() {
        return MessageGenerator.getEventChronologyExceptionMessage();
    }
}

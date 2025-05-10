package bun.ui;

/**
 * DateFormatException represents errors related to wrong time data format.
 * @author OVOtter
 */
public class DateFormatException extends BunException {
    public DateFormatException() {
        super("Please provide date in the format \"YYYY-MM-DD\"!");
    }
}

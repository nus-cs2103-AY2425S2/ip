package bun.ui;

public class InvalidIndexException extends BunException {
    public InvalidIndexException(int index, int length) {
        super("There is no Task " + index + " >< You have " + length + " task(s) now.");
    }
}

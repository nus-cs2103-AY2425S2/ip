package bun.ui;

/**
 * BunException is the main entity we'll be using to handle all exceptions specific to Bun.
 * @author OVOtter
 */
public class BunException extends Exception {
    public BunException(String message) {
        super("    " + message);
    }
}

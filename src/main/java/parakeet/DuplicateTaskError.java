package parakeet;

public class DuplicateTaskError extends Exception {
    public DuplicateTaskError (String message) {
        super(message);
    }
}

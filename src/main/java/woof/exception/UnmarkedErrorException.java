package woof.exception;

public class UnmarkedErrorException extends Exception {
    /**
     * Reminds users of an attempt to unmark a task that have yet to be marked.
     */
    public UnmarkedErrorException() {
        super("WERWER! It seems like you are trying to unmark a task that you have not completed!");
    }
}

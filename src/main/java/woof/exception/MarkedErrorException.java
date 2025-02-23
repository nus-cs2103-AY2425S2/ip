package woof.exception;

public class MarkedErrorException extends Exception {
    /**
     * Reminds users of an attempt to mark a task that is already marked.
     */
    public MarkedErrorException() {
        super("WERWER! It seems like you are trying to mark a task that you have already completed!");
    }
}

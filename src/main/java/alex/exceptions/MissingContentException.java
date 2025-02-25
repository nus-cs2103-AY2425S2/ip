package alex.exceptions;

public class MissingContentException extends AlexException{
    public MissingContentException() {
        super("Did you miss the task description?");
    }
}

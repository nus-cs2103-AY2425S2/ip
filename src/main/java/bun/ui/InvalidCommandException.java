package bun.ui;

public class InvalidCommandException extends BunException {
    protected String invalidCommand;

    public InvalidCommandException(String invalidCommand) {
        super(invalidCommand + "? I don't understand ><");
        this.invalidCommand = invalidCommand;
    }
}

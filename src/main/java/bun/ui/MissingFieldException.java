package bun.ui;

public class MissingFieldException extends BunException {
    protected String varName;

    public MissingFieldException(String varName) {
        super("Bun can't read your mind >< Please provide the " + varName + " of the task!");
        this.varName = varName;
    }
}

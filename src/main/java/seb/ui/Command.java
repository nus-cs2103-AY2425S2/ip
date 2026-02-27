package seb.ui;

public class Command {

    private String args;
    private String command;

    public Command(String command, String args) {
        this.command = command;
        this.args = args;
    }

    /**
     * Returns String of Command in uppercase
     * eg "bye" -> "BYE"
     *
     * @return String of command
     */
    public String getCommand() {
        return this.command.toUpperCase();
    }

    /**
     * Returns args variable in Command object
     *
     * @return String of args
     */
    public String getArgs() {
        return this.args;
    }
}

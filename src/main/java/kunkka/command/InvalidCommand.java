package kunkka.command;

import kunkka.tasklist.Tasklist;

public class InvalidCommand extends Command {
    public InvalidCommand() {
        super("invalid");
    }

    @Override
    public String execute(Tasklist tasks) {
        return "Invalid command!";
    }
    
}

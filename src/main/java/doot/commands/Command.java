package doot.commands;

import doot.InvalidFormatException;

import java.io.IOException;

/**
 * Used as a template for all commands
 */
interface Command{
    String execute() throws InvalidFormatException, IOException;
}

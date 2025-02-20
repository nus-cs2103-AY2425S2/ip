package prophet.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.stream.Stream;

import prophet.command.Command;
import prophet.exception.InvalidTaskNumberException;
import prophet.exception.NoDescriptionException;
import prophet.gui.Ui;
import prophet.parser.Parser;

/**
 * The Save class helps to save to and retrieve tasks from a file.
 */
public class Save {
    private static final String FILE_PATH = "data";
    private static final String SAVE_PATH = "data/tasks.txt";

    /**
     * Saves the tasks to a file.
     * @param ui the user interface of the chatbot
     * @param storage the storage of tasks accumulated as the chatbot runs
     */
    public static void save(Ui ui, Storage storage) {
        try {
            File filePath = new File(FILE_PATH);
            if (!filePath.exists()) {
                filePath.mkdir();
            }
            File saveFile = new File(SAVE_PATH);
            if (!saveFile.exists()) {
                saveFile.createNewFile();
            }
            FileWriter writer = new FileWriter(SAVE_PATH);
            writer.write(storage.enumerateSaveList());
            writer.close();
        } catch (IOException e) {
            ui.print("An error occurred while saving the tasks: " + e.getMessage());
        }
    }

    /**
     * Loads the tasks from a file into the storage of tasks.
     * @param ui the user interface of the chatbot
     * @param storage the storage of tasks accumulated as the chatbot runs
     */
    public static void load(Ui ui, Storage storage) {
        try {
            File saveFile = new File(SAVE_PATH);
            if (!saveFile.exists()) {
                return;
            }
            Scanner sc = new Scanner(saveFile);
            while (sc.hasNextLine()) {
                String task = sc.nextLine();
                Stream<Command> loadedCommands = Parser.parse(task);
                loadedCommands.forEach(c -> {
                    try {
                        c.execute(ui, storage);
                    } catch (InvalidTaskNumberException | NoDescriptionException e) {
                        ui.print("An error occurred while loading the tasks: " + e.getMessage());
                    }
                });
            }
        } catch (IOException | NoDescriptionException e) {
            ui.print("An error occurred while loading the tasks: " + e.getMessage());
        }
    }
}

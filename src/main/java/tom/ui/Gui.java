package tom.ui;

import java.util.function.Consumer;

/**
 * Handles the graphical user interface with the user.
 */
public class Gui implements Ui {

    private final MainWindow mainWindow;

    public Gui(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    /**
     * Displays a formatted message in given dialog box.
     *
     * @param commandId  The commandId of dialog box to be formatted.
     * @param msg  The message template to be formatted.
     * @param args The arguments to be used in the message template.
     */
    public void showMessage(int commandId, String msg, Object... args) {
        String outputMsg = String.format(msg, args);
        DialogBox box = mainWindow.getBox(commandId);
        box.setText(outputMsg);
    }

    @Override
    public void promptForInput(int commandId, String promptMsg, Consumer<String> onInputReceived) {
        DialogBox box = mainWindow.getBox(commandId);
        box.setText(promptMsg);
        box.setHandler(onInputReceived);
    }
}

package tom.ui;

import java.util.function.Consumer;

/**
 * Abstracts the user interface for interacting with the user.
 */
public interface Ui {

    public void showMessage(int commandId, String msg, Object ...args);

    public void promptForInput(int commandId, String promptMsg, Consumer<String> onInputReceived);
}

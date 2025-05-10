package elchino.commands;

import elchino.exceptions.ElchinoException;
import elchino.storage.Storage;
import elchino.tasks.TaskList;
import elchino.ui.Ui;
import java.util.ArrayList;

public class FindCommand extends Command {
    private final String keyword;
    private static final String MESSAGE_NO_MATCHING_TASKS = "No se encontraron tareas coincidentes.";
    private static final String MESSAGE_MATCHING_TASKS = "Aquí están los partidos:";

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ElchinoException {
        ArrayList<String> matchingTasks = tasks.findTasks(keyword);

        if (matchingTasks.isEmpty()) {
            return MESSAGE_NO_MATCHING_TASKS;
        } else {
            StringBuilder response = new StringBuilder(MESSAGE_MATCHING_TASKS + "\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                response.append((i + 1)).append(". ").append(matchingTasks.get(i)).append("\n");
            }
            return response.toString().trim();
        }
    }
}

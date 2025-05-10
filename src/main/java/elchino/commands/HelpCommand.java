package elchino.commands;

import elchino.storage.Storage;
import elchino.tasks.TaskList;
import elchino.ui.Ui;

/**
 * Command to display help message.
 */
public class HelpCommand extends Command {
    private static final String HELP_MESSAGE =
            """
                    Lista de comandos:
                    1. list - Muestra todas las tareas.
                    2. todo [descripcion] - Agrega una tarea tipo 'To-Do'
                    3. deadline [descripcion] /by [fecha] - Agrega una tarea tipo 'Deadline'
                    4. event [descripcion] /at [fecha] - Agrega una tarea tipo 'Event'
                    5. mark [numero] - Marca una tarea como completada.
                    6. unmark [numero] - Marca una tarea como no completada.
                    7. delete [numero] - Elimina una tarea.
                    8. find [palabra clave] - Busca tareas que contengan la palabra clave.
                    9. bye - Salir del aplicación.
                    """;

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return HELP_MESSAGE;
    }
}

package Krypto.Commands;

import Krypto.Exceptions.KryptoExceptions;
import Krypto.IO.GUI;
import Krypto.IO.Storage;
import Krypto.Task.Task;
import Krypto.Utils.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class RescheduleCommand extends Command{
    private final String date;
    private final int index;

    /**
     * Constructs a ShowCommand with the specified date.
     *
     * @param date The date for which to display the tasks.
     */
    public RescheduleCommand(String index, String date) throws KryptoExceptions {
        this.date = date;
        try {
            this.index = Integer.parseInt(index);
        } catch(NumberFormatException e) {
            throw new KryptoExceptions("Invalid index supplied");
        }
    }
    @Override
    public void execute(GUI gui, TaskList tasks, Storage storage) throws KryptoExceptions {
        try {
            String[] dates = date.split(",");
            Task tsk = tasks.getTask(index - 1);
            LocalDateTime date1 = LocalDateTime.parse(dates[0], Krypto.Task.Task.INPUT_FORMAT);
            LocalDateTime date2 = null;
            if (dates.length == 2) {
                date2 = LocalDateTime.parse(dates[1], Krypto.Task.Task.INPUT_FORMAT);
            }
            tsk.setDate(date1, date2);
            storage.store(tasks);
            gui.newResponse(String.format("Great I've rescheduled this task: %s" ,tsk));
        } catch(DateTimeParseException e) {
            throw new KryptoExceptions("Invalid date format! Use yyyy/MM/dd HH:mm (e.g., 2/12/2019 18:00).");
        }
    }
    @Override
    public boolean isExit() {
        return false;
    }
}

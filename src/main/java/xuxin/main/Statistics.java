package xuxin.main;

import xuxin.task.Task;

/**
 * Calculates the statistics of the tasks.
 */
public class Statistics {
    private int total;
    private long done;
    private long notDone;

    public Statistics(TaskList tasks) {
        this.total = tasks.getSize();
        this.done = tasks.getTasks().stream().filter(t -> t.getIsDone()).count();
        this.notDone = total - done;
    }

    public String toString() {
        return String.format("📊 Task Statistics:\n"
                + "✅ Completed: %d\n"
                + "❌ Incomplete: %d\n"
                + "Total Tasks: %d", this.done, this.notDone, this.total);
    }

    /**
     * Increases the count of not done tasks.
     */
    public void addNotDone() {
        this.notDone++;
        this.total++;
        assert this.notDone + this.done ==  this.total;
    }

    /**
     * Increases the count of done tasks.
     */
    public void markAsDone() {
        this.done++;
        this.notDone--;
        assert this.notDone + this.done ==  this.total;
    }

    /**
     * Adjusts the counts when a task is deleted.
     */
    public void deleteTask(Task task) {
        this.total--;
        if (task.getIsDone()) {
            this.done--;
        } else {
            this.notDone--;
        }
        assert this.notDone + this.done ==  this.total;
    }
}

package nikingoda.TaskList;

import nikingoda.NikingodaException.NikingodaException;
import nikingoda.Task.Deadline;
import nikingoda.Task.Event;
import nikingoda.Task.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> taskContainsKeyword(String keyword) {
        keyword = keyword.trim();
        String[] keyWords = keyword.split(" ");
        ArrayList<Task> tmpTasks = new ArrayList<>();
        for (Task task : tasks) {
            String[] descriptionSplit = task.getDescription().split(" ");
            for (String word : descriptionSplit) {
                for (String kword : keyWords) {
                    if (word.equals(kword) && !tmpTasks.contains(task)) {
                        tmpTasks.add(task);
                        break;
                    }
                }
            }
        }
        return tmpTasks;
    }

    public int getSize() {
        return this.tasks.size();
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task delete(int id) {
        Task task = this.tasks.get(id);
        this.tasks.remove(id);
        return task;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task mark(int id) {
        Task task = tasks.get(id);
        task.mark();
        return task;
    }

    public Task unmark(int id) {
        Task task = tasks.get(id);
        task.unmark();
        return task;
    }

    public void listTasks() {
        int i = 1;
        for (Task task : tasks) {
            System.out.println(i + ". " + task);
            i++;
        }
    }

    /**
     * update description of task with id
     *
     * @param id          id of task
     * @param description new description
     */
    public Task updateTaskDescription(int id, String description) throws NikingodaException {
        try {
            this.tasks.get(id).updateDescription(description);
            return tasks.get(id);
        } catch (IndexOutOfBoundsException e) {
            throw new NikingodaException("There is no task with given id");
        } catch (Exception e) {
            throw new NikingodaException(e.getMessage());
        }
    }

    /**
     * update deadline for DeadlineTask
     *
     * @param id       id of task
     * @param deadline new deadline
     * @throws NikingodaException handle exception
     */
    public Task updateTaskDeadline(int id, String deadline) throws NikingodaException {
        try {
            Task task = this.tasks.get(id);
            if (!(task instanceof Deadline)) {
                throw new NikingodaException("You can only update deadline of Deadline task");
            }
            ((Deadline) task).updateDeadline(deadline);
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new NikingodaException("There is no task with given id");
        } catch (Exception e) {
            throw new NikingodaException(e.getMessage());
        }
    }

    /**
     * update begin_time of Event task
     *
     * @param id    id of task
     * @param begin new begin_time
     * @throws NikingodaException handle error
     */
    public Task updateTaskBegin(int id, String begin) throws NikingodaException {
        try {
            Task task = this.tasks.get(id);
            if (!(task instanceof Event)) {
                throw new NikingodaException("You can only update begin_time of Event task");
            }
            ((Event) task).updateBegin(begin);
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new NikingodaException("There is no task with given id");
        } catch (Exception e) {
            throw new NikingodaException(e.getMessage());
        }
    }

    /**
     * update end_time of Event task
     *
     * @param id  id of task
     * @param end new end_time
     * @throws NikingodaException handle error
     */
    public Task updateTaskEnd(int id, String end) throws NikingodaException {
        try {
            Task task = this.tasks.get(id);
            if (!(task instanceof Event)) {
                throw new NikingodaException("You can only update end_time of Event task");
            }
            ((Event) task).updateEnd(end);
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new NikingodaException("There is no task with given id");
        } catch (Exception e) {
            throw new NikingodaException(e.getMessage());
        }
    }
}

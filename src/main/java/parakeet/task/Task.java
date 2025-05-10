package parakeet.task;

public abstract class Task {
    public boolean isDone;
    public String description;

    private TaskType taskType;

    public Task(boolean isDone, String description, TaskType taskType) {
        this.isDone = isDone;
        this.description = description;
        this.taskType = taskType;
    }

    public void complete() {
        this.isDone = true;
    }

    public boolean contains(String keyword) {
        return this.description.contains(keyword);
    }

    public void unDone() {
        this.isDone = false;
    }

    public abstract String convertToFileFormat();

    public boolean checkDuplicate(Task task) {
        if (!task.description.equals(this.description)) {
            return false;
        }
        if (task.taskType != this.taskType) {
            return false;
        }
        //task with same description and type
        if (task.taskType == TaskType.TODO) {
            return true;
        } else if (task.taskType == TaskType.DEADLINE) {
            Deadline convertedTask = (Deadline) task;
            Deadline thisTask = (Deadline) this;
            return thisTask.checkSameTime(convertedTask);
        } else if (task.taskType == TaskType.EVENT) {
            Event convertedTask = (Event) task;
            Event thisTask = (Event) this;
            return thisTask.checkSameTime(convertedTask);
        } else {
            System.out.println("Error:invalid task type.");
            return false;
        }
    }
    @Override
    public String toString() {
        String str = "[";
        if(isDone) {
            str = str + "X";
        }
        str = str + "] " + description;
        return str;
    }
}

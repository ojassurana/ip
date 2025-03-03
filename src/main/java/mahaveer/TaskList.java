package mahaveer;

import java.util.ArrayList;
import java.util.List;
import mahaveer.exception.MaheveerException;
import mahaveer.task.Task;

public class TaskList {
    private final List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) throws MaheveerException {
        if (index < 0 || index >= tasks.size())
            throw new MaheveerException("Task number does not exist.");
        return tasks.remove(index);
    }

    public Task getTask(int index) throws MaheveerException {
        if (index < 0 || index >= tasks.size())
            throw new MaheveerException("Task number does not exist.");
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public List<Task> getAllTasks() {
        return tasks;
    }
}

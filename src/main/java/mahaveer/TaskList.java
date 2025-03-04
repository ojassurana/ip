package mahaveer;

import java.util.ArrayList;
import java.util.List;
import mahaveer.exception.MaheveerException;
import mahaveer.task.Task;

/**
 * The TaskList class encapsulates a list of Task objects and provides methods
 * to add, remove, and retrieve tasks, as well as to get the total number of tasks.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructs a new TaskList with the provided list of tasks.
     *
     * @param tasks the initial list of tasks
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a new task to the list.
     *
     * @param task the Task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index the index of the task to delete
     * @return the removed Task
     * @throws MaheveerException if the index is out of bounds
     */
    public Task deleteTask(int index) throws MaheveerException {
        if (index < 0 || index >= tasks.size())
            throw new MaheveerException("Task number does not exist.");
        return tasks.remove(index);
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index the index of the task to retrieve
     * @return the Task at the given index
     * @throws MaheveerException if the index is out of bounds
     */
    public Task getTask(int index) throws MaheveerException {
        if (index < 0 || index >= tasks.size())
            throw new MaheveerException("Task number does not exist.");
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the size of the task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the underlying list of tasks.
     *
     * @return a List of Task objects
     */
    public List<Task> getAllTasks() {
        return tasks;
    }
}

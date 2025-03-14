package mahaveer.command;

import mahaveer.TaskList;
import mahaveer.Ui;
import mahaveer.Storage;
import mahaveer.task.Task;
import mahaveer.exception.MaheveerException;

/**
 * The DeleteCommand class handles the deletion of a task.
 * <p>
 * It removes a task from the task list based on its number,
 * shows a confirmation message to the user, and updates the storage.
 * </p>
 */
public class DeleteCommand extends Command {
    private final int taskNumber;

    /**
     * Constructs a DeleteCommand with the specified task number.
     *
     * @param taskNumber the number of the task to delete (1-indexed)
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command to delete a task.
     * <p>
     * This method calculates the zero-based index from the task number,
     * deletes the task from the task list, displays a confirmation message,
     * and updates the storage.
     * </p>
     *
     * @param tasks   the current task list
     * @param ui      the user interface for displaying messages
     * @param storage the storage system for persisting tasks
     * @throws MaheveerException if the task number is invalid
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MaheveerException {
        int index = taskNumber - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new MaheveerException("Task number does not exist.");
        }
        Task removedTask = tasks.deleteTask(index);
        ui.showToUser("Noted. I've removed this task:");
        ui.showToUser("  " + removedTask);
        ui.showToUser("Now you have " + tasks.size() + " tasks in the list.");
        storage.deleteTask(index);
    }
}

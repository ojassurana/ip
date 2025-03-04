package mahaveer.command;

import mahaveer.TaskList;
import mahaveer.Ui;
import mahaveer.Storage;
import mahaveer.task.Task;
import mahaveer.exception.MaheveerException;

/**
 * The UnmarkCommand class handles marking a task as not done.
 * <p>
 * When executed, this command retrieves the task corresponding to the provided task number,
 * verifies that it is currently marked as done, and then unmarks it.
 * It updates the storage and displays a confirmation message to the user.
 * </p>
 */
public class UnmarkCommand extends Command {
    private final int taskNumber;

    /**
     * Constructs an UnmarkCommand with the specified task number.
     *
     * @param taskNumber the 1-indexed number of the task to be unmarked
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the unmark command.
     * <p>
     * This method calculates the zero-based index for the task, validates it,
     * retrieves the task, and if it is marked as done, it unmarks it.
     * The change is then reflected in the storage and a confirmation is displayed.
     * </p>
     *
     * @param tasks   the current task list
     * @param ui      the user interface for displaying messages
     * @param storage the storage system for persisting changes
     * @throws MaheveerException if the task number is invalid or the task is not found
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MaheveerException {
        int index = taskNumber - 1;
        if (index < 0 || index >= tasks.size())
            throw new MaheveerException("Task number does not exist.");
        Task task = tasks.getTask(index);
        if (!task.isDone()) {
            ui.showToUser("This task is already marked as not done!");
        } else {
            task.setDone(false);
            ui.showToUser("OK, I've marked this task as not done yet:");
            ui.showToUser("  [" + task.getStatusIcon() + "] " + task.getDescription());
            storage.unmarkTask(index);
        }
    }
}

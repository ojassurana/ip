package mahaveer.command;

import mahaveer.TaskList;
import mahaveer.Ui;
import mahaveer.Storage;
import mahaveer.task.Task;
import mahaveer.exception.MaheveerException;

/**
 * The MarkCommand class handles marking a task as done.
 * <p>
 * When executed, this command retrieves the task corresponding to the provided task number,
 * verifies that it is not already marked as done, and then marks it as completed.
 * The change is updated in the storage and a confirmation message is displayed to the user.
 * </p>
 */
public class MarkCommand extends Command {
    private final int taskNumber;

    /**
     * Constructs a MarkCommand with the specified task number.
     *
     * @param taskNumber the 1-indexed number of the task to be marked as done
     */
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the mark command.
     * <p>
     * This method calculates the zero-based index of the task, checks if the index is valid,
     * and then marks the task as done if it is not already completed. It updates the storage
     * and displays an appropriate message to the user.
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
        if (task.isDone()) {
            ui.showToUser("This task is already marked as done!");
        } else {
            storage.markTask(index);
            task.setDone(true);
            ui.showToUser("Nice! I've marked this task as done:");
            ui.showToUser("  [" + task.getStatusIcon() + "] " + task.getDescription());
        }
    }
}

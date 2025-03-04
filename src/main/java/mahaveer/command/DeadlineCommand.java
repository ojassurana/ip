package mahaveer.command;

import mahaveer.TaskList;
import mahaveer.Ui;
import mahaveer.Storage;
import mahaveer.task.Deadline;
import mahaveer.task.Task;
import mahaveer.exception.MaheveerException;

/**
 * The DeadlineCommand class handles the addition of a deadline task.
 * <p>
 * It creates a new deadline task with a description and a "/by" time,
 * adds it to the task list, displays a confirmation to the user, and saves it in storage.
 * </p>
 */
public class DeadlineCommand extends Command {
    private final String description;
    private final String by;

    /**
     * Constructs a new DeadlineCommand with the specified description and deadline time.
     *
     * @param description the task description
     * @param by          the deadline time (e.g., "/by tonight")
     */
    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the command to add a deadline task.
     * <p>
     * This method checks if the description and deadline time are valid, creates a new deadline task,
     * adds it to the task list, shows a confirmation to the user, and saves the task to storage.
     * </p>
     *
     * @param tasks   the current task list
     * @param ui      the user interface for displaying messages
     * @param storage the storage system for persisting tasks
     * @throws MaheveerException if the description or deadline time is invalid
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MaheveerException {
        if (description.isEmpty() || by == null || by.isEmpty()) {
            throw new MaheveerException("A 'deadline' requires a description and '/by' time.\n"
                    + "For example:\n  deadline Submit assignment /by tonight\n  deadline Finish reading /by next Monday");
        }
        Task task = new Deadline(description, by);
        tasks.addTask(task);
        ui.showToUser("Got it. I've added this task:");
        ui.showToUser("  " + task);
        storage.addDeadline(description, by);
    }
}

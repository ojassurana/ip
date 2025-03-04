package mahaveer.command;

import mahaveer.TaskList;
import mahaveer.Ui;
import mahaveer.Storage;
import mahaveer.exception.MaheveerException;

/**
 * Abstract class representing a command in the Mahaveer application.
 * <p>
 * Concrete command classes must extend this class and implement the {@code execute} method.
 * </p>
 */
public abstract class Command {

    /**
     * Executes the command using the provided task list, user interface, and storage.
     *
     * @param tasks   the current task list
     * @param ui      the user interface for displaying messages
     * @param storage the storage system for persisting tasks
     * @throws MaheveerException if an error occurs during command execution
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MaheveerException;

    /**
     * Indicates whether this command signals the application to exit.
     *
     * @return {@code true} if this is an exit command; {@code false} otherwise
     */
    public boolean isExit() {
        return false;
    }
}

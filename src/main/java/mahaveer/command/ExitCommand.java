package mahaveer.command;

import mahaveer.TaskList;
import mahaveer.Ui;
import mahaveer.Storage;

/**
 * The ExitCommand class handles the exit operation for the Mahaveer application.
 * <p>
 * When executed, it displays a farewell message to the user and signals the application to exit.
 * </p>
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command.
     * <p>
     * This method displays a farewell message on the user interface.
     * </p>
     *
     * @param tasks   the current task list (not used)
     * @param ui      the user interface for displaying messages
     * @param storage the storage system (not used)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showToUser("Jai Jinendra! Till we meet next time :)");
    }

    /**
     * Indicates that this command signals an exit.
     *
     * @return {@code true} indicating that the application should exit
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

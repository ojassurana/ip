package mahaveer.command;

import mahaveer.TaskList;
import mahaveer.Ui;
import mahaveer.Storage;
import mahaveer.task.Task;

/**
 * The ListCommand class is responsible for listing all the tasks in the task list.
 * <p>
 * When executed, it displays a message indicating either that there are no tasks available
 * or it prints each task with its index.
 * </p>
 */
public class ListCommand extends Command {

    /**
     * Executes the list command.
     * <p>
     * This method displays all tasks in the provided task list to the user via the UI.
     * If there are no tasks, it shows a message stating that no tasks are available.
     * </p>
     *
     * @param tasks   the current task list
     * @param ui      the user interface for displaying messages
     * @param storage the storage system (not used in this command)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() == 0) {
            ui.showToUser("No tasks available.");
        } else {
            ui.showToUser("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.getAllTasks().get(i);
                ui.showToUser(" " + (i + 1) + ". " + task);
            }
        }
    }
}

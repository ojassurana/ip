package mahaveer.command;

import java.util.ArrayList;
import java.util.List;

import mahaveer.TaskList;
import mahaveer.Ui;
import mahaveer.Storage;
import mahaveer.task.Task;
import mahaveer.exception.MaheveerException;

/**
 * The FindCommand class handles searching for tasks that match a specified query.
 * <p>
 * When executed, it filters tasks in the task list by checking if each task's description
 * contains the given query (case-insensitive) and displays the matching tasks to the user.
 * </p>
 */
public class FindCommand extends Command {
    private final String query;

    /**
     * Constructs a new FindCommand with the specified search query.
     *
     * @param query the query string used to search for matching tasks
     */
    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Executes the find command.
     * <p>
     * This method iterates over all tasks in the task list, collects tasks whose descriptions
     * contain the query (ignoring case), and displays either the matching tasks or a message
     * if no matches are found.
     * </p>
     *
     * @param tasks   the current task list
     * @param ui      the user interface for displaying messages
     * @param storage the storage system (not used by this command)
     * @throws MaheveerException if an error occurs during execution
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MaheveerException {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks.getAllTasks()) {
            if (task.getDescription().toLowerCase().contains(query.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.isEmpty()) {
            ui.showToUser("No matching tasks found.");
        } else {
            ui.showToUser("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                ui.showToUser(" " + (i + 1) + ". " + matchingTasks.get(i));
            }
        }
    }
}

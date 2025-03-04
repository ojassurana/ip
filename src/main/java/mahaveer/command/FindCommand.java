package mahaveer.command;

import java.util.ArrayList;
import java.util.List;
import mahaveer.TaskList;
import mahaveer.Ui;
import mahaveer.Storage;
import mahaveer.task.Task;
import mahaveer.exception.MaheveerException;

public class FindCommand extends Command {
    private final String query;

    public FindCommand(String query) {
        this.query = query;
    }

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
